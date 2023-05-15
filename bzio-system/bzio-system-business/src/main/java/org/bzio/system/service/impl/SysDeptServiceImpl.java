package org.bzio.system.service.impl;

import org.bzio.common.core.exception.system.dept.DeptException;
import org.bzio.common.core.util.*;
import org.bzio.common.core.web.entity.TreeNode;
import org.bzio.common.core.web.service.BaseServiceImpl;
import org.bzio.common.security.entity.SysDept;
import org.bzio.common.security.mapper.SysDeptMapper;
import org.bzio.common.security.util.AuthUtil;
import org.bzio.system.service.SysDeptService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: snow
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
    public List<TreeNode> queryTreeNode() {
        return sysDeptMapper.queryTreeNode();
    }

    /**
     * 部门树状下拉列表
     */
    @Override
    public List<TreeNode> treeList(List<TreeNode> treeNodes) {
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
    public int deleteDept(String deptId) {
        return sysDeptMapper.deleteById(deptId);
    }

    /**
     * 构建前端所需要树结构
     * 递归方式
     */
    private List<SysDept> buildDeptTree(List<SysDept> deptList) {
        // 结果集
        List<SysDept> returnList = new ArrayList<>();
        // 临时集合，暂存部门id
        List<String> tempList = new ArrayList<>();

        // 遍历部门集合，部门id添加进入临时集合
        for (SysDept dept : deptList) {
            tempList.add(dept.getDeptId());
        }

        for (SysDept dept : deptList) {
            // tempList不包含dept父级id时，为顶级节点
            if (!tempList.contains(dept.getParentId())) {
                // 如果是顶级节点, 遍历该父节点的所有子节点
                // set部门t的子级部门集合
                recursionFn(deptList, dept);
                // 将set子级集合children的部门返回
                returnList.add(dept);
            }
        }

        // 空部门直接返回
        if (returnList.isEmpty()) {
            returnList = deptList;
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<SysDept> list, SysDept t) {
        // 得到子节点列表
        List<SysDept> childList = getChildList(list, t);
        // set部门t的子级部门集合
        t.setChildren(childList);

        // 递归查询部门t每个子孙级部门的子级
        for (SysDept tChild : childList) {
            // 判断有子级递归
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     * @param list 所有部门集合
     * @param t 父级id
     * @return 部门t的子级集合
     */
    private List<SysDept> getChildList(List<SysDept> list, SysDept t) {
        // 部门t子级集合
        List<SysDept> tlist = new ArrayList<>();

        // 遍历部门列表
        for (SysDept n : list) {
            // 当前循环中部门父级id不为空，并且父级id和部门t的id相同则为t的子级
            if (StringUtil.isNotEmpty(n.getParentId()) && n.getParentId().equals(t.getDeptId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<SysDept> list, SysDept t) {
        return getChildList(list, t).size() > 0;
    }
}
