package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysUser;
import org.bzio.common.security.entity.SysUserDept;
import org.bzio.common.security.mapper.SysUserDeptMapper;
import org.bzio.common.security.mapper.SysUserMapper;
import org.bzio.common.security.qo.SysUserQo;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.common.security.vo.SysUserVo;
import org.bzio.system.service.SysUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysUserServiceImpl extends BaseServiceImpl implements SysUserService {

    @Resource
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Resource
    SysUserMapper sysUserMapper;
    @Resource
    SysUserDeptMapper sysUserDeptMapper;

    /**
     * 根据用户名查询用户详情
     */
    @Override
    public SysUser queryInfo(String userId) {
        SysUser sysUser = sysUserMapper.queryByUserId(userId);
        SysUserDept sysUserDept = sysUserDeptMapper.queryByUserId(userId);
        if (StringUtil.isNotNull(sysUserDept)) {
            sysUser.setDeptId(sysUserDept.getDeptId());
        }
        return sysUser;
    }

    /**
     * 根据条件查询用户列表
     */
    @Override
    public List<SysUserVo> queryAll(SysUserQo sysUser) {
        return sysUserMapper.queryAll(sysUser);
    }

    /**
     * 新增用户
     */
    @Override
    public int saveUser(SysUser sysUser) {
        int result = 0;
        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();

        SysUser newUser = sysUserMapper.queryByUserId(sysUser.getUserId());
        // 判断传入的id是否为空
        // 为空新增用户
        if (StringUtil.isEmpty(sysUser.getUserId())) {
            if (StringUtil.isNotNull(sysUserMapper.queryByUsername(sysUser.getUsername()))) 
                throw new UserException("用户名不能重复！");

            // 新增用户，密码加密
            sysUser.setPassword(bCryptPasswordEncoder.encode(sysUser.getPassword()));
            
            sysUser.setUserId(IdUtil.snowflakeId());
            sysUser.setCreateBy(username);
            sysUser.setCreateName(nickname);
            sysUser.setCreateDate(DateUtil.getNowDate());
            sysUser.setUpdateBy(username);
            sysUser.setUpdateName(nickname);
            sysUser.setUpdateDate(DateUtil.getNowDate());
            sysUser.setDelFlag(0);
            result = sysUserMapper.insert(sysUser);
        }else {
            if (newUser == null) throw new UserException("未查询到部门信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysUser, newUser);
            newUser.setUpdateBy(username);
            newUser.setUpdateName(nickname);
            newUser.setUpdateDate(DateUtil.getNowDate());
            result = sysUserMapper.update(newUser);
        }
        
        if (result == 1) {
            // 用户部门处理
            SysUserDept sysUserDept = new SysUserDept();
            sysUserDept.setId(IdUtil.snowflakeId());
            sysUserDept.setUserId(sysUser.getUserId());
            sysUserDept.setDeptId(sysUser.getDeptId());
            if (StringUtil.isNotNull(sysUser.getDeptId())) {
                sysUserDeptMapper.delete(sysUserDept);
                result = sysUserDeptMapper.insert(sysUserDept);
            }
        }
        return result;
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
