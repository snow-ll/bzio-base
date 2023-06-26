package org.bzio.common.security.entity;

import org.bzio.common.core.web.entity.TreeNode;

/**
 * @author snow
 */
public class DeptTreeNode extends TreeNode {

    private static final long serialVersionUID = 6774016705616742314L;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 显示顺序
     */
    private Integer orderNum;
    
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
}
