package com.jcble.jcparking.common.mybatis.interceptor;

import java.lang.reflect.Field;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.jcble.jcparking.common.model.BaseDto;

/**
 * 
 * @author Jingdong Wang
 * 
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class,
		RowBounds.class, ResultHandler.class }) })
public class MyBatisCheckEmptyBeforeExecuteInterceptor implements Interceptor {
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		Object parameter = invocation.getArgs()[1];
		if (parameter != null && parameter instanceof BaseDto) {
			Field[] fields = parameter.getClass().getDeclaredFields();
			if (fields != null && fields.length > 0) {
				for (Field f : fields) {
					if (f.getType() == String.class) {
						f.setAccessible(true);
						Object obj = f.get(parameter);
						if (obj != null && StringUtils.isNotBlank(obj.toString())) {
							f.set(parameter, obj.toString().trim());
						}
					}
				}
			}
		}

		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		if (target instanceof Executor) {
			return Plugin.wrap(target, this);
		} else {
			return target;
		}
	}

	@Override
	public void setProperties(Properties properties) {
	}
}
