package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.system.entity.SysDictData;

import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
@Mapper
public interface SysDictDataMapper {

    SysDictData queryById(String dictCode);

    SysDictData queryByValue(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    List<SysDictData> queryALl(SysDictData sysDictData);

    List<Map> queryDictData(SysDictData sysDictData);

    int insert(SysDictData sysDictData);

    int update(SysDictData sysDictData);

    int deleteById(String dictCode);
    
    int deleteByType(String dictType);
}
