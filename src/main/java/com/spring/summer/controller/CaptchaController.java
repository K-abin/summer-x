package com.spring.summer.controller;

import com.google.code.kaptcha.Producer;
import com.spring.summer.admin.common.AjaxResult;
import com.spring.summer.common.utils.Base64;
import com.spring.summer.common.utils.Constants;
import com.spring.summer.common.utils.RedisCache;
import com.spring.summer.common.utils.UuidToString;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Author CXB
 * @ClassName CaptchaController
 * @date 2022/8/13 16:53
 * @Description 验证码操纵处理
 */
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource
    private RedisCache redisCache;


    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response){

        UUID uuids = UUID.randomUUID();
        String uuid = UuidToString.UUIDToUtils(uuids);

        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
        String code = null;
        BufferedImage image = null;
        AjaxResult ajaxResult = AjaxResult.success();

        code = captchaProducer.createText();
        image = captchaProducer.createImage(code);
        System.err.println("验证码内容code："+code+"------------------------------");
        //缓存验证码
        redisCache.setCacheObject(verifyKey,code,Constants.EXPIRATION, TimeUnit.MINUTES);
        FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image,"jpg",outputStream);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }
        ajaxResult.put("uuid",uuid);
        ajaxResult.put("img", Base64.encode(outputStream.toByteArray()));
        System.err.println("验证码获取成功");
        return ajaxResult;
    }

}
