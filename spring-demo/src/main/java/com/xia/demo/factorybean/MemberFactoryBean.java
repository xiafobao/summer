package com.xia.demo.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author xiafb
 * @date Created in 2019/12/17 11:12
 * description
 * modified By
 * version
 */
public class MemberFactoryBean implements FactoryBean<Member> {
    @Override
    public Member getObject() throws Exception {
        return new Member();
    }

    @Override
    public Class<?> getObjectType() {
        return Member.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
