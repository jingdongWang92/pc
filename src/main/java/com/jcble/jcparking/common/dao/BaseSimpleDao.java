package com.jcble.jcparking.common.dao;

import com.jcble.jcparking.common.model.BaseDto;

/**
 * The Interface BaseSimpleDao. 
 * @author Jingdong Wang
 * 
 */
public interface BaseSimpleDao {

	/**
	 * 通过唯一键（主键或唯一索引）插入一条BaseDto子类记录.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @throws Exception
	 *             the exception
	 * @version
	 */
	public <T extends BaseDto> void insert(T dto) throws Exception;

	/**
	 * 通过唯一键（主键或唯一索引）删除一条BaseDto子类记录.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @throws Exception
	 *             the exception
	 * @version
	 */
	public <T extends BaseDto> void delete(T dto) throws Exception;

	/**
	 * 通过唯一键（主键或唯一索引）更新一条BaseDto子类记录.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @throws Exception
	 *             the exception
	 * @version
	 */
	public <T extends BaseDto> void update(T dto) throws Exception;

	/**
	 * 通过唯一键（主键或唯一索引）查询一条BaseDto子类记录.
	 * 
	 * @param <T>
	 *            the generic type
	 * @param dto
	 *            the dto
	 * @return List
	 * @throws Exception
	 *             the exception
	 * @return
	 */
	public <T extends BaseDto> T select(T dto) throws Exception;
}
