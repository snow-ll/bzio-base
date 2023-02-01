package org.bzio.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.system.entity.SysDept;
import org.bzio.system.entity.TreeSelect;

import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysDeptMapper {

    SysDept queryById(String deptId);

    List<SysDept> queryAll(SysDept sysDept);

    List<TreeSelect> queryTreeNode();

    int insert(SysDept sysDept);

    int update(SysDept sysDept);

    int deleteById(String deptId);
}
