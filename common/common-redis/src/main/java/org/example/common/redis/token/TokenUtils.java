package org.example.common.redis.token;

import org.example.common.redis.utils.RedisUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TokenUtils {

    @Resource
    private RedisUtils redisUtils;
    // 30 分钟过期 token过期
    private final Long TOKEN_EXPIRE_TIME = 30 * 60L;
    // 剩余时间 < 15分钟就刷新
    private final Long TOKEN_REFRESH_TIME = 15 * 60 * 60L;
    // 8小时登录过期
    private final Long LOGIN_EXPIRE = 8 * 60 * 60L;
    private final String REDIS_TOKEN_KEY = "USER";


    /**
     * 验证令牌有效期，相差不足时间，自动刷新缓存
     */
    public void verifyToken(Long userId, LoginUser loginUser) {
        long expireTime = loginUser.getExpireTime();
        long currentTime = System.currentTimeMillis();
        String token = loginUser.getToken();
        if (expireTime - currentTime <= TOKEN_REFRESH_TIME) {
            refreshToken(userId, token);
        }
    }

    /**
     * 刷新令牌有效期
     *
     * @param userId 用户Id
     */
    public void refreshToken(Long userId, String token) {
        Long systemCurrentTimeMillis = System.currentTimeMillis();
        LoginUser loginUser = new LoginUser();
        loginUser.setLoginTime(systemCurrentTimeMillis);
        loginUser.setExpireTime(systemCurrentTimeMillis + TOKEN_EXPIRE_TIME);
        loginUser.setToken(token);
        redisUtils.set(REDIS_TOKEN_KEY + ":" + userId, loginUser, LOGIN_EXPIRE);
    }


    /**
     * 获取LoginUser
     *
     * @param userId 用户Id
     * @return loginUser
     */
    public LoginUser getLoginUser(Long userId) {
        return (LoginUser) redisUtils.get(REDIS_TOKEN_KEY + ":" + userId);
    }


    /**
     * 存在LoginUser
     *
     * @param userId 用户Id
     * @return loginUser
     */
    public boolean isExistLoginUser(Long userId) {
        return redisUtils.hasKey(REDIS_TOKEN_KEY + ":" + userId);
    }





}
