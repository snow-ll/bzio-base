package org.bzio.system.service.impl;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysDictType;
import org.bzio.system.mapper.SysDictTypeMapper;
import org.bzio.system.service.SysDictTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysDictTypeServiceImpl extends BaseServiceImpl implements SysDictTypeService {

    @Resource
    SysDictTypeMapper sysDictTypeMapper;

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
        String userName = AuthUtil.getUserName();
        String nickName = AuthUtil.getNickName();

        if (StringUtil.isEmpty(sysDictType.getDictId())) {
            sysDictType.setDictId(IdUtil.simpleUUID());
            sysDictType.setCreateBy(userName);
            sysDictType.setCreateName(nickName);
            sysDictType.setCreateDate(DateUtil.getNowDate());
            sysDictType.setUpdateBy(userName);
            sysDictType.setUpdateName(nickName);
            sysDictType.setUpdateDate(DateUtil.getNowDate());
            return sysDictTypeMapper.insert(sysDictType);
        } else {
            SysDictType newDictType = sysDictTypeMapper.queryById(sysDictType.getDictId());
            if (newDictType == null) throw new BaseException("未查询到字典数据信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysDictType, newDictType);
            newDictType.setUpdateBy(userName);
            newDictType.setUpdateName(nickName);
            newDictType.setUpdateDate(DateUtil.getNowDate());
            return sysDictTypeMapper.update(newDictType);
        }
    }

    @Override
    public int deleteDictType(String dictId) {
        return sysDictTypeMapper.deleteById(dictId);
    }
}
