package org.bzio.system.service;

import org.bzio.system.entity.SysDictData;

import java.util.List;

/**
 * @author snow
 */
public interface SysDictDataService {

    SysDictData queryInfo(String dictCode);

    List<SysDictData> queryAll(SysDictData sysDictData);

    int saveDictData(SysDictData sysDictData);

    int deleteDictData(String dictCode);
}
