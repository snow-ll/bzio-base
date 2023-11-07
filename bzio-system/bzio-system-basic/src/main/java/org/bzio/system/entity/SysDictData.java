package org.bzio.system.entity;

import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;

/**
 * 系统字典数据表实体类
 *
 * @author snow
 */
@Data
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 495282597677111616L;

    private String dictCode;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private Integer orderNum;

    private Integer status;
    
    private String note;
}
