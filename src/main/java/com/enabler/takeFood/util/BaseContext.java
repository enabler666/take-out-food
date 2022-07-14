package com.enabler.takeFood.util;

/**
 * 基于ThreadLocal的工具类
 * 用于同线程（处理同一用户的）的数据共享
 * @author Enabler
 */
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();

    /**
     * 在本线程中设置一个id
     * @param id
     */
    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    /**
     * 在本线程中取出设置的id
     * @return
     */
    public static Long getCurrentId() {
        return threadLocal.get();
    }
}
