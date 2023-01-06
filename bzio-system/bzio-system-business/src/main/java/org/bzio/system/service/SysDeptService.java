package org.bzio.system.service;

import org.bzio.system.entity.TreeSelect;
import org.bzio.system.entity.SysDept;

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
