package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.dept.DeptException;
import org.bzio.common.core.util.*;
import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.DeptTreeNode;
import org.bzio.common.security.entity.SysDept;
import org.bzio.common.security.mapper.SysDeptMapper;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author snow
 */
@Service
public class SysDeptServiceImpl extends BaseServiceImpl implements SysDeptService {

    @Resource
    SysDeptMapper sysDeptMapper;

    /**
     * 部门详情信息
     */
    @Override
    public SysDept queryInfo(String deptId) {
        return sysDeptMapper.queryById(deptId);
    }

    /**
     * 查询部门列表
     */
    @Override
    public List<SysDept> queryAll(SysDept sysDept) {
        return sysDeptMapper.queryAll(sysDept);
    }

    /**
     * 查询树节点信息
     */
    @Override
    public List<DeptTreeNode> queryTreeNode() {
        return sysDeptMapper.queryTreeNode();
    }

    /**
     * 部门树状下拉列表
     */
    @Override
    public List<DeptTreeNode> treeList(List<DeptTreeNode> treeNodes) {
        return TreeNodeUtil.buildTreeList(treeNodes);
    }

    /**
     * 保存部门
     */
    @Override
    public int saveDept(SysDept sysDept) {
        // 获取登录人信息
        String username = AuthUtil.getUsername();
        String nickname = AuthUtil.getNickname();

        // 调整父级单位
        SysDept parentDept = sysDeptMapper.queryById(sysDept.getParentId());
        if (parentDept != null) {
            if (StringUtil.isNotEmpty(parentDept.getAncestors()))
                sysDept.setAncestors(parentDept.getAncestors() + "," + sysDept.getParentId());
            else
                sysDept.setAncestors(sysDept.getParentId());
        }

        // 判断传入的id是否为空
        // 为空新增用户
        if (StringUtil.isEmpty(sysDept.getDeptId())) {
            sysDept.setDeptId(IdUtil.simpleUUID());
            sysDept.setCreateBy(username);
            sysDept.setCreateName(nickname);
            sysDept.setCreateDate(DateUtil.getNowDate());
            sysDept.setUpdateBy(username);
            sysDept.setUpdateName(nickname);
            sysDept.setUpdateDate(DateUtil.getNowDate());
            return sysDeptMapper.insert(sysDept);
        }else {
            SysDept newDept = sysDeptMapper.queryById(sysDept.getDeptId());
            if (newDept == null) throw new DeptException("未查询到部门信息！");

            BeanUtil.copyPropertiesIgnoreNull(sysDept, newDept);
            newDept.setUpdateBy(username);
            newDept.setUpdateName(nickname);
            newDept.setUpdateDate(DateUtil.getNowDate());
            return sysDeptMapper.update(newDept);
        }
    }

    /**
     * 删除部门
     */
    @Override
    public int delBatch(String[] deptIds) {
        for (String deptId: deptIds) {
            deleteChild(deptId);
        }
        return sysDeptMapper.deleteBatch(deptIds);
    }

    /**
     * 递归删除子级 
     * @param parentId 当前父级id
     * @return
     */
    public void deleteChild(String parentId) {
        // 查询当前节点所有子节点
        List<Map> depts = sysDeptMapper.queryChild(parentId);

        // 遍历子节点处理
        for (Map dept: depts) {
            long isLeaf = (long) dept.get("isLeaf");
            String menuId = (String) dept.get("deptId");
            if (isLeaf != 1) {
                // 有子节点继续处理
                deleteChild(menuId);
            }
            // 无子节点直接删除
            sysDeptMapper.deleteById(menuId);
        }
    }
}
