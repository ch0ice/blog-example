package cn.com.onlinetool.common.base;

import cn.com.onlinetool.common.CommonPageResult;
import cn.com.onlinetool.common.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class BaseService<M extends Mapper<E>, E, D> {
    @Autowired
    protected M mapper;

    public D get(E entity) {
        return toDto(mapper.selectOne(entity));
    }

    public D getById(Object id) {
        return toDto(mapper.selectByPrimaryKey(id));
    }

    public List<D> find() {
        return toDto(mapper.selectAll());
    }

    public List<D> find(E entity) {
        return toDto(mapper.select(entity));
    }

    public int count(E entity) {
        return mapper.selectCount(entity);
    }

    public CommonPageResult<D> findPage(D d, int pageNo, int pageSize) {
        Example example = new Example(getEntityClass());

        Map<String, Object> map = BeanUtil.beanToMap(d);

        if (map.size() > 0) {
            Example.Criteria criteria = example.createCriteria();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null && StringUtils.isNotBlank(value.toString())) {
                    criteria.andLike(entry.getKey(),   value.toString() + "%");
                }
            }
        }

        Page<Object> result = PageHelper.startPage(pageNo, pageSize);

        CommonPageResult<D> pageResult = new CommonPageResult<>();
        pageResult.setPageNo(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setPageData(toDto(mapper.selectByExample(example)));
        pageResult.setTotal(result.getTotal());
        pageResult.setPages(result.getPages());
        return pageResult;
    }

    @Transactional
    public D insert(D dto) {
        E entity = toEntity(dto);
        mapper.insertSelective(entity);
        return toDto(entity);
    }

    @Transactional
    public int updateById(D dto) {
        return mapper.updateByPrimaryKeySelective(toEntity(dto));
    }

    @Transactional
    public void delete(D dto) {
        mapper.delete(toEntity(dto));
    }

    @Transactional
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }

    protected E toEntity(D dto) {
        if (dto == null) {
            return null;
        }

        E entity = BeanUtil.newInstance(getEntityClass());
        BeanUtil.copy(entity, dto);
        return entity;
    }

    protected D toDto(E entity) {
        if (entity == null) {
            return null;
        }

        D dto = BeanUtil.newInstance(getDtoClass());
        BeanUtil.copy(dto, entity);
        return dto;
    }

    protected List<D> toDto(List<E> entityList) {
        if (entityList == null) {
            return null;
        }

        List<D> dtoList = new ArrayList<>();
        for (E entity : entityList) {
            dtoList.add(toDto(entity));
        }

        return dtoList;
    }

    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @SuppressWarnings("unchecked")
    private Class<D> getDtoClass() {
        return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[2];
    }
}
