package org.bzio.common.core.web.entity;

import java.util.List;

/**
 * @author: snow
 */
public class TreeNode {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 父ID
     */
    private String pid;

    /**
     * 子节点
     */
    private List<TreeNode> children;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

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
}
