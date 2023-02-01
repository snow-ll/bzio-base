package org.bzio.auth.controller;

import org.bzio.auth.service.AuthService;
import org.bzio.common.core.config.BaseConstant;
import org.bzio.common.core.util.AesUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.controller.BaseController;
import org.bzio.common.core.web.entity.AjaxResult;
import org.bzio.common.core.util.JwtUtil;
import org.bzio.common.core.config.AuthConfig;
import org.bzio.common.redis.service.StringRedisService;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.system.entity.SysUser;
import org.bzio.system.mapper.SysUserMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 认证业务控制层
 *
 * @author: snow
 */
@RestController
public class AuthController extends BaseController {

    @Resource
    JwtUtil jwtUtil;
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    AuthService authService;
    @Resource
    StringRedisService stringRedisService;

    /**
     * 用户登陆
     */
    @PostMapping("/login")
    public AjaxResult login(SysUser sysUser) {
        String key = "";
        try {
            // 登录过程
            LoginUser loginUser = authService.login(sysUser);
            // 生成token
            key = loginUser.getUserId() + "_" +  DateUtil.format(loginUser.getLoginTime(), BaseConstant.YYYYMMDDHHMMSS);
            String token = jwtUtil.generateToken(sysUser.getUserName());
            // redis缓存token,
            stringRedisService.set(AuthConfig.prefix + key, token, AuthConfig.expiration, TimeUnit.MILLISECONDS);
            logger.info("登录成功！");
            // 修改最后登录的信息
            sysUserMapper.updateLoginInfo(loginUser.getIpaddr(), loginUser.getLoginTime(), loginUser.getUserId());
        }catch (Exception e) {
            stringRedisService.delete(AuthConfig.prefix + key);
            logger.error("登录失败，异常信息：" + e);
            return AjaxResult.error("登录失败！");
        }
        return AjaxResult.success("登录成功！", AesUtil.encrypt(key, AuthConfig.aesKey));
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public AjaxResult register(SysUser sysUser) {
        int result = authService.register(sysUser);
        if (result != 1)
            return AjaxResult.error("注册失败！");

        return AjaxResult.success("注册成功！");
    }

    /**
     * 修改密码
     */
    @PostMapping("/updatePassword")
    public AjaxResult updatePassword(String userName, String password) {
        int result = authService.updatePassword(userName, password);
        if (result != 1)
            return AjaxResult.error("修改密码失败！");

        return AjaxResult.success("修改密码成功！");
    }

    /**
     * 登出
     */
    @PostMapping("/logOut")
    public AjaxResult logOut(HttpServletRequest request) {
        String key = request.getHeader(AuthConfig.header);
        // 清楚缓存中的token
        boolean f = stringRedisService.delete(AuthConfig.prefix + AesUtil.decrypt(key, AuthConfig.aesKey));
        // 清空spring security登录信息
        SecurityContextHolder.clearContext();
        if (f)
            return AjaxResult.error("登出成功！");

        return AjaxResult.success("登出失败！");
    }

    /**
     * 判断用户是否已经登录
     */
    @PostMapping("/isLogin")
    public AjaxResult isLogin(String userName) {
        SysUser user = sysUserMapper.queryByUserName(userName);
        String key = AuthConfig.prefix + user.getUserId() + "_" + DateUtil.format(user.getLoginDate(), BaseConstant.YYYYMMDDHHMMSS);
        String token = stringRedisService.get(key);
        return AjaxResult.success(StringUtil.isNotEmpty(token));
    }

    /**
     * 刷新token
     */
    @PostMapping("/refreshToken")
    public AjaxResult refreshToken(String userName) {
        SysUser user = sysUserMapper.queryByUserName(userName);
        String key = AuthConfig.prefix + user.getUserId() + "_" + DateUtil.format(user.getLoginDate(), BaseConstant.YYYYMMDDHHMMSS);
        String token = stringRedisService.get(key);

        // 判断token是否合法
        if (!jwtUtil.validateToken(token, userName))
            return AjaxResult.error("token不合法！");

        // 判断token是否有必要刷新
        Date expiredDate = jwtUtil.getExpiredDateFromToken(token);
        Date now = DateUtil.getNowDate();
        logger.info("token有效期至：" + DateUtil.format(expiredDate, BaseConstant.YYYY_MM_DD_HH_MM_SS));
        if (!DateUtil.belongCalendar(now, new Date(expiredDate.getTime() - 1000 * 60), expiredDate))
            return AjaxResult.error("token在有效期内，无需刷新");

         // 生成新的token
        String newToken = jwtUtil.refreshToken(token, userName);
        // 新的token存入redis
        stringRedisService.set(key, newToken, AuthConfig.expiration, TimeUnit.MILLISECONDS);
        return AjaxResult.success("刷新成功");
    }
}