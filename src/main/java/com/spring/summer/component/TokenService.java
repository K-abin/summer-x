package com.spring.summer.component;

import com.spring.summer.admin.common.LoginUser;
import com.spring.summer.common.utils.Constants;
import com.spring.summer.common.utils.RedisCache;
import com.spring.summer.common.utils.UuidToString;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author CXB
 * @ClassName TokenService
 * @date 2022/8/20 9:49
 * @Description TODO
 */
@Component
public class TokenService {

    @Resource
    private RedisCache redisCache;

//    令牌自定义标识
    @Value("${token.header}")
    private String header;

//    令牌密钥
    @Value("${token.secret}")
    private String secret;

//    令牌有效期
    @Value("${token.expireTime}")
    private int expireTime;

//    token 过期时间单位转换
    public static final long MILLIS_MINUTE = 60 * 1000;





    /**
     * 创建token
     *
     * @param loginUser
     * @return
     */
    public String create(LoginUser loginUser) {
        String uuidFast = UuidToString.UUIDFast();
        loginUser.setToken(uuidFast);
        refreshToken(loginUser);

        HashMap<String, Object> claims = new HashMap<>();
        claims.put(Constants.LONG_TOKEN_KEY,uuidFast);
        return createToken(claims);
    }

    /**
     * 从数据声明成token
     * 算法加密 SignatureAlgorithm.HS384
     * @param claims = LONG_TOKEN_KEY,uuidFast
     * @return
     */
    private String createToken(Map<String,Object> claims) {
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS384,secret).compact();
        return token;
    }


    /**
     * 延长了token的时间，切保证token的key还是登录信息里面的那个key和value
     * 刷新token时间
     * @param loginUser
     */
    public void refreshToken(LoginUser loginUser){
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(loginUser.getExpireTime()*MILLIS_MINUTE);

        String userTokenKey = getTokenKey(loginUser.getToken());
        redisCache.setCacheObject(userTokenKey,loginUser,expireTime, TimeUnit.MINUTES);
    }

    /**
     * 生成tokenkey的key
     * @param uuid
     * @return
     */
    private String getTokenKey(String uuid){
        return Constants.CAPTCHA_CODE_KEY+uuid;
    }

    /**
     * 获取登录用户的token
     * @param request
     * @return 用户信息
     */
    public LoginUser getLoginUser(HttpServletRequest request){
//          获取登录瀛湖携带的token
        String headerToken = getToken(request);
        if (!(headerToken.equals("")||headerToken.equals(null))){
            Claims claims = parseToken(headerToken);
//           解析对应的权限以及用户信息
            String uuid = (String) claims.get(Constants.LONG_TOKEN_KEY);
            String tokenKey = getTokenKey(uuid);
//           根据tokenKey来获取redis中的token
            LoginUser user = redisCache.getCacheObject(tokenKey);
            return user;
        }
        return null;
    }

    /**
     * 解析token
     * 设置签名密钥 setSigningKey
     * @param token
     * @return
     */
    private Claims parseToken(String token){
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     *  去掉令牌前缀“Bearer "
     * @param request
     * @return
     */
    private String getToken(HttpServletRequest request) {
        String headerToken = request.getHeader(this.header);
        if (!(headerToken.equals("")||headerToken.equals(null)) && headerToken.startsWith(Constants.TOKEN_PREFIX)){
            headerToken.replace(Constants.TOKEN_PREFIX,"");
        }
        return headerToken;
    }
}
