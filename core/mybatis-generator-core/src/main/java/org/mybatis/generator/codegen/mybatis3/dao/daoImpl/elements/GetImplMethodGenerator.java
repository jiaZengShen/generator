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
package org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.dao.elements.GetMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.dao.elements.UpdateMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class GetImplMethodGenerator extends AbstractJavaDaoImplMethodGenerator {

    private GetMethodGenerator getMethodGenerator;

    public GetImplMethodGenerator(GetMethodGenerator getMethodGenerator){
        this.getMethodGenerator = getMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(getMethodGenerator.getImportedTypes());
        Method method = copyInterface(getMethodGenerator.getMethod());

        String mapperName = getMapperFiledName(topLevelClass);

        try {
            method.addBodyLine("return " + mapperName + "." + introspectedTable.getSelectByPrimaryKeyStatementId() +
                    "(" + getMethodGenerator.getParameter().getName() + ");");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        method.setVisibility(JavaVisibility.PUBLIC);

        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
