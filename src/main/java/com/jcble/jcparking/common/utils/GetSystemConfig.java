package com.jcble.jcparking.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcble.jcparking.common.model.system.RabbitMq;
import com.jcble.jcparking.common.service.system.SystemConfigService;

public final class GetSystemConfig {

    private static Logger logger = LoggerFactory.getLogger(GetSystemConfig.class);

    /**
     * Gets the system value.
     * 
     * @param paramname
     *            the key
     * @return the system value
     */
    public static String getValueByKey(String paramKey) {
        String value = null;
        try {
            SystemConfigService sysCfgParamService = (SystemConfigService) SpringBeanUtils
                    .getSpringBean("systemConfigServiceImpl");
            value = sysCfgParamService.getSysCfgParamValueByKey(paramKey);
        } catch (Exception e) {
        	e.printStackTrace();
        	logger.error("get system param failure.");
        }
        return value;
    }
    
    public static RabbitMq getRabbitParam() {
    	RabbitMq rabbit = new RabbitMq();
    	String host = GetSystemConfig.getValueByKey("rabbitmq.host");
		String port = GetSystemConfig.getValueByKey("rabbitmq.port");
		String username = GetSystemConfig.getValueByKey("rabbitmq.username");
		String password = GetSystemConfig.getValueByKey("rabbitmq.password");
		String virtualhost = GetSystemConfig.getValueByKey("rabbitmq.virtualhost");
		String exchangeName = GetSystemConfig.getValueByKey("rabbitmq.exchange");
		rabbit.setHost(host);
		rabbit.setPort(Integer.parseInt(port));
		rabbit.setVirtualHost(virtualhost);
		rabbit.setExchangeName(exchangeName);
		rabbit.setUsername(username);
		rabbit.setPassword(password);
    	return rabbit;
    }

}

