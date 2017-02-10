package com.taobao.tddl.group.jdbc;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.NClob;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.taobao.tddl.atom.jdbc.DataChannel;
import com.taobao.tddl.atom.jdbc.TPreparedStatement;
import com.taobao.tddl.atom.utils.LoadFileUtils;
import com.taobao.tddl.common.jdbc.ParameterContext;
import com.taobao.tddl.common.jdbc.ParameterMethod;
import com.taobao.tddl.common.jdbc.Parameters;
import com.taobao.tddl.common.jdbc.SqlTypeParser;
import com.taobao.tddl.common.model.SqlType;
import com.taobao.tddl.group.config.GroupIndex;
import com.taobao.tddl.group.dbselector.DBSelector.AbstractDataSourceTryer;
import com.taobao.tddl.group.dbselector.DBSelector.DataSourceTryer;
import com.taobao.tddl.group.utils.GroupHintParser;

import com.taobao.tddl.common.utils.logger.Logger;
import com.taobao.tddl.common.utils.logger.LoggerFactory;

/**
 * @author linxuan
 * @author yangzhu
 */
public class TGroupPreparedStatement extends TGroupStatement implements TPreparedStatement {

    private static final Logger log = LoggerFactory.getLogger(TGroupPreparedStatement.class);

    private String              sql;

    public TGroupPreparedStatement(TGroupDataSource tGroupDataSource, TGroupConnection tGroupConnection, String sql,
                                   String appName){
        super(tGroupDataSource, tGroupConnection, appName);
        this.sql = sql;
    }

    private int                              autoGeneratedKeys = -1;
    private int[]                            columnIndexes;
    private String[]                         columnNames;

    // 参数列表到参数上下文的映射 如 1:name 2：'2011-11-11'
    protected Map<Integer, ParameterContext> parameterSettings = new HashMap<Integer, ParameterContext>();

    @Override
    public void clearParameters() throws SQLException {
        parameterSettings.clear();
    }

    private PreparedStatement createPreparedStatementInternal(Connection conn, String sql) throws SQLException {
        PreparedStatement ps;
        if (autoGeneratedKeys != -1) {
            ps = conn.prepareStatement(sql, autoGeneratedKeys);
        } else if (columnIndexes != null) {
            ps = conn.prepareStatement(sql, columnIndexes);
        } else if (columnNames != null) {
            ps = conn.prepareStatement(sql, columnNames);
        } else {
            int resultSetHoldability = this.resultSetHoldability;
            if (resultSetHoldability == -1) // 未调用过setResultSetHoldability
            resultSetHoldability = conn.getHoldability();

            ps = conn.prepareStatement(sql, this.resultSetType, this.resultSetConcurrency, resultSetHoldability);
        }
        setBaseStatement(ps);
        ps.setQueryTimeout(queryTimeout); // 这句可能抛出异常，所以要放到setBaseStatement之后
        ps.setFetchSize(fetchSize);
        ps.setMaxRows(maxRows);
        if (this.getLocalInfileInputStream() != null) {
            LoadFileUtils.setLocalInfileInputStream(ps, this.getLocalInfileInputStream());
        }

        if (ps instanceof DataChannel) {
            ((DataChannel) ps).fillMetaData(this.sqlMetaData);
        }
        return ps;
    }

    @Override
    public boolean execute() throws SQLException {
        if (log.isDebugEnabled()) {
            log.debug("invoke execute, sql = " + sql);
        }
        if (SqlTypeParser.isQuerySql(sql)) {
            executeQuery();
            return true;
        } else {
            super.updateCount = executeUpdate();
            return false;
        }
    }

    /*
     * ========================================================================
     * executeQuery逻辑
     * ======================================================================
     */
    @Override
    public ResultSet executeQuery() throws SQLException {
        checkClosed();
        ensureResultSetIsEmpty();

        boolean gotoRead = SqlType.SELECT.equals(SqlTypeParser.getSqlType(sql)) && tGroupConnection.getAutoCommit();
        Connection conn = tGroupConnection.getBaseConnection(sql, gotoRead);

        if (conn != null) {
            sql = GroupHintParser.removeTddlGroupHint(sql);
            return executeQueryOnConnection(conn, sql);
        } else {
            // hint优先
            GroupIndex dataSourceIndex = GroupHintParser.convertHint2Index(sql);
            sql = GroupHintParser.removeTddlGroupHint(sql);
            if (dataSourceIndex == null) {
                dataSourceIndex = ThreadLocalDataSourceIndex.getIndex();
            }

            return tGroupDataSource.getDBSelector(gotoRead).tryExecute(executeQueryTryer,
                retryingTimes,
                sql,
                dataSourceIndex);
        }
    }

