package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysDictType;

import java.util.List;

/**
 * @author snow
 */
@Mapper
public interface SysDictTypeMapper {

    SysDictType queryById(String dictCode);

    List<SysDictType> queryALl(SysDictType sysDictType);

    int insert(SysDictType sysDictType);

    int update(SysDictType sysDictType);

    int deleteById(String dictCode);
}
