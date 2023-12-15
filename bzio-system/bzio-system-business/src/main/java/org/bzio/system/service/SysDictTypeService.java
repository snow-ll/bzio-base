package org.bzio.system.service;


import org.bzio.system.entity.SysDictType;

import java.util.List;

/**
 * @author snow
 */
public interface SysDictTypeService {

    /**
     * 查询字典信息
     */
    SysDictType queryInfo(String dictId);

    /**
     * 查询字典列表
     */
    List<SysDictType> queryAll(SysDictType sysDictType);

    /**
     * 保存字典信息
     */
    int saveDictType(SysDictType sysDictType);

    /**
     * 删除字典信息
     */
    int deleteDictType(String dictType);
}
