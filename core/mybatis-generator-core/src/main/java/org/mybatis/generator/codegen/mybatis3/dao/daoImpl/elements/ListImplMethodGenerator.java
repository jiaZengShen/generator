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
import org.mybatis.generator.codegen.mybatis3.dao.elements.ListMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class ListImplMethodGenerator extends AbstractJavaDaoImplMethodGenerator {

    private ListMethodGenerator listMethodGenerator;

    public ListImplMethodGenerator(ListMethodGenerator listMethodGenerator){
        this.listMethodGenerator = listMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(listMethodGenerator.getImportedTypes());
        Method method = copyInterface(listMethodGenerator.getMethod());

        String mapperName = getMapperFiledName(topLevelClass);
        FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(
                introspectedTable.getExampleType());
        method.setVisibility(JavaVisibility.PUBLIC);

        /*
        * UserAuthorityExample example = new UserAuthorityExample();
        UserAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andRankBetween(startRank,endRank);
        List<UserAuthority> userAuthorities =  userAuthoritypper.selectByExample(example);
        */
        method.addBodyLine(exampleType.getShortName() + " example = new "+exampleType.getShortName()+"();");
        method.addBodyLine(exampleType.getShortName()+".Criteria criteria = example.createCriteria();");
        method.addBodyLine(listMethodGenerator.getMethod().getReturnType().getShortName()+" list = "+mapperName+".selectByExample(example);");
        method.addBodyLine("return list;");


        importedTypes.add(exampleType);



        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
