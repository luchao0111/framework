/**************************************************************************************** 
 Copyright © 2003-2012 hbasesoft Corporation. All rights reserved. Reproduction or       <br>
 transmission in whole or in part, in any form or by any means, electronic, mechanical <br>
 or otherwise, is prohibited without the prior written consent of the copyright owner. <br>
 ****************************************************************************************/
package com.hbasesoft.framework.common;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <Description> 测试项目启动 <br>
 * 
 * @author 王伟<br>
 * @version 1.0<br>
 * @taskId <br>
 * @CreateDate 2018年9月11日 <br>
 * @since V1.0<br>
 * @see com.hbasesoft.framework.common <br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BootstrapTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BootstrapTest {

    /**
     * Description: <br>
     * 
     * @author 王伟<br>
     * @taskId <br>
     * @param args <br>
     */
    public static void main(final String[] args) {
        // 项目启动时

        Bootstrap.before();
        ConfigurableApplicationContext context = SpringApplication.run(BootstrapTest.class, args);
        Bootstrap.after(context);
    }
}
