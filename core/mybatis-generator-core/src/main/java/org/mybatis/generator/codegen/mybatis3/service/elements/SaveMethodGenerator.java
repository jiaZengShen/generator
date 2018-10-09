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
package org.mybatis.generator.codegen.mybatis3.service.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.dao.elements.AbstractJavaDaoMethodGenerator;

public class SaveMethodGenerator extends AbstractJavaServiceMethodGenerator {

    @Override
    public  void addInterfaceElements(Interface interfaze){
        importedTypes.clear();

        method = new Method();

        //设置返回值
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());

        //设置名称
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getServiceSaveId());

        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();



        //添加参数
        importedTypes.add(parameterType);
        parameter = new Parameter(parameterType, getSmallParameter(parameterType));
        parameter.setComment(introspectedTable.getRemarks());
        method.addParameter(parameter); //$NON-NLS-1$

        //设置备注
        setMethodCommentWithTableRemarks(method,"保存");



        if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }




}
