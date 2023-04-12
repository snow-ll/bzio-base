package org.bzio.common.security.entity;

import org.bzio.common.core.web.entity.TreeNode;

/**
 * @author snow
 */
public class MenuTreeNode extends TreeNode {

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