    @Override
    protected ResultSet executeQueryOnConnection(Connection conn, String sql) throws SQLException {
        PreparedStatement ps = createPreparedStatementInternal(conn, sql);
        Parameters.setParameters(ps, parameterSettings);
        this.currentResultSet = ps.executeQuery();
        return this.currentResultSet;
    }

    /*
     * ========================================================================
     * executeUpdate逻辑
     * ======================================================================
     */
    @Override
    public int executeUpdate() throws SQLException {
        checkClosed();
        ensureResultSetIsEmpty();

        Connection conn = tGroupConnection.getBaseConnection(sql, false);
        if (conn != null) {
            sql = GroupHintParser.removeTddlGroupHint(sql);
            // #bug 2011-10-28,modify by junyu,updateCount not set,fixed
            int updateCount = executeUpdateOnConnection(conn);
            super.updateCount = updateCount;
            return updateCount;
        } else {
            // hint优先
            GroupIndex dataSourceIndex = GroupHintParser.convertHint2Index(sql);
            sql = GroupHintParser.removeTddlGroupHint(sql);
            if (dataSourceIndex == null) {
                dataSourceIndex = ThreadLocalDataSourceIndex.getIndex();
            }
            // #bug 2011-10-28,modify by junyu,updateCount not set,fixed
            int updateCount = tGroupDataSource.getDBSelector(false).tryExecute(null,
                executeUpdateTryer,
                retryingTimes,
                sql,
                dataSourceIndex);
            super.updateCount = updateCount;
            return updateCount;
        }
    }

    private int executeUpdateOnConnection(Connection conn) throws SQLException {
        PreparedStatement ps = createPreparedStatementInternal(conn, sql);

        Parameters.setParameters(ps, parameterSettings);
        return ps.executeUpdate();
    }

    private DataSourceTryer<Integer> executeUpdateTryer = new AbstractDataSourceTryer<Integer>() {

                                                            @Override
                                                            public Integer tryOnDataSource(DataSourceWrapper dsw,
                                                                                           Object... args)
                                                                                                          throws SQLException {
                                                                Connection conn = TGroupPreparedStatement.this.tGroupConnection.createNewConnection(dsw,
                                                                    false);
                                                                return executeUpdateOnConnection(conn);
                                                            }
                                                        };

    @Override
    public ResultSetMetaData getMetaData() throws SQLException {
        throw new UnsupportedOperationException("getMetaData");
    }

    @Override
    public ParameterMetaData getParameterMetaData() throws SQLException {
        throw new UnsupportedOperationException("getParameterMetaData");
    }

