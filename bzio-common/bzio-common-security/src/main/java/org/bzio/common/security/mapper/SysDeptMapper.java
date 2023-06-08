package org.bzio.common.security.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.security.entity.DeptTreeNode;
import org.bzio.common.security.entity.SysDept;

import java.util.List;
import java.util.Map;

/**
 * @author: snow
 */
@Mapper
public interface SysDeptMapper {

    SysDept queryById(String deptId);

    List<SysDept> queryAll(SysDept sysDept);

    List<DeptTreeNode> queryTreeNode();

    List<Map> queryChild(@Param("parentId") String parentId);

    int insert(SysDept sysDept);

    int update(SysDept sysDept);

    int deleteById(String deptId);
    
    int deleteBatch(@Param("deptIds") String[] deptIds);
}
