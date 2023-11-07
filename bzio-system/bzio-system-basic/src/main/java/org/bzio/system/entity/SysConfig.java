package org.bzio.system.entity;

import lombok.Data;
import org.bzio.common.core.web.entity.BaseEntity;

import java.io.Serializable;

/**
 * 系统参数实体类
 *
 * @author snow
 */
@Data
public class SysConfig extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 505170173396504308L;

    private String configId;

    private String configName;

    private String configKey;

    private String configValue;
    
    private String note;
}
