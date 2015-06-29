package com.taobao.tddl.executor.handler;

import java.util.List;

import com.taobao.tddl.executor.codec.CodecFactory;
import com.taobao.tddl.executor.common.ExecutionContext;
import com.taobao.tddl.executor.exception.ExecutorException;
import com.taobao.tddl.executor.function.ScalarFunction;
import com.taobao.tddl.executor.record.CloneableRecord;
import com.taobao.tddl.executor.rowset.IRowSet;
import com.taobao.tddl.executor.spi.ITable;
import com.taobao.tddl.executor.spi.ITransaction;
import com.taobao.tddl.executor.utils.ExecUtils;
import com.taobao.tddl.optimizer.config.table.ColumnMeta;
import com.taobao.tddl.optimizer.config.table.IndexMeta;
import com.taobao.tddl.optimizer.core.expression.IFunction;
import com.taobao.tddl.optimizer.core.expression.IFunction.FunctionType;
import com.taobao.tddl.optimizer.core.plan.IPut;

public class ReplaceHandler extends PutHandlerCommon {

    public ReplaceHandler(){
        super();
    }

    @Override
    protected int executePut(ExecutionContext executionContext, IPut put, ITable table, IndexMeta meta)
                                                                                                       throws Exception {
        ITransaction transaction = executionContext.getTransaction();
        int affect_rows = 0;
        CloneableRecord key = CodecFactory.getInstance(CodecFactory.FIXED_LENGTH)
            .getCodec(meta.getKeyColumns())
            .newEmptyRecord();
        CloneableRecord value = CodecFactory.getInstance(CodecFactory.FIXED_LENGTH)
            .getCodec(meta.getValueColumns())
            .newEmptyRecord();
        List columns = put.getUpdateColumns();
        L: for (int i = 0; i < columns.size(); i++) {
            for (ColumnMeta cm : meta.getKeyColumns()) {
                if (cm.getName().equals(ExecUtils.getColumn(columns.get(i)).getColumnName())) {
                    Object v = put.getUpdateValues().get(i);
                    if (v instanceof IFunction) {
                        if (((IFunction) v).getFunctionType().equals(FunctionType.Aggregate)) {
                            throw new ExecutorException("replace is not support aggregate function");
                        }
                        IFunction func = ((IFunction) v);

                        v = ((ScalarFunction) func.getExtraFunction()).scalarCalucate((IRowSet) null, executionContext);

                    }
                    key.put(cm.getName(), v);
                    continue L;
                }
            }
            for (ColumnMeta cm : meta.getValueColumns()) {
                if (cm.getName().equals(ExecUtils.getColumn(columns.get(i)).getColumnName())) {
                    Object v = put.getUpdateValues().get(i);
                    value.put(cm.getName(), v);
                    break;
                }
            }
        }
        if (put.getPutType() == IPut.PUT_TYPE.INSERT) {
            CloneableRecord value1 = table.get(executionContext, key, meta, put.getTableName());
            if (value1 != null) {
                throw new ExecutorException("Duplicate_entry :" + key);
            }
        }

        table.put(executionContext, key, value, meta, put.getTableName());
        affect_rows++;
        return affect_rows;

    }

}
