package org.bzio.common.security.entity;


import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 部门实体类
 *
 * @author snow
 */
@Data
public class SysDept extends BaseEntity {

    private static final long serialVersionUID = 4502238025214991585L;

    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 父部门ID
     */
    private String parentId;
    /**
     * 父部门名称
     */
    private String parentName;
    /**
     * 祖级列表
     */
    private String ancestors;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 负责人
     */
    private String leader;
    /**
     * 部门联系电话
     */
    private String phone;
    /**
     * 部门邮箱
     */
    private String email;
    /**
     * 组织机构层级编码
     */
    private String level;
    /**
     * 组织机构子层级编码
     */
    private String subLevel;
    /**
     * 部门状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;
    /**
     * 子部门
     */
    private List<SysDept> children = new ArrayList<>();
}
