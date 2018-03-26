package com.jcble.jcparking.common.dto.request;

import java.io.File;
import java.io.Serializable;

public class BaseReqDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2049763411161148053L;

	/**
	 * 签名信息
	 */
	private String sign;

	/**
	 * 时间戳
	 */
	private String timestamp;

	// 上传文件
	private File file;
	
	// 文件名称
	private String fileName;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


}
