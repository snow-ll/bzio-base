package org.bzio.service;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.web.entity.AjaxResult;

/**
 * @author snow
 * @since 2023/3/8 10:07
 */
public interface ValidateCodeService {

    AjaxResult createCaptcha();

    void checkCaptcha(String code, String uuid) throws UserException;
}
