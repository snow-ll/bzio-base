package org.bzio.common.core.web.entity;

/**
 * 响应结果
 *
 * @author: snow
 */
public class AjaxResult {

    private int code;

    private String msg;

    private Object data;

    public AjaxResult() {

    }

    public AjaxResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public AjaxResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    public static AjaxResult success(Object obj) {
        return new AjaxResult(200, "操作成功！", obj);
    }

    public static AjaxResult success(String msg) {
        return new AjaxResult(200, msg, null);
    }

    public static AjaxResult success(String msg, Object data) {
        return new AjaxResult(200, msg, data);
    }

    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    public static AjaxResult error(String msg) {
        return new AjaxResult(500, msg);
    }

    /**
     *
     * @param f 判断操作数据库是否之操作一条数据 true-只操作一条
     */
    public static AjaxResult toAjax(int rows, boolean f, String msg, String errMsg) {
        if (f)
            return rows == 1 ? success(msg) : error(errMsg);
        else
            return rows > 0 ? success(msg) : error(errMsg);
    }

    public static AjaxResult toAjax(int rows) {
        return toAjax(rows, false, "操作成功！", "操作失败！");
    }

    public static AjaxResult toAjax(int rows, String msg, String errMsg) {
        return toAjax(rows, false, msg, errMsg);
    }

    public static AjaxResult toAjax(boolean result) {
        return result ? success() : error();
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "AjaxResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
