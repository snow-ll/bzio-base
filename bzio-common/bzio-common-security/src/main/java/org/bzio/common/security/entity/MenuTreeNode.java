package org.bzio.common.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.bzio.common.core.web.entity.TreeNode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author snow
 */
@Data
public class MenuTreeNode extends TreeNode {

    private static final long serialVersionUID = 6774016705616742314L;
    
    /**
     * 跳转路径
     */
    private String path;

    /**
     * 组件路径
     */
    private String component;

    /**
     * svg图标
     */
    private String icon;

    /**
     * 权限字符
     */
    private String perms;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer orderNum;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
}
