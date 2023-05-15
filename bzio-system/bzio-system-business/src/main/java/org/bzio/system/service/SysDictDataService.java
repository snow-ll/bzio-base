package org.bzio.system.service;

import org.bzio.system.entity.SysDictData;

import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
public interface SysDictDataService {

    SysDictData queryInfo(String dictCode);

    List<SysDictData> queryAll(SysDictData sysDictData);
    
    List<Map> queryDictData(SysDictData sysDictData);

    int saveDictData(SysDictData sysDictData);

    int deleteDictData(String dictCode);
}
