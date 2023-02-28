package org.bzio.system.service.impl;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysConfig;
import org.bzio.system.mapper.SysConfigMapper;
import org.bzio.system.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysConfigServiceImpl extends BaseServiceImpl implements SysConfigService {

    @Resource
    SysConfigMapper sysConfigMapper;

    @Override
    public SysConfig queryInfo(String configId) {
        return sysConfigMapper.queryById(configId);
    }

    @Override
    public List<SysConfig> queryAll(SysConfig sysConfig) {
        return sysConfigMapper.queryAll(sysConfig);
    }

    @Override
    public int saveConfig(SysConfig sysConfig) {
        // 获取登录人信息
        String userName = AuthUtil.getUserName();
        String nickName = AuthUtil.getNickName();

        if (StringUtil.isEmpty(sysConfig.getConfigId())) {
            sysConfig.setConfigId(IdUtil.simpleUUID());
            sysConfig.setCreateBy(userName);
            sysConfig.setCreateName(nickName);
            sysConfig.setCreateDate(DateUtil.getNowDate());
            sysConfig.setUpdateBy(userName);
            sysConfig.setUpdateName(nickName);
            sysConfig.setUpdateDate(DateUtil.getNowDate());
            return sysConfigMapper.insert(sysConfig);
        } else {
            SysConfig newConfig = sysConfigMapper.queryById(sysConfig.getConfigId());
            if (newConfig == null) throw new BaseException("未查询到系统配置信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysConfig, newConfig);
            newConfig.setUpdateBy(userName);
            newConfig.setUpdateName(nickName);
            newConfig.setUpdateDate(DateUtil.getNowDate());
            return sysConfigMapper.update(newConfig);
        }
    }

    @Override
    public int deleteConfig(String configId) {
        return sysConfigMapper.deleteById(configId);
    }
}
