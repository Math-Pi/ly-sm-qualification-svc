package com.ly.cloud.service.openapi.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ly.cloud.common.exception.NHExpHandleUtils;
import com.ly.cloud.mapper.base.ZgdyPOMapper;
import com.ly.cloud.mapper.base.ZgxZgPOMapper;
import com.ly.cloud.service.openapi.OpenAPIService;

@Service
public class OpenAPIServiceImpl implements OpenAPIService {
	
	private static final Logger log = LoggerFactory.getLogger(OpenAPIServiceImpl.class);
	@Autowired
	private ZgdyPOMapper zgdyPOMapper;
	@Autowired
	private ZgxZgPOMapper zgxZgPOMapper;
	
	@Transactional
	@Override
	public Integer deleteQua(String quaId) {
		try {
			zgxZgPOMapper.deleteByZgid(quaId);
			return zgdyPOMapper.deleteById(quaId);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}
	
	@Transactional
	@Override
	public Integer deleteMultiQua(List<String> quaIds) {
		try {
			if (quaIds.size() > 0) {
				zgxZgPOMapper.deleteByZgids(quaIds);
				return zgdyPOMapper.deleteBatchIds(quaIds);
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			NHExpHandleUtils.throwesException(e);
		}
		return null;
	}

}
