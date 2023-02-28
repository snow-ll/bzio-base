package org.bzio.system.service;


import org.bzio.system.entity.SysDictType;

import java.util.List;

/**
 * @author snow
 */
public interface SysDictTypeService {

    SysDictType queryInfo(String dictId);

    List<SysDictType> queryAll(SysDictType sysDictType);

    int saveDictType(SysDictType sysDictType);

    int deleteDictType(String dictId);
}
