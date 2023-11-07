package org.bzio.common.security.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 系统用户部门关联表
 *
 * @author snow
 */
@Data
public class SysUserDept implements Serializable {

    private static final long serialVersionUID = 6859082493603128522L;

    private String id;

    private String userId;

    private String deptId;
}
