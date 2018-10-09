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
package org.mybatis.generator.codegen.mybatis3.dao.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

public class ListMethodGenerator extends AbstractJavaDaoMethodGenerator {

    @Override
    public  void addInterfaceElements(Interface interfaze){
        method = new Method();

        //设置返回值
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType
                .getNewListInstance();
        FullyQualifiedJavaType listType;
        listType = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());
        returnType.addTypeArgument(listType);
        importedTypes.add(returnType);
        importedTypes.add( FullyQualifiedJavaType.getNewListInstance());
        method.setReturnType(returnType);


        //设置名称
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getDaoListAllId());

        //设置备注
        setMethodCommentWithTableRemarks(method,"列出所有的");



        if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

}
