package org.bzio.system.service.impl;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.util.snowflake.SnowflakeIdGenerator;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysDictData;
import org.bzio.system.entity.SysDictType;
import org.bzio.system.mapper.SysDictDataMapper;
import org.bzio.system.mapper.SysDictTypeMapper;
import org.bzio.system.service.SysDictDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author snow
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl implements SysDictDataService {

    @Resource
    SnowflakeIdGenerator snowflakeIdGenerator;
    @Resource
    SysDictDataMapper sysDictDataMapper;
    @Resource
    SysDictTypeMapper sysDictTypeMapper;

    @Override
    public SysDictData queryInfo(String dictCode) {
        return sysDictDataMapper.queryById(dictCode);
    }

    @Override
    public List<SysDictData> queryAll(SysDictData sysDictData) {
        return sysDictDataMapper.queryALl(sysDictData);
    }

    @Override
    public List<Map> queryByType(SysDictData sysDictData) {
        // 判断字典是否有效，过滤无效字典
        SysDictType type = sysDictTypeMapper.queryByType(sysDictData.getDictType());
        if (StringUtil.isNotNull(type) && type.getStatus() == 0) {
            sysDictData.setStatus(0);
            return sysDictDataMapper.queryDictData(sysDictData);
        }
        return null;
    }

    @Override
    public int saveDictData(SysDictData sysDictData) {
        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();

        if (StringUtil.isEmpty(sysDictData.getDictType())) throw new BaseException("字典数据类型不能为空！");

        if (StringUtil.isEmpty(sysDictData.getDictCode())) {
            sysDictData.setDictCode(snowflakeIdGenerator.snowflakeId());
            sysDictData.setCreateBy(username);
            sysDictData.setCreateName(nickname);
            sysDictData.setCreateDate(DateUtil.getNowDate());
            sysDictData.setUpdateBy(username);
            sysDictData.setUpdateName(nickname);
            sysDictData.setUpdateDate(DateUtil.getNowDate());
            return sysDictDataMapper.insert(sysDictData);
        } else {
            SysDictData newData = sysDictDataMapper.queryById(sysDictData.getDictCode());
            if (newData == null) throw new BaseException("未查询到字典数据信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysDictData, newData);
            newData.setUpdateBy(username);
            newData.setUpdateName(nickname);
            newData.setUpdateDate(DateUtil.getNowDate());
            return sysDictDataMapper.update(newData);
        }
    }

    @Override
    public int deleteDictData(String dictCode) {
        return sysDictDataMapper.deleteById(dictCode);
    }
}
