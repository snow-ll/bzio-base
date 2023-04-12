package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.security.entity.SysDept;

import java.util.List;

/**
 * @author: snow
 */
@Mapper
public interface SysDeptMapper {

    SysDept queryById(String deptId);

    List<SysDept> queryAll(SysDept sysDept);

    List<TreeNode> queryTreeNode();

    int insert(SysDept sysDept);

    int update(SysDept sysDept);

    int deleteById(String deptId);
}
