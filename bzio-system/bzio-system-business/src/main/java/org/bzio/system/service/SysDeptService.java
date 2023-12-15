package org.bzio.system.service;

import org.bzio.common.security.entity.DeptTreeNode;
import org.bzio.common.security.entity.SysDept;

import java.util.List;

/**
 * @author snow
 */
public interface SysDeptService {

    /**
     * 部门详情信息
     */
    SysDept queryInfo(String deptId);

    /**
     * 查询部门列表
     */
    List<SysDept> queryAll(SysDept sysDept);

    /**
     * 查询树节点信息
     */
    List<DeptTreeNode> queryTreeNode();

    /**
     * 部门树状下拉列表
     */
    List<DeptTreeNode> treeList(List<DeptTreeNode> treeNodes);

    /**
     * 保存部门
     */
    int saveDept(SysDept sysDept);

    /**
     * 删除部门
     */
    int delBatch(String[] deptIds);
}