    /*
     * ========================================================================
     * setxxx SQL参数设置
     * ======================================================================
     */
    @Override
    public void setArray(int parameterIndex, Array x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setArray, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setAsciiStream, new Object[] {
                parameterIndex, x, length }));
    }

    @Override
    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setBigDecimal, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setBinaryStream, new Object[] {
                parameterIndex, x, length }));
    }

    @Override
    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setBlob, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setBoolean, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setByte(int parameterIndex, byte x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setByte, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setBytes, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setCharacterStream, new Object[] {
                parameterIndex, reader, length }));
    }

    @Override
    public void setClob(int parameterIndex, Clob x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setClob, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setDate(int parameterIndex, Date x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setDate1, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setDate2, new Object[] {
                parameterIndex, x, cal }));
    }

    @Override
    public void setDouble(int parameterIndex, double x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setDouble, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setFloat(int parameterIndex, float x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setFloat, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setInt(int parameterIndex, int x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setInt, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setLong(int parameterIndex, long x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setLong, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setNull1, new Object[] {
                parameterIndex, sqlType }));
    }

    @Override
    public void setNull(int paramIndex, int sqlType, String typeName) throws SQLException {
        parameterSettings.put(paramIndex, new ParameterContext(ParameterMethod.setNull2, new Object[] { paramIndex,
                sqlType, typeName }));
    }

    @Override
    public void setObject(int parameterIndex, Object x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setObject1, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setObject2, new Object[] {
                parameterIndex, x, targetSqlType }));
    }

    @Override
    public void setObject(int parameterIndex, Object x, int targetSqlType, int scale) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setObject3, new Object[] {
                parameterIndex, x, targetSqlType, scale }));
    }

    @Override
    public void setRef(int parameterIndex, Ref x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setRef, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setShort(int parameterIndex, short x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setShort, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setString(int parameterIndex, String x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setString, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setTime(int parameterIndex, Time x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setTime1, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setTime2, new Object[] {
                parameterIndex, x, cal }));
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setTimestamp1, new Object[] {
                parameterIndex, x }));
    }

    @Override
    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setTimestamp2, new Object[] {
                parameterIndex, x, cal }));
    }

    @Override
    public void setURL(int parameterIndex, URL x) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setURL, new Object[] {
                parameterIndex, x }));
    }

    @Override
    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        parameterSettings.put(parameterIndex, new ParameterContext(ParameterMethod.setUnicodeStream, new Object[] {
                parameterIndex, x, length }));
    }

    /*
     * ========================================================================
     * executeBatch
     * ======================================================================
     */
    private List<Map<Integer, ParameterContext>> pstArgs;

    @Override
    public void addBatch() throws SQLException {
        if (pstArgs == null) {
            pstArgs = new LinkedList<Map<Integer, ParameterContext>>();
        }
        Map<Integer, ParameterContext> newArg = new HashMap<Integer, ParameterContext>(parameterSettings.size());
        newArg.putAll(parameterSettings);

        parameterSettings.clear();
        pstArgs.add(newArg);
    }

    @Override
    public int[] executeBatch() throws SQLException {
        try {
            checkClosed();
            ensureResultSetIsEmpty();

            if (pstArgs == null || pstArgs.isEmpty()) {
                return new int[0];
            }

            Connection conn = tGroupConnection.getBaseConnection(sql, false);

            if (conn != null) {
                sql = GroupHintParser.removeTddlGroupHint(sql);
                // 如果当前已经有连接,则不做任何重试。对于更新来说，不管有没有事务，
                // 用户总期望getConnection获得连接之后，后续的一系列操作都在这同一个库，同一个连接上执行
                return executeBatchOnConnection(conn);
            } else {
                GroupIndex dataSourceIndex = GroupHintParser.convertHint2Index(sql);
                sql = GroupHintParser.removeTddlGroupHint(sql);
                if (dataSourceIndex == null) {
                    dataSourceIndex = ThreadLocalDataSourceIndex.getIndex();
                }
                return tGroupDataSource.getDBSelector(false).tryExecute(null,
                    executeBatchTryer,
                    retryingTimes,
                    dataSourceIndex);
            }
        } finally {
            if (pstArgs != null) {
                pstArgs.clear();
            }
        }
    }

    private DataSourceTryer<int[]> executeBatchTryer = new AbstractDataSourceTryer<int[]>() {

                                                         @Override
                                                         public int[] tryOnDataSource(DataSourceWrapper dsw,
                                                                                      Object... args)
                                                                                                     throws SQLException {
                                                             Connection conn = tGroupConnection.createNewConnection(dsw,
                                                                 false);
                                                             return executeBatchOnConnection(conn);
                                                         }
                                                     };

    private InputStream            localFileInputStream;

    // TODO 重试中Statement的关闭
    private int[] executeBatchOnConnection(Connection conn) throws SQLException {
        PreparedStatement ps = createPreparedStatementInternal(conn, sql);

        for (Map<Integer, ParameterContext> parameterSettings : pstArgs) {
            setBatchParameters(ps, parameterSettings.values());
            ps.addBatch();
        }

        return ps.executeBatch();
    }

    private static void setBatchParameters(PreparedStatement ps, Collection<ParameterContext> batchedParameters)
                                                                                                                throws SQLException {
        for (ParameterContext context : batchedParameters) {
            context.getParameterMethod().setParameter(ps, context.getArgs());
        }
    }

    /*
     * ========================================================================
     * 无逻辑的getter/setter
     * ======================================================================
     */
    public int getAutoGeneratedKeys() {
        return autoGeneratedKeys;
    }

    public void setAutoGeneratedKeys(int autoGeneratedKeys) {
        this.autoGeneratedKeys = autoGeneratedKeys;
    }

    public int[] getColumnIndexes() {
        return columnIndexes;
    }

    public void setColumnIndexes(int[] columnIndexes) {
        this.columnIndexes = columnIndexes;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    @Override
    public boolean isClosed() throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setPoolable(boolean poolable) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public boolean isPoolable() throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setNString(int parameterIndex, String value) throws SQLException {
        throw new SQLException("not support exception");

    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        throw new SQLException("not support exception");
    }

    @Override
    public void setLocalInfileInputStream(InputStream stream) {
        this.localFileInputStream = stream;
    }

    @Override
    public InputStream getLocalInfileInputStream() {
        return this.localFileInputStream;
    }

}
