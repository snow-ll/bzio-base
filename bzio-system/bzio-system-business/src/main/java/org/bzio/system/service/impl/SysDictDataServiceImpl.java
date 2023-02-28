package org.bzio.system.service.impl;

import org.bzio.common.core.exception.BaseException;
import org.bzio.common.core.util.BeanUtil;
import org.bzio.common.core.util.DateUtil;
import org.bzio.common.core.util.IdUtil;
import org.bzio.common.core.util.StringUtil;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.entity.SysDictData;
import org.bzio.system.mapper.SysDictDataMapper;
import org.bzio.system.service.SysDictDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author snow
 */
@Service
public class SysDictDataServiceImpl extends BaseServiceImpl implements SysDictDataService {

    @Resource
    SysDictDataMapper sysDictDataMapper;

    @Override
    public SysDictData queryInfo(String dictCode) {
        return sysDictDataMapper.queryById(dictCode);
    }

    @Override
    public List<SysDictData> queryAll(SysDictData sysDictData) {
        return sysDictDataMapper.queryALl(sysDictData);
    }

    @Override
    public int saveDictData(SysDictData sysDictData) {
        // 获取登录人信息
        String userName = AuthUtil.getUserName();
        String nickName = AuthUtil.getNickName();

        if (StringUtil.isEmpty(sysDictData.getDictType())) throw new BaseException("字典数据类型不能为空！");

        if (StringUtil.isEmpty(sysDictData.getDictCode())) {
            sysDictData.setDictCode(IdUtil.simpleUUID());
            sysDictData.setCreateBy(userName);
            sysDictData.setCreateName(nickName);
            sysDictData.setCreateDate(DateUtil.getNowDate());
            sysDictData.setUpdateBy(userName);
            sysDictData.setUpdateName(nickName);
            sysDictData.setUpdateDate(DateUtil.getNowDate());
            return sysDictDataMapper.insert(sysDictData);
        } else {
            SysDictData newData = sysDictDataMapper.queryById(sysDictData.getDictCode());
            if (newData == null) throw new BaseException("未查询到字典数据信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysDictData, newData);
            newData.setUpdateBy(userName);
            newData.setUpdateName(nickName);
            newData.setUpdateDate(DateUtil.getNowDate());
            return sysDictDataMapper.update(newData);
        }
    }

    @Override
    public int deleteDictData(String dictCode) {
        return sysDictDataMapper.deleteById(dictCode);
    }
}
