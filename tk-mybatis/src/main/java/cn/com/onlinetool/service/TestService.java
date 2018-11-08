package cn.com.onlinetool.service;

import cn.com.onlinetool.common.base.BaseService;
import cn.com.onlinetool.dto.TestDTO;
import cn.com.onlinetool.entity.Test;
import cn.com.onlinetool.mapper.TestMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class TestService extends BaseService<TestMapper, Test, TestDTO> {

}
