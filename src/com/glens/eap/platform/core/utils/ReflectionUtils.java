package com.glens.eap.platform.core.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

public class ReflectionUtils {
	private static Logger logger = LoggerFactory
			.getLogger(ReflectionUtils.class);

	private ReflectionUtils() {
	}

	/**
	 * 直接读取对象属性值,无视private/protected修饰符,不经过getter函数.
	 */
	public static Object getFieldValue(final Object object,
			final String fieldName) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		Object result = null;
		try {
			result = field.get(object);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 直接设置对象属性值,无视private/protected修饰符,不经过setter函数.
	 */
	public static void setFieldValue(final Object object,
			final String fieldName, final Object value) {
		Field field = getDeclaredField(object, fieldName);

		if (field == null)
			throw new IllegalArgumentException("Could not find field ["
					+ fieldName + "] on target [" + object + "]");

		makeAccessible(field);

		try {
			field.set(object, value);
		} catch (IllegalAccessException e) {
			logger.error("不可能抛出的异常:{}", e.getMessage());
		}
	}

	/**
	 * 循环向上转型,获取对象的DeclaredField.
	 */
	protected static Field getDeclaredField(final Object object,
			final String fieldName) {
		Assert.notNull(object, "object不能为空");
		return getDeclaredField(object.getClass(), fieldName);
	}

	/**
	 * 循环向上转型,获取类的DeclaredField.
	 */
	@SuppressWarnings("unchecked")
	protected static Field getDeclaredField(final Class clazz,
			final String fieldName) {
		Assert.notNull(clazz, "clazz不能为空");
		Assert.hasText(fieldName, "fieldName");
		for (Class superClass = clazz; superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (NoSuchFieldException e) {
				// Field不在当前类定义,继续向上转型
			}
		}
		return null;
	}

	/**
	 * 强制转换fileld可访问.
	 */
	protected static void makeAccessible(final Field field) {
		if (!Modifier.isPublic(field.getModifiers())
				|| !Modifier.isPublic(field.getDeclaringClass().getModifiers())) {
			field.setAccessible(true);
		}
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends HibernateDao<User>
	 * 
	 * @param clazz
	 *            The class to introspect
	 * @return the first generic declaration, or Object.class if cannot be
	 *         determined
	 */
	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz) {
		return getSuperClassGenricType(clazz, 0);
	}

	/**
	 * 通过反射,获得定义Class时声明的父类的泛型参数的类型. 如public UserDao extends
	 * HibernateDao<User,Long>
	 * 
	 * @param clazz
	 *            clazz The class to introspect
	 * @param index
	 *            the Index of the generic ddeclaration,start from 0.
	 * @return the index generic declaration, or Object.class if cannot be
	 *         determined
	 */

	@SuppressWarnings("unchecked")
	public static Class getSuperClassGenricType(final Class clazz,
			final int index) {

		Type genType = clazz.getGenericSuperclass();

		if (!(genType instanceof ParameterizedType)) {
			logger.warn(clazz.getSimpleName()
					+ "'s superclass not ParameterizedType");
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			logger.warn("Index: " + index + ", Size of "
					+ clazz.getSimpleName() + "'s Parameterized Type: "
					+ params.length);
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			logger.warn(clazz.getSimpleName()
					+ " not set the actual class on superclass generic parameter");
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * 提取集合中的对象的属性,组合成List.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertityName
	 *            要提取的属性名.
	 */
	@SuppressWarnings("unchecked")
	public static List fetchElementPropertyToList(final Collection collection,
			final String propertyName) throws Exception {

		List list = new ArrayList();

		for (Object obj : collection) {
			list.add(PropertyUtils.getProperty(obj, propertyName));
		}

		return list;
	}

	/**
	 * 提取集合中的对象的属性,组合成由分割符分隔的字符串.
	 * 
	 * @param collection
	 *            来源集合.
	 * @param propertityName
	 *            要提取的属性名.
	 * @param separator
	 *            分隔符.
	 */
	@SuppressWarnings("unchecked")
	public static String fetchElementPropertyToString(
			final Collection collection, final String propertyName,
			final String separator) throws Exception {
		List list = fetchElementPropertyToList(collection, propertyName);
		return StringUtils.join(list.toArray(), separator);
	}

	/**
	 * 比较两个BEAN或MAP对象的值是否相等 如果是BEAN与MAP对象比较时MAP中的key值应与BEAN的属性值名称相同且字段数目要一致
	 * 
	 * @param source
	 * @param target
	 * @return
	 */
	public static boolean domainEquals(Object source, Object target) {
		if (source == null || target == null) {
			return false;
		}
		boolean rv = true;
		if (source instanceof Map) {
			rv = mapOfSrc(source, target, rv);
		} else {
			rv = classOfSrc(source, target, rv);
		}
		//logger.info("THE EQUALS RESULT IS " + rv);
		return rv;
	}

	/**
	 * 源目标为MAP类型时
	 * 
	 * @param source
	 * @param target
	 * @param rv
	 * @return
	 */
	private static boolean mapOfSrc(Object source, Object target, boolean rv) {
		HashMap<String, String> map = new HashMap<String, String>();
		map = (HashMap) source;
		for (String key : map.keySet()) {
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				if (tarMap.get(key) == null) {
					rv = false;
					break;
				}
				if (!map.get(key).equals(tarMap.get(key))) {
					rv = false;
					break;
				}
			} else {
				String tarValue = getClassValue(target, key) == null ? ""
						: getClassValue(target, key).toString();
				if (!tarValue.equals(map.get(key))) {
					rv = false;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 源目标为非MAP类型时
	 * 
	 * @param source
	 * @param target
	 * @param rv
	 * @return
	 */
	private static boolean classOfSrc(Object source, Object target, boolean rv) {
		Class<?> srcClass = source.getClass();
		Field[] fields = srcClass.getDeclaredFields();
		for (Field field : fields) {
			String nameKey = field.getName();
			if (target instanceof Map) {
				HashMap<String, String> tarMap = new HashMap<String, String>();
				tarMap = (HashMap) target;
				String srcValue = getClassValue(source, nameKey) == null ? ""
						: getClassValue(source, nameKey).toString();
				if (tarMap.get(nameKey) == null) {
					rv = false;
					break;
				}
				if (!tarMap.get(nameKey).equals(srcValue)) {
					rv = false;
					break;
				}
			} else {
				String srcValue = getClassValue(source, nameKey) == null ? ""
						: getClassValue(source, nameKey).toString();
				String tarValue = getClassValue(target, nameKey) == null ? ""
						: getClassValue(target, nameKey).toString();
				if (!srcValue.equals(tarValue)) {
					rv = false;
					break;
				}
			}
		}
		return rv;
	}

	/**
	 * 根据字段名称取值
	 * 
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getClassValue(Object obj, String fieldName) {
		if (obj == null) {
			return null;
		}
		try {
			Class beanClass = obj.getClass();
			Method[] ms = beanClass.getMethods();
			for (int i = 0; i < ms.length; i++) {
				// 非get方法不取
				if (!ms[i].getName().startsWith("get")) {
					continue;
				}
				Object objValue = null;
				try {
					objValue = ms[i].invoke(obj, new Object[] {});
				} catch (Exception e) {
					logger.info("反射取值出错：" + e.toString());
					continue;
				}
				if (objValue == null) {
					continue;
				}
				if (ms[i].getName().toUpperCase()
						.equals(fieldName.toUpperCase())
						|| ms[i].getName().substring(3).toUpperCase()
								.equals(fieldName.toUpperCase())) {
					return objValue;
				} else if (fieldName.toUpperCase().equals("SID")
						&& (ms[i].getName().toUpperCase().equals("ID") || ms[i]
								.getName().substring(3).toUpperCase()
								.equals("ID"))) {
					return objValue;
				}
			}
		} catch (Exception e) {
			// logger.info("取方法出错！" + e.toString());
		}
		return null;
	}
}
