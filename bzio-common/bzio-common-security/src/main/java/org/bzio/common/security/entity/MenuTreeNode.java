package org.bzio.common.security.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bzio.common.core.web.entity.TreeNode;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author snow
 */
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
