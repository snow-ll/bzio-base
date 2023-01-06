package org.bzio.common.core.web.entity;

import java.util.List;

/**
 * 列表响应
 *
 * @author: snow
 */
public class TableData {

    /**
     * 数据总数
     */
    private long total;

    /**
     * 列表数据
     */
    private List<?> rows;

    /**
     * 状态码
     */
    private int code;

    /**
     * 响应消息
     */
    private String msg;

    public TableData() {

    }

    public TableData(List<?> list, int total)
    {
        this.rows = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
