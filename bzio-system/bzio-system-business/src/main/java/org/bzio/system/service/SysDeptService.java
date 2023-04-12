package org.bzio.system.service;

import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.security.entity.SysDept;

import java.util.List;

/**
 * @author: snow
 */
public interface SysDeptService {

    SysDept queryInfo(String deptId);

    List<SysDept> queryAll(SysDept sysDept);

    List<TreeNode> queryTreeNode();
    
    List<TreeNode> treeList(List<TreeNode> treeNodes);

    int saveDept(SysDept sysDept);

    int deleteDept(String deptId);
}
