/**
 * 
 */
package com.fccfc.framework.web.interceptor;

import javax.annotation.Resource;

import com.fccfc.framework.core.Initialization;
import com.fccfc.framework.core.utils.LogUtil;
import com.fccfc.framework.web.service.MenuService;

/**
 * <Description> <br>
 * 
 * @author 王伟<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2014年11月25日 <br>
 * @since V1.0<br>
 * @see com.fccfc.framework.web.interceptor <br>
 */
public class MenuInitialization implements Initialization {

    @Resource
    private MenuService menuService;

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        menuService.cacheAllMenu();
    }

    /*
     * (non-Javadoc)
     * @see org.springframework.beans.factory.DisposableBean#destroy()
     */
    @Override
    public void destroy() throws Exception {
        LogUtil.info("-------MenuInitialization destroy---------");
    }

}
