package com.jcble.jcparking.common.model.system;

/**
 * RabbitMq
 * 
 * @author Jingdong Wang
 * @date 2017年3月1日 下午3:39:16
 *
 */
public class RabbitMq {

	private String host;

	private Integer port;

	private String username;

	private String password;

	private String virtualHost;

	private String exchangeName;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVirtualHost() {
		return virtualHost;
	}

	public void setVirtualHost(String virtualHost) {
		this.virtualHost = virtualHost;
	}

	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}

}
