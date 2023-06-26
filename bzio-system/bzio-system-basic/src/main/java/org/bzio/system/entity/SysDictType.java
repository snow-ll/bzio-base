package org.bzio.system.entity;

import org.bzio.common.core.web.entity.BaseEntity;

/**
 * 系统字典类型表实体类
 *
 * @author snow
 */
public class SysDictType extends BaseEntity {

    private static final long serialVersionUID = 266769603328128983L;

    private String dictId;

    private String dictName;

    private String dictType;
    
    private Integer orderNum;

    private Integer status;
    
    private String note;

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

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

