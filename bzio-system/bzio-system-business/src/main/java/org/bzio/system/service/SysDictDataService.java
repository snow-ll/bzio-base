package org.bzio.system.service;

import org.bzio.system.entity.SysDictData;

import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
public interface SysDictDataService {

    /**
     * 查询字典项信息
     */
    SysDictData queryInfo(String dictCode);

    /**
     * 查询字典项列表
     */
    List<SysDictData> queryAll(SysDictData sysDictData);

    /**
     * 根据查询字典类型查询字典项
     */
    List<Map> queryByType(SysDictData sysDictData);

    /**
     * 保存字典项信息
     */
    int saveDictData(SysDictData sysDictData);

    /**
     * 删除字典项信息
     */
    int deleteDictData(String dictCode);
}
