package org.bzio.system.service;

import org.bzio.common.security.entity.DeptTreeNode;
import org.bzio.common.security.entity.SysDept;

import java.util.List;

/**
 * @author snow
 */
public interface SysDeptService {

    SysDept queryInfo(String deptId);

    List<SysDept> queryAll(SysDept sysDept);

    List<DeptTreeNode> queryTreeNode();
    
    List<DeptTreeNode> treeList(List<DeptTreeNode> treeNodes);

    int saveDept(SysDept sysDept);

    int delBatch(String[] deptIds);
}
