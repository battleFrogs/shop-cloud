package org.example.common.core.context;

import org.apache.commons.lang3.StringUtils;
import org.example.common.core.utils.JwtUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 获取当前线程变量中的 用户id、用户名称、Token等信息
 * 注意： 必须在网关通过请求头的方法传入，同时在HeaderInterceptor拦截器设置值。 否则这里无法获取
 *
 * @author ruoyi
 */
public class SecurityContextHolder {
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(String key, Object value) {
        Map<String, Object> map = getLocalMap();
        map.put(key, value == null ? StringUtils.EMPTY : value);
    }

    public static String get(String key) {
        Map<String, Object> map = getLocalMap();
        return (String) (map.getOrDefault(key, StringUtils.EMPTY));
    }

    public static Map<String, Object> getLocalMap() {
        Map<String, Object> map = THREAD_LOCAL.get();
        if (map == null) {
            map = new ConcurrentHashMap<String, Object>();
            THREAD_LOCAL.set(map);
        }
        return map;
    }

    public static void setLocalMap(Map<String, Object> threadLocalMap) {
        THREAD_LOCAL.set(threadLocalMap);
    }

    public static Long getUserId() {
        return Long.valueOf(get(JwtUtils.USE_ID));
    }

    public static void setUserId(String account) {
        set(JwtUtils.USE_ID, account);
    }

    public static String getUserName() {
        return get(JwtUtils.USE_NAME);
    }

    public static void setUserName(String username) {
        set(JwtUtils.USE_NAME, username);
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}
