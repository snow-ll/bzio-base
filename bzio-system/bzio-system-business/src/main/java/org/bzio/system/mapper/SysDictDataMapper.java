package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysDictData;

import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
@Mapper
public interface SysDictDataMapper {

    SysDictData queryById(String dictCode);

    List<SysDictData> queryALl(SysDictData sysDictData);
    
    List<Map> queryDictData(SysDictData sysDictData);

    int insert(SysDictData sysDictData);

    int update(SysDictData sysDictData);

    int deleteById(String dictCode);
}
