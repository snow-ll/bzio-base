package org.bzio.common.redis.service;

import org.bzio.common.core.util.BeanUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * redis Hash操作实现
 *
 * @author: snow
 */
@Component
public class HashRedisService {

    @Resource
    RedisTemplate redisTemplate;

    /**
     * 添加缓存基本的对象
     *
     * @param key
     * @param t
     * @param <T>
     */
    public <T> void set(final String key, final T t) {
        set(key, BeanUtil.beanToMap(t));
    }

    /**
     * 添加缓存基本的对象
     *
     * @param key   键值
     * @param dataMap 对象map集合
     */
    public <T> void set(final String key, final Map<String, T> dataMap) {
        redisTemplate.opsForHash().putAll(key, dataMap);
    }
}
