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

    public TreeNode() {

    }

    public TreeNode(String id, String label, String pid) {
        this.id = id;
        this.label = label;
        this.pid = pid;
    }

    public TreeNode(TreeNode TreeNode) {
        this.id = TreeNode.getId();
        this.label = TreeNode.getLabel();
        this.children = TreeNode.getChildren();
    }

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

    @Override
    public String toString() {
        return "TreeNode{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", pid='" + pid + '\'' +
                ", children=" + children +
                '}';
    }
}
