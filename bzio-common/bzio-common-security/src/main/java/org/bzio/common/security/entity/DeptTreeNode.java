package org.bzio.common.security.entity;

import lombok.Data;
import org.bzio.common.core.web.entity.TreeNode;

/**
 * @author snow
 */
@Data
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
}
