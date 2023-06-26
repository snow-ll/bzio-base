package org.bzio.system.entity;

import org.bzio.common.core.web.entity.BaseEntity;

/**
 * 系统字典数据表实体类
 *
 * @author snow
 */
public class SysDictData extends BaseEntity {

    private static final long serialVersionUID = 495282597677111616L;

    private String dictCode;

    private String dictLabel;

    private String dictValue;

    private String dictType;

    private Integer orderNum;

    private Integer status;
    
    private String note;

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
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
