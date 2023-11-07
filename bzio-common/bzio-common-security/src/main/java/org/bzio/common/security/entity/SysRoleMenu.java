package org.bzio.common.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 角色菜单关联表实体
 *
 * @author snow
 */
@Data
public class SysRoleMenu implements Serializable {

    private static final long serialVersionUID = -8753494836637008114L;

    private String id;

    private String roleId;

    private String menuId;
}
