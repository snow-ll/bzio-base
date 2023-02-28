package org.bzio.system.entity;

import org.bzio.common.core.web.entity.BaseEntity;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统字典类型表实体类
 *
 * @author snow
 */
public class SysDictType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 266769603328128983L;

    private String dictId;

    private String dictName;

    private String dictType;

    private Integer status;

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId;
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

