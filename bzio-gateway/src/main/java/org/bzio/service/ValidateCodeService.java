package org.bzio.service;

import org.bzio.common.core.exception.system.user.UserException;
import org.bzio.common.core.web.entity.AjaxResult;

/**
 * @author snow
 */
public interface ValidateCodeService {

    AjaxResult createCaptcha();

    void checkCaptcha(String code, String uuid) throws UserException;
}
