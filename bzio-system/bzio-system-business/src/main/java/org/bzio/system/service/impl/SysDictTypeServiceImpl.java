package org.bzio.system.service.impl;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysDictType;
import org.bzio.system.mapper.SysDictDataMapper;
import org.bzio.system.mapper.SysDictTypeMapper;
import org.bzio.system.service.SysDictTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl implements SysDictTypeService {

    @Resource
    SnowflakeIdGenerator snowflakeIdGenerator;
    @Resource
    SysDictTypeMapper sysDictTypeMapper;
    @Resource
    SysDictDataMapper sysDictDataMapper;

    @Override
    public SysDictType queryInfo(String dictId) {
        return sysDictTypeMapper.queryById(dictId);
    }

    @Override
    public List<SysDictType> queryAll(SysDictType sysDictType) {
        return sysDictTypeMapper.queryALl(sysDictType);
    }

    @Override
    public int saveDictType(SysDictType sysDictType) {
        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();

        if (StringUtil.isEmpty(sysDictType.getDictId())) {
            sysDictType.setDictId(snowflakeIdGenerator.snowflakeId());
            sysDictType.setCreateBy(username);
            sysDictType.setCreateName(nickname);
            sysDictType.setCreateDate(DateUtil.getNowDate());
            sysDictType.setUpdateBy(username);
            sysDictType.setUpdateName(nickname);
            sysDictType.setUpdateDate(DateUtil.getNowDate());
            return sysDictTypeMapper.insert(sysDictType);
        } else {
            SysDictType newDictType = sysDictTypeMapper.queryById(sysDictType.getDictId());
            if (newDictType == null) throw new BaseException("未查询到字典数据信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysDictType, newDictType);
            newDictType.setUpdateBy(username);
            newDictType.setUpdateName(nickname);
            newDictType.setUpdateDate(DateUtil.getNowDate());
            return sysDictTypeMapper.update(newDictType);
        }
    }

    @Override
    @Transactional
    public int deleteDictType(String dictType) {
        sysDictDataMapper.deleteByType(dictType);
        return sysDictTypeMapper.deleteByType(dictType);
    }
}
