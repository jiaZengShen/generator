/**
 *    Copyright 2006-2017 the original author or authors.
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
package org.mybatis.generator.codegen;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.config.Context;

public abstract class AbstractGenerator {
    protected Context context;
    protected IntrospectedTable introspectedTable;
    protected List<String> warnings;
    protected ProgressCallback progressCallback;

    public AbstractGenerator() {
        super();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public IntrospectedTable getIntrospectedTable() {
        return introspectedTable;
    }

    public void setIntrospectedTable(IntrospectedTable introspectedTable) {
        this.introspectedTable = introspectedTable;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<String> warnings) {
        this.warnings = warnings;
    }

    public ProgressCallback getProgressCallback() {
        return progressCallback;
    }

    public void setProgressCallback(ProgressCallback progressCallback) {
        this.progressCallback = progressCallback;
    }

    /**
     * 得到主键的parameter
     * @return
     */
    public Parameter getPrimaryKeyParameter(){
        List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
        if(columns != null && columns.size()>0){
            IntrospectedColumn column = columns.get(0);
            FullyQualifiedJavaType parameterType = column.getFullyQualifiedJavaType();
            Parameter parameter = new Parameter(parameterType,column.getJavaProperty());
            return parameter;
        }else {
            return null;
        }
    }

    /**
     * 得到驼峰的命名法的参数
     * @param type
     * @return
     */
    public String getSmallParameter(FullyQualifiedJavaType type){
        String str = type.getShortName();

        if(Character.isLowerCase(str.charAt(0)))
            return str;
        else
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }

    /**
     * 设置方法的备注
     * @param method
     * @param comment
     */
    public void setMethodCommentWithTableRemarks(Method method, String comment){
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
     * 复制接口函数
     * @param interMethod
     * @return
     */
    public Method copyInterface(Method interMethod){
        Method implMethod = new Method();
        implMethod.setName(interMethod.getName());
        implMethod.setComment(interMethod.getComment());
        implMethod.setReturnType(interMethod.getReturnType());
        for(Parameter parameter :interMethod.getParameters()){
            implMethod.addParameter(parameter);
        }
        implMethod.addAnnotation("@Override");
        return implMethod;
    }
}
