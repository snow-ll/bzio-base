package org.bzio.auth.controller;

import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.log.annotation.Log;
import org.bzio.auth.service.AuthService;
import org.bzio.common.core.config.BaseConstant;
import org.bzio.common.core.enums.BusinessType;
import org.bzio.common.core.util.AesUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.util.JwtUtil;
import org.bzio.common.redis.service.StringRedisService;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.util.AuthUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 认证业务控制层
 *
 * @author snow
 */
@RestController
public class AuthController extends BaseController {

    @Resource
    JwtUtil jwtUtil;
    @Resource
    AuthConfig authConfig;
    @Resource
    AuthService authService;
    @Resource
    StringRedisService stringRedisService;

    /**
     * 用户登陆
     */
    @Log(title = "登录", businessType = BusinessType.AUTH)
    @PostMapping("/login")
    public AjaxResult login(@RequestBody SysUser sysUser) {
        String key = "";
        LoginUser user = null;
        try {
            // 登录过程
            user = authService.login(sysUser);
            key = user.getKey();
        } catch (UserException e) {
            stringRedisService.delete(authConfig.getPrefix() + key);
            log.error("登录失败，异常信息：", e);
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            stringRedisService.delete(authConfig.getPrefix() + key);
            log.error("登录失败，异常信息：", e);
            return AjaxResult.error("登录失败！");
        }
        return AjaxResult.success("登录成功！", AesUtil.encrypt(key, authConfig.getAesKey()));
    }

    /**
     * 用户注册
     */
    @Log(title = "注册", businessType = BusinessType.AUTH)
    @PostMapping("/register")
    public AjaxResult register(@RequestBody SysUser sysUser) {
        int result = authService.register(sysUser);
        if (result != 1)
            return AjaxResult.error("注册失败！");

        return AjaxResult.success("注册成功！");
    }

    /**
     * 修改密码
     */
    @Log(title = "修改用户密码", businessType = BusinessType.AUTH)
    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(String username, String password, String newPassword) {
        int result = authService.updatePassword(username, password, newPassword);
        if (result != 1)
            return AjaxResult.error("修改密码失败！");

        return AjaxResult.success("修改密码成功！");
    }

    /**
     * 登出
     */
    @Log(title = "注销用户", businessType = BusinessType.AUTH)
    @PostMapping("/logOut")
    public AjaxResult logOut(HttpServletRequest request) {
        String key = request.getHeader(authConfig.getHeader());
        // 清楚缓存中的token
        boolean f = stringRedisService.delete(authConfig.getPrefix() + AesUtil.decrypt(key, authConfig.getAesKey()));

        if (f) {
            return AjaxResult.success("登出成功！");
        }
        return AjaxResult.error("登出失败！");
    }

    /**
     * 判断用户是否已经登录
     */
    @PostMapping("/isLogin")
    public AjaxResult isLogin(String username) {
        return AjaxResult.success(authService.isLogin(username));
    }

    /**
     * 强制注销
     */
    @Log(title = "强制注销", businessType = BusinessType.AUTH)
    @PostMapping("/force")
    public AjaxResult force(String username, String password) {
        return AjaxResult.toAjax(authService.force(username, password));
    }

    /**
     * 刷新token
     */
    @Log(title = "刷新token", businessType = BusinessType.AUTH)
    @PostMapping("/refreshToken")
    public AjaxResult refreshToken() {
        String username = AuthUtil.getUsername();
        // 获取token
        String key = authService.generateKey(username);
        log.info("生成key：" + key);
        String token = stringRedisService.get(key);

        // 判断token是否合法
        if (!jwtUtil.validateToken(token, username))
            return AjaxResult.error("token不合法！");

        // 判断token是否有必要刷新
        Date expiredDate = jwtUtil.getExpiredDateFromToken(token);
        Date now = DateUtil.getNowDate();
        log.info("token有效期至：{}", DateUtil.format(expiredDate, BaseConstant.YYYY_MM_DD_HH_MM_SS));
        // 有效时间段内刷新
        if (!DateUtil.belongCalendar(now, new Date(expiredDate.getTime() - 1000 * 30), expiredDate))
            return AjaxResult.error("token在有效期内，无需刷新");

         // 生成新的token
        String newToken = jwtUtil.refreshToken(token);
        // 新的token存入redis
        stringRedisService.set(key, newToken, authConfig.getExpiration(), TimeUnit.MILLISECONDS);

        return AjaxResult.success("刷新成功");
    }

    /**
     * 获取登录用户信息
     */
    @GetMapping("/getInfo")
    public AjaxResult getInfo() {
        return AjaxResult.success(authService.getInfo(AuthUtil.getUserId()));
    }
    
    /**
     * 获取路由信息
     */
    @GetMapping("/getRouter")
    public AjaxResult getRouter() {
        return AjaxResult.success(authService.getRouter());
    }
}
