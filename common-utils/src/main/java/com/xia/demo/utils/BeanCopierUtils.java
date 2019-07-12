package com.xia.demo.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.beans.BeanMap;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean复制工具
 * @author xiafb
 */
@Slf4j
public class BeanCopierUtils {

    /**
     * 使用缓存提高效率
     */
    private static final ConcurrentHashMap<String, BeanCopier> MAP_CACHES = new ConcurrentHashMap<>();

    /**
     * bean浅拷贝
     * @param source 源对象
     * @param target 目标类
     * @return 返回目标对象
     */
    public static <O, T> T copy(O source, Class<T> target) {
        return baseMapper(source, target);
    }

    /**
     * bean浅拷贝
     * @param source 源对象
     * @param target 目标类
     * @return 返回目标对象
     */
    private static <O, T> T baseMapper(O source, Class<T> target) {
        String baseKey = generateKey(source.getClass(), target);
        BeanCopier copier;
        if (!MAP_CACHES.containsKey(baseKey)) {
            copier = BeanCopier.create(source.getClass(), target, false);
            MAP_CACHES.put(baseKey, copier);
        } else {
            copier = MAP_CACHES.get(baseKey);
        }
        T instance = null;
        try {
            instance = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("创建对象异常" + e.getMessage());
        }
        copier.copy(source, instance, null);
        return instance;
    }

    /**
     * 生成缓存对象键
     * @param source 源类
     * @param target 目标类
     * @return 返回键
     */
    private static String generateKey(Class<?> source, Class<?> target) {
        return source.toString() + target.toString();
    }

    /**
     * bean浅拷贝并执行函数action
     * @param source 源对象
     * @param target 目标类
     * @param action 匿名函数
     * @return 目标实例
     */
    public static <O, T> T copy(O source, Class<T> target, IAction<T> action) {
        T instance = baseMapper(source, target);
        action.run(instance);
        return instance;
    }

    /**
     * 将bean装换为map
     * @param bean 源对象
     * @return Map
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key+"", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * 将map装换为javabean对象
     * @param map map
     * @param target 目标类
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, Class<T> target) {
        T instance = null;
        try {
            instance = target.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            log.error("创建对象异常" + e.getMessage());
        }
        BeanMap beanMap = BeanMap.create(instance);
        beanMap.putAll(map);
        return instance;
    }

    /**
     * 将map装换为javabean对象
     * @param map
     * @param bean
     * @return
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * 浅拷贝将List<T>转换为List<Map<String, Object>>
     * @param objList 源对象列表
     * @return List<Map<String, Object>>
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map;
            T bean;
            for (T t : objList) {
                bean = t;
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * 将List<Map<String,Object>>转换为List<T>
     * @param maps List<Map<String,Object>>
     * @param clazz List<T>
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map;
            T bean;
            for (Map<String, Object> map1 : maps) {
                map = map1;
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }
}