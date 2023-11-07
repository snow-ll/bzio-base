package org.bzio.system.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统日志实体类
 *
 * @author snow
 */
@Data
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1780474228893568179L;

    /**
     * 日志主键
     */
    private String logId;

    /**
     * 日志标题
     */
    private String title;

    /**
     * 日志操作类型
     */
    private Integer businessType;

    /**
     * 描述
     */
    private String logDesc;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 操作url
     */
    private String operationUrl;

    /**
     * 操作ip
     */
    private String operationIp;

    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date operationTime;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求结果
     */
    private String responseResult;

    /**
     * 错误信息
     */
    private String errorMsg;

    /**
     * 操作状态
     */
    private Integer status;
}
