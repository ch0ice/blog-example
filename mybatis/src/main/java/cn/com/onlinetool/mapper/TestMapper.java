package cn.com.onlinetool.mapper;

import cn.com.onlinetool.entity.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}