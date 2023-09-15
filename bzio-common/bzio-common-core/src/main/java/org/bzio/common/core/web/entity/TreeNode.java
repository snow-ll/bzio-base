package org.bzio.common.core.web.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author snow
 */
public class TreeNode<T> implements Serializable {

    private static final long serialVersionUID = -8136014146878363040L;
    
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
    private List<T> children;

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

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }
}
