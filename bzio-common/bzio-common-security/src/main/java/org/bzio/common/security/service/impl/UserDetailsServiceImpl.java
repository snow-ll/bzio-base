package org.bzio.common.security.service.impl;

import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.ServletUtil;
import org.bzio.common.security.entity.LoginUser;
import org.bzio.system.entity.SysUser;
import org.bzio.system.mapper.SysMenuMapper;
import org.bzio.system.mapper.SysRoleMapper;
import org.bzio.system.mapper.SysUserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDetailsService实现类
 * 自定义用户加载
 *
 * @author: snow
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    SysRoleMapper sysRoleMapper;
    @Resource
    SysMenuMapper sysMenuMapper;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUser user = sysUserMapper.queryByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("未查询到相关用户信息！");
        }
        return new LoginUser(user.getUserId(),
                             user.getUsername(),
                             user.getPassword(),
                             user.getNickname(),
                             DateUtil.getNowDateAccurateSecond(),
                             ServletUtil.getIpAddr(),
                             getAuthorities(user.getUserId()));
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

        // 根据用户查询菜单权限字符
        List<String> perms = sysMenuMapper.queryPermByUserId(userId);
        authorities.addAll(perms);

        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[0]));
    }
}
