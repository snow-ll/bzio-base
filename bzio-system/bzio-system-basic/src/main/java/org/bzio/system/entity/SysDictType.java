package org.bzio.system.entity;

import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;

/**
 * 系统字典类型表实体类
 *
 * @author snow
 */
@Data
public class SysDictType extends BaseEntity {

    private static final long serialVersionUID = 266769603328128983L;

    private String dictId;

    private String dictName;

    private String dictType;
    
    private Integer orderNum;

    private Integer status;
    
    private String note;
}

