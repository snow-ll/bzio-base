package org.bzio.common.redis.service;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

/**
 * redis String操作实现
 *
 * @author: snow
 */
@Component
public class StringRedisService {

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 添加缓存基本的对象
     *
     * @param key   键值
     * @param value 缓存的值
     */
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 添加缓存基本的对象
     *
     * @param key      键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public <T> void set(final String key, final T value, final Long timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置有效时间
     *
     * @param key     键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public boolean expire(final String key, final long timeout, final TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象
     *
     * @param key 键值
     * @return 缓存键值对应的数据
     */
    public <T> T get(final String key) {
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 删除单个对象
     *
     * @param key 删除对象的key
     */
    public boolean delete(final String key) {
        return redisTemplate.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param collection 多个对象集合
     * @return
     */
    public long delete(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 判断key是否过期
     */
    public boolean isExpire(String key) {
        return exists(key) && redisTemplate.opsForValue().getOperations().getExpire(key) > 0;
    }
}
