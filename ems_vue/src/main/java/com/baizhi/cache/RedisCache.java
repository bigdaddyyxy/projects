package com.baizhi.cache;

import com.baizhi.util.ApplicationContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Slf4j
public class RedisCache implements Cache {

    private String id;


    public RedisCache(String id){
        log.info("当前缓存的id:[{}]",id);
        this.id = id;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override  //放入redis缓存
    public void putObject(Object key, Object value) {
        log.info("key:[{}]",key);
        log.info("value:[{}]",value);
        getRedisTemplate().opsForHash().put(id,key.toString(),value);


    }

    @Override  //从redis缓存获取
    public Object getObject(Object key) {
        log.info("key:[{}]",key.toString());
        return getRedisTemplate().opsForHash().get(id,key.toString());
    }

    @Override
    public Object removeObject(Object o) {
        return null;
    }

    @Override
    public void clear() {
        log.info("清除缓存");
        getRedisTemplate().delete(id);
    }

    @Override
    public int getSize() {
        return getRedisTemplate().opsForHash().size(id).intValue();
    }

    //封装获取redisTemplate方法
    public RedisTemplate getRedisTemplate(){
        RedisTemplate redisTemplate = (RedisTemplate) ApplicationContextUtils.getBean("redisTemplate");
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
