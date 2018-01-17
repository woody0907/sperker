package com.woody;

/**
 * Created with IntelliJ IDEA.
 * User: woody
 * Date: 18-1-17
 * Time: 上午9:26
 * To change this template use File | Settings | File Templates.
 */

public interface IRedisService {
    public boolean set(String key, String value);
    public String get(String key);
}
