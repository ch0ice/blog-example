package cn.com.onlinetool.service;

import cn.com.onlinetool.common.base.BaseService;
import cn.com.onlinetool.dto.Test1DTO;
import cn.com.onlinetool.entity.Test1;
import cn.com.onlinetool.mapper.Test1Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author choice
 * @description:
 * @date 2018/11/8 下午8:31
 *
 */
@Service
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class Test1Service extends BaseService<Test1Mapper, Test1, Test1DTO> {
}
