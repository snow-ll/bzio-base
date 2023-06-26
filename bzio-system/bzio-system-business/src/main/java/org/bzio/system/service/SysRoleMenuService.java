package org.bzio.system.service;

import java.util.List;

/**
 * @author snow
 */
public interface SysRoleMenuService {
    
    List<String> queryMenuIdsByRoleId(String roleId);
    
    int insertBatch(String roleId, List<String> menuIds);
    
    
    int clearMenu(String roleId);
}
