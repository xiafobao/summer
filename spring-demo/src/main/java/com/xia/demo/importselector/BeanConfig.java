package com.xia.demo.importselector;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xiafb
 * @date Created in 2019/12/16 17:07
 * description
 * modified By
 * version
 */
@Configuration
@Import(MyImportSeleector.class)
public class BeanConfig {
}
