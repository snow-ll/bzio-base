package org.bzio.common.security.entity;

import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * 角色信息表实体类
 *
 * @author snow
 */
@Data
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 4080774754247548788L;

    /**
     * 角色主键
     */
    private String roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色权限字符串
     */
    private String roleKey;
    /**
     * 显示顺序
     */
    private Integer orderNum;
    /**
     * 菜单树选择项是否关联显示
     */
    private Boolean menuCheckStrictly;
    /**
     * 部门树选择项是否关联显示
     */
    private Boolean deptCheckStrictly;
    /**
     * 角色状态（0正常 1停用）
     */
    private Integer status;
    /**
     * 删除标志（0代表存在 1代表删除）
     */
    private Integer delFlag;
    
    private List<String> menuIds;
}

