package com.cn.ascept;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Set;

/**
 * @author CacheAspect
 * @time 2021/1/4-15:55
 */
@Configuration
@Aspect
public class CacheAspectHash {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;


    /*
    * redis   String  Hash(key,value)
    * String(KEY)  Hash(key,value)
    *
    * KEY:com.baizhi.serviceImpl.CategoryServiceImpl
    *                       key:方法名+实参  value:数据
    * */

    //配置添加缓存的切面  切所有以query开头的方法
    //@Around("execution(* com.baizhi.serviceImpl.*.query*(..) )")
    @Around("@annotation(com.cn.annotation.AddCache)")
    public Object AddCache(ProceedingJoinPoint proceedingJoinPoint){

        System.out.println("环绕通知：添加缓存 ");

        //redis  key:类的全限定名+方法名+实参    value:数据

        //序列化解决key乱码
        /*解决缓存乱码*/
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        //字符串拼接
        StringBuilder sb = new StringBuilder();

        //获取类的全限定名
        String className = proceedingJoinPoint.getTarget().getClass().getName();

        //获取方法名
        String methodName = proceedingJoinPoint.getSignature().getName();
        sb.append(methodName);

        //获取参数
        Object[] args = proceedingJoinPoint.getArgs();
        //遍历参数
        for (Object arg : args) {
            sb.append(arg);
        }

        //获取拼接的字符串
        String key = sb.toString();

        HashOperations hashOperations = redisTemplate.opsForHash();

        //根据key 查询是否存在
        //Boolean aBoolean = redisTemplate.hasKey(key);
        Boolean aBoolean = hashOperations.hasKey(className, key);

        Object result=null;
        //判断有没有缓存
        if(aBoolean){
            //   有  直接取出缓存返回结果  redis
            result = hashOperations.get(className,key);
        }else{
            //   没有  放行方法 查询数据库返回结果加入缓存
            try {
                //放行
                result = proceedingJoinPoint.proceed();
                //添加缓存
                hashOperations.put(className,key,result);
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
        return result;
    }

    //返回通知
    @AfterReturning("@annotation(com.baizhi.annotation.DelCache)")
    public void delCache(JoinPoint joinPoint){

        //获取类的全限定名
        String KEY = joinPoint.getTarget().getClass().getName();

        //清除缓存
        redisTemplate.delete(KEY);
    }

}
