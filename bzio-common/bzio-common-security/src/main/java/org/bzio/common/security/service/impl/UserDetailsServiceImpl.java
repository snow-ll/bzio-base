package org.bzio.common.security.service.impl;

import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.ServletUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.mapper.SysMenuMapper;
import org.bzio.common.security.mapper.SysRoleMapper;
import org.bzio.common.security.mapper.SysUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * UserDetailsService实现类
 * 自定义用户加载
 *
 * @author: snow
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static String ADMIN = "admin";
    
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    SysRoleMapper sysRoleMapper;
    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserMapper.queryByUsername(username);
        // 第一次登录，时间添加进去
        if (StringUtil.isNotNull(user) && StringUtil.isNull(user.getLoginDate())) {
            user.setLoginDate(DateUtil.getNowDate());
        }
        if (user == null) {
            throw new UsernameNotFoundException("未查询到相关用户信息！");
        }
        return new LoginUser(user, getAuthorities(user.getUserId()));
    }

    /**
     * 获取权限字符
     * 自定义方式添加
     */
    private List<GrantedAuthority> getAuthorities(String userId) {
        List<String> authorities = new ArrayList<>();
        
        // 根据用户查询角色
        List<String> roles = sysRoleMapper.queryRoleByUserId(userId);
        for (String role : roles) {
            authorities.add("ROLE_" + role);
        }
        
        // 管理员权限
        if (roles.contains(ADMIN)) {
            userId = null;
        }

        // 根据用户查询菜单权限字符
        List<String> perms = sysMenuMapper.queryPermByUserId(userId);
        authorities.addAll(perms);

        authorities = authorities.stream().filter(item -> StringUtil.isNotEmpty(item)).collect(Collectors.toList());
        
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }
}
