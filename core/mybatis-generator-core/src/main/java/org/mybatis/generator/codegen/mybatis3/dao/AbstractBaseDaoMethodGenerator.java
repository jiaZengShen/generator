/**
 *    Copyright 2006-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
