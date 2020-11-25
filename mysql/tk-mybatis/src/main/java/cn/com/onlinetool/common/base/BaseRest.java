package cn.com.onlinetool.common.base;

import cn.com.onlinetool.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;

/**
 * User: Kyll
 * Date: 2018-07-13 14:27
 */
public abstract class BaseRest<S extends BaseService, D> {

	@Autowired
	protected S myService;
	/**
	 * 页面VM转DTO
	 * @param o
	 * @return
	 */
	protected D toDto(Object o){
		D dto = BeanUtil.newInstance(getDtoClass());
		BeanUtil.copy(dto, o);
		return dto;
	}
	private Class<D> getDtoClass() {
		return (Class<D>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
	}
}
