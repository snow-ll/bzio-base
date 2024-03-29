package org.bzio.service.impl;

import com.google.code.kaptcha.Producer;
import org.bzio.common.core.exception.system.code.CodeException;
import org.bzio.common.core.util.Base64Util;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.redis.service.StringRedisService;
import org.bzio.config.CaptchaProperties;
import org.bzio.service.ValidateCodeService;
import org.springframework.stereotype.Service;
import org.springframework.util.FastByteArrayOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 验证码操作
 *
 * @author snow
 */
@Service
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private CaptchaProperties captchaProperties;

    @Resource
    private StringRedisService stringRedisService;

    /**
     * 生成验证码
     */
    @Override
    public AjaxResult createCaptcha() {
        AjaxResult ajax = AjaxResult.success();
        Map<String, Object> result = new HashMap<>();

        boolean captchaEnabled = captchaProperties.getEnabled();
        result.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            ajax.setData(result);
            return ajax;
        }

        // 保存验证码信息
        String uuid = IdUtil.simpleUUID();
        String verifyKey = "captcha_codes:" + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;

        String captchaType = captchaProperties.getType();
        // 生成验证码
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        stringRedisService.set(verifyKey, code, 1L, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            assert image != null;
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        result.put("uuid", uuid);
        result.put("capStr", capStr);
        result.put("img", Base64Util.encode(os.toByteArray()));

        ajax.setData(result);
        return ajax;
    }

    /**
     * 校验验证码
     */
    @Override
    public void checkCaptcha(String code, String uuid) throws CodeException {
        if (StringUtil.isEmpty(code)) {
                throw new CodeException("验证码不能为空");
        }
        if (StringUtil.isEmpty(uuid)) {
            throw new CodeException("验证码已失效");
        }
        String verifyKey = "captcha_codes:" + uuid;
        String captcha = stringRedisService.get(verifyKey);
        stringRedisService.delete(verifyKey);

        if (!code.equalsIgnoreCase(captcha)) {
            throw new CodeException("验证码错误");
        }
    }
}
