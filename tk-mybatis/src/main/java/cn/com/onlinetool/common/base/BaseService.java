package cn.com.onlinetool.common.base;

import cn.com.onlinetool.common.util.BeanUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public abstract class BaseService<M extends Mapper<E>, E> {
    @Autowired
    protected M mapper;

    public E findOne(E entity) {
        return mapper.selectOne(entity);
    }

    public E findById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }

    public List<E> find(E entity) {
        return mapper.select(entity);
    }

    public List<E> find() {
        return mapper.selectAll();
    }

    public int count(E entity) {
        return mapper.selectCount(entity);
    }

    public BasePageResult<E> findPage(E d, int pageNo, int pageSize) {
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

        BasePageResult<E> pageResult = new BasePageResult<>();
        pageResult.setPageNo(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setPageData(mapper.selectByExample(example));
        pageResult.setTotal(result.getTotal());
        pageResult.setPages(result.getPages());
        return pageResult;
    }

    @Transactional
    public E insert(E e) {
        mapper.insertSelective(e);
        return e;
    }

    @Transactional
    public List<E> insertAll(List<E> dList){
        dList.forEach(d -> {
            mapper.insertSelective(d);
        });
        return dList;
    }

    @Transactional
    public int updateById(E e) {
        return mapper.updateByPrimaryKeySelective(e);
    }

    @Transactional
    public void delete(E e) {
        mapper.delete(e);
    }

    @Transactional
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }


    @SuppressWarnings("unchecked")
    private Class<E> getEntityClass() {
        return (Class<E>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }


}
