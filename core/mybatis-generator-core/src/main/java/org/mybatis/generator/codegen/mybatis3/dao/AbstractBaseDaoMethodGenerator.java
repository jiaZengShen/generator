package org.mybatis.generator.codegen.mybatis3.dao;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.config.Context;

import java.util.List;

public abstract class  AbstractBaseDaoMethodGenerator extends AbstractGenerator {

    /**
     * 设置方法的备注
     * @param method
     * @param comment
     */
    public void setMethodCommentWithTableRemarks(  Method method, String comment){
        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        if(introspectedTable.getRemarks() != null && introspectedTable.getRemarks().length()>0)
            method.setComment(comment+introspectedTable.getRemarks());
        else
            method.setComment(comment+parameterType.getShortName());
        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);
    }

    /**
     * 得到参数的小写
     * @param type
     * @return
     */



}
