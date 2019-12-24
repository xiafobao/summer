package com.xia.demo.importselector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xiafb
 * @date Created in 2019/12/16 17:04
 * description 自定义逻辑返回要导入的组件
 * modified By
 * version
 */
public class MyImportSeleector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.xia.demo.importselector.Student"};
    }
}
