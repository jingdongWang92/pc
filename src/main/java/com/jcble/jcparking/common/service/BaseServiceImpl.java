package com.jcble.jcparking.common.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcble.jcparking.common.dao.BaseSimpleDao;
import com.jcble.jcparking.common.model.BaseDto;

public abstract class BaseServiceImpl extends AbstractBaseService implements BaseService {
	/** The logger. */
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected abstract BaseSimpleDao getDao();

	@Override
	public <T extends BaseDto> void insert(T dto) throws Exception {
		try {
			getDao().insert(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public <T extends BaseDto> void delete(T dto) throws Exception {
		try {
			getDao().delete(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public <T extends BaseDto> void update(T dto) throws Exception {
		try {
			getDao().update(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public <T extends BaseDto> T select(T dto) throws Exception {
		try {
			return getDao().select(dto);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw e;
		}
	}

}
