package org.bzio.system.service;

import org.bzio.common.security.entity.SysDept;
import org.bzio.common.security.entity.TreeSelect;

import java.util.List;

/**
 * @author: snow
 */
public interface SysDeptService {

    SysDept queryInfo(String deptId);

    List<SysDept> queryAll(SysDept sysDept);

    List<TreeSelect> treeList(List<SysDept> sysDepts);

    int saveDept(SysDept sysDept);

    int deleteDept(String deptId);
}
