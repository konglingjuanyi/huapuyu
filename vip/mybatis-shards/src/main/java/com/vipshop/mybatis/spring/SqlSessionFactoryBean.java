package com.vipshop.mybatis.spring;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.NestedIOException;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.util.ObjectUtils;

import com.vipshop.mybatis.converter.DefaultSqlConverter;
import com.vipshop.mybatis.converter.SqlConverter;
import com.vipshop.mybatis.plugin.ShardPlugin;
import com.vipshop.mybatis.strategy.ShardStrategy;

public class SqlSessionFactoryBean implements ApplicationContextAware, MultiDataSourceSupport {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private ApplicationContext applicationContext;

	private DataSource defaultDataSource;

	private SqlSessionFactory defaultSqlSessionFactory;

	private Map<String, DataSource> shardDataSources;

	private Map<String, SqlSessionFactory> shardSqlSessionFactory;

	private List<DataSource> shardDataSourceList;

	private Resource[] mapperLocations;

	private Map<String, ShardStrategy> shardStrategyMap = new HashMap<String, ShardStrategy>();
	private Map<String, Class<?>> shardStrategyConfig = new HashMap<String, Class<?>>();

	private SqlConverter sqlConverter = new DefaultSqlConverter();

	public DataSource getDefaultDataSource() {
		return defaultDataSource;
	}

	public void setDefaultDataSource(DataSource defaultDataSource) {
		if (defaultDataSource instanceof TransactionAwareDataSourceProxy) {
			// If we got a TransactionAwareDataSourceProxy, we need to perform
			// transactions for its underlying target DataSource, else data
			// access code won't see properly exposed transactions (i.e.
			// transactions for the target DataSource).
			this.defaultDataSource = ((TransactionAwareDataSourceProxy) defaultDataSource).getTargetDataSource();
		}
		else {
			this.defaultDataSource = defaultDataSource;
		}
	}

	public void setShardDataSourceList(List<DataSource> shardDataSourceList) {
		this.shardDataSourceList = shardDataSourceList;
	}

	public Map<String, DataSource> getShardDataSources() {
		return shardDataSources;
	}

	public void setMapperLocations(Resource[] mapperLocations) {
		this.mapperLocations = mapperLocations;
	}

	public void setShardStrategy(Map<String, Class<?>> shardStrategyMap) {
		this.shardStrategyConfig = shardStrategyMap;
	}

	public SqlSessionFactory getDefaultSqlSessionFactory() {
		return defaultSqlSessionFactory;
	}

	public Map<String, SqlSessionFactory> getShardSqlSessionFactory() {
		return shardSqlSessionFactory;
	}

	public Map<String, ShardStrategy> getShardStrategyMap() {
		return shardStrategyMap;
	}

	public void afterPropertiesSet() throws Exception {
		if (defaultDataSource == null && (shardDataSourceList == null || shardDataSourceList.size() == 0)) {
			throw new RuntimeException(" Property 'defaultDataSource' and property 'shardDataSourceList' can not be null together! ");
		}
		if (shardDataSourceList != null && shardDataSourceList.size() > 0) {
			shardDataSources = new LinkedHashMap<String, DataSource>();
			Map<String, DataSource> dataSourceMap = applicationContext.getBeansOfType(DataSource.class);
			for (Entry<String, DataSource> entry : dataSourceMap.entrySet()) {
				for (int i = 0; i < shardDataSourceList.size(); i++) {
					DataSource ds = shardDataSourceList.get(i);
					if (entry.getValue() == ds) {
						DataSource dataSource = entry.getValue();
						if (dataSource instanceof TransactionAwareDataSourceProxy) {
							dataSource = ((TransactionAwareDataSourceProxy) dataSource).getTargetDataSource();
						}
						shardDataSources.put(entry.getKey(), dataSource);
					}
				}
			}
		}
		if (defaultDataSource == null) {
			if (shardDataSourceList.get(0) instanceof TransactionAwareDataSourceProxy) {
				this.defaultDataSource = ((TransactionAwareDataSourceProxy) shardDataSourceList.get(0)).getTargetDataSource();
			}
			else {
				defaultDataSource = shardDataSources.get(0);
			}
		}

		this.defaultSqlSessionFactory = buildSqlSessionFactory(getDefaultDataSource());
		if (getShardDataSources() != null && getShardDataSources().size() > 0) {
			shardSqlSessionFactory = new LinkedHashMap<String, SqlSessionFactory>(getShardDataSources().size());
			for (Entry<String, DataSource> entry : getShardDataSources().entrySet()) {
				shardSqlSessionFactory.put(entry.getKey(), buildSqlSessionFactory(entry.getValue()));
			}
		}
		//
		if (shardStrategyConfig != null) {
			shardStrategyMap = new HashMap<String, ShardStrategy>();
			for (Map.Entry<String, Class<?>> entry : shardStrategyConfig.entrySet()) {
				Class<?> clazz = entry.getValue();
				if (!ShardStrategy.class.isAssignableFrom(clazz)) {
					throw new IllegalArgumentException("class " + clazz.getName() + " is illegal, subclass of ShardStrategy is required.");
				}
				try {
					shardStrategyMap.put(entry.getKey(), (ShardStrategy) (entry.getValue().newInstance()));
				}
				catch (Exception e) {
					throw new RuntimeException("new instance for class " + clazz.getName() + " failed, error:" + e.getMessage());
				}
			}
			//
			shardStrategyConfig = null;
		}
	}

	private SqlSessionFactory buildSqlSessionFactory(DataSource dataSource) throws IOException {
		ShardPlugin plugin = new ShardPlugin();
		plugin.setSqlConverter(sqlConverter);

		Configuration configuration = null;
		SpringManagedTransactionFactory transactionFactory = null;
		//
		configuration = new Configuration();
		configuration.addInterceptor(plugin);
		//
		transactionFactory = new SpringManagedTransactionFactory(dataSource);

		Environment environment = new Environment(SqlSessionFactoryBean.class.getSimpleName(), transactionFactory, dataSource);
		configuration.setEnvironment(environment);

		if (!ObjectUtils.isEmpty(this.mapperLocations)) {
			for (Resource mapperLocation : this.mapperLocations) {
				if (mapperLocation == null) {
					continue;
				}
				// this block is a workaround for issue
				// http://code.google.com/p/mybatis/issues/detail?id=235
				// when running MyBatis 3.0.4. But not always works.
				// Not needed in 3.0.5 and above.
				String path;
				if (mapperLocation instanceof ClassPathResource) {
					path = ((ClassPathResource) mapperLocation).getPath();
				}
				else {
					path = mapperLocation.toString();
				}

				try {
					XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(mapperLocation.getInputStream(), configuration, path, configuration.getSqlFragments());
					xmlMapperBuilder.parse();
				}
				catch (Exception e) {
					throw new NestedIOException("Failed to parse mapping resource: '" + mapperLocation + "'", e);
				}
				finally {
					ErrorContext.instance().reset();
				}

				if (this.logger.isDebugEnabled()) {
					this.logger.debug("Parsed mapper file: '" + mapperLocation + "'");
				}
			}
		}
		else {
			if (this.logger.isDebugEnabled()) {
				this.logger.debug("Property 'mapperLocations' was not specified or no matching resources found");
			}
		}

		return new SqlSessionFactoryBuilder().build(configuration);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
