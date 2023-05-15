package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.mapper.SysUserMapper;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.service.SysUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: snow
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl implements SysUserService {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    SysUserMapper sysUserMapper; 

    /**
     * 根据用户名查询用户详情
     */
    @Override
    public SysUser queryInfo(String username) {
        return sysUserMapper.queryByUsername(username);
    }

    /**
     * 根据条件查询用户列表
     */
    @Override
    public List<SysUser> queryAll(SysUserQo sysUser) {
        return sysUserMapper.queryAll(sysUser);
    }

    /**
     * 新增用户
     */
    @Override
    public int saveUser(SysUser sysUser) {
//        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();
        
        // 判断传入的id是否为空
        // 为空新增用户
        if (StringUtil.isEmpty(sysUser.getUserId())) {
            if (StringUtil.isNotNull(sysUserMapper.queryByUsername(sysUser.getUsername()))) 
                throw new UserException("用户名不能重复！");

            // 新增用户，密码加密
            sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
            
            sysUser.setUserId(IdUtil.simpleUUID());
            sysUser.setCreateBy(username);
            sysUser.setCreateName(nickname);
            sysUser.setCreateDate(DateUtil.getNowDate());
            sysUser.setUpdateBy(username);
            sysUser.setUpdateName(nickname);
            sysUser.setUpdateDate(DateUtil.getNowDate());
            sysUser.setDelFlag(0);
            return sysUserMapper.insert(sysUser);
        }else {
            SysUser newUser = sysUserMapper.queryByUserId(sysUser.getUserId());
            if (newUser == null) throw new UserException("未查询到部门信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysUser, newUser);
            newUser.setUpdateBy(username);
            newUser.setUpdateName(nickname);
            newUser.setUpdateDate(DateUtil.getNowDate());
            return sysUserMapper.update(newUser);
        }
    }

    /**
     * 根据用户名删除用户
     */
    @Override
    public int deleteUser(String username) {
        return sysUserMapper.deleteByUsername(username);
    }

    @Override
    public int changeStatus(String userId, Integer status) {
        return sysUserMapper.changeStatus(userId, status);
    }
}
