package org.bzio.common.security.service.impl;

import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.SystemUtil;
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
import java.net.InetAddress;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserMapper.queryByUserName(username);
        return new LoginUser(user.getUserId(),
                             user.getUserName(),
                             user.getPassword(),
                             user.getNickName(),
                             DateUtil.getNowDate(),
                             SystemUtil.getLocalIp(),
                             getAuthorities(user.getUserId()));
    }

    /**
     * 获取权限字符
     *
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

        /**
         * 错误记录：
         * queryPermByUserId查询返回：SysMenu类型
         * toArray类型不同导致空指针
         */
        return AuthorityUtils.createAuthorityList(authorities.toArray(new String[authorities.size()]));
    }
}
