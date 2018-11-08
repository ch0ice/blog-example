package cn.com.onlinetool.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanCopier;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * User: Kyll
 * Date: 2018-07-13 14:16
 */
public class BeanUtil {
	private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);
	private static final Map<String, BeanCopier> BEAN_COPIER_MAP = new HashMap<>();

	public static void copy(Object dest, Object orig) {
		String beanKey = dest.getClass().getName() + "_" + orig.getClass().getName();

		BeanCopier copier;
		if (BEAN_COPIER_MAP.containsKey(beanKey)) {
			copier = BEAN_COPIER_MAP.get(beanKey);
		} else {
			copier = BeanCopier.create(orig.getClass(), dest.getClass(), false);
			BEAN_COPIER_MAP.put(beanKey, copier);
		}

		copier.copy(orig, dest, null);
	}

	public static Map<String, Object> beanToMap(Object object) {
		Map<String, Object> map = new HashMap<>();
		Arrays.stream(object.getClass().getDeclaredFields()).forEach(field -> {
			if (field.getType() != Logger.class) {
				map.put(field.getName(), getFieldValue(object, field));
			}
		});
		return map;
	}

	public static <T> T newInstance(Class<T> clazz) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			log.error("实例化对象失败", e);
		}
		return t;
	}

	public static Object getFieldValue(Object object, Field field) {
		Object value = null;

		if (field != null) {
			field.setAccessible(true);

			try {
				value = field.get(object);
			} catch (IllegalAccessException e) {
				log.error("获取对象中的属性值失败", e);
			}
		}

		return value;
	}
}
