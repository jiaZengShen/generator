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
package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.service.elements.UpdateMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class UpdateImplMethodGenerator extends AbstractJavaControllerImplMethodGenerator {

    private UpdateMethodGenerator updateMethodGenerator;

    public UpdateImplMethodGenerator(UpdateMethodGenerator updateMethodGenerator){
        this.updateMethodGenerator = updateMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(updateMethodGenerator.getImportedTypes());
        Method method = new Method();
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getControllerReturnType());
        method.setReturnType(returnType);
        method.setName(introspectedTable.getControllerUpdateId());

        //参数
        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        Parameter parameter = new Parameter(parameterType, getSmallParameter(parameterType));
        parameter.addAnnotation("@RequestBody");
        parameter.setComment(introspectedTable.getRemarks());
        method.addParameter(parameter);


        method.setVisibility(JavaVisibility.PUBLIC);


        //method annotaion
        method.addAnnotation("@ResponseBody");
        method.addAnnotation("@RequestMapping(value = \"/"+introspectedTable.getControllerUpdateId()+"\", method = RequestMethod.POST)");


        String serviceName = getMapperFiledName(topLevelClass);
        method.addBodyLine(returnType.getShortName()+" "+getSmallParameter(returnType)+" = new "+returnType.getShortName()+"();");

        String serviceReturnType =getSmallParameter(updateMethodGenerator.getMethod().getReturnType() );
        method.addBodyLine(serviceReturnType+" value = "+serviceName+"."+introspectedTable.getServiceUpdateId()+"("+parameter.getName()+");");
        method.addBodyLine("//TODO 请将service返回值与前台数据关联");
        method.addBodyLine("return "+getSmallParameter(returnType)+";");



        setMethodCommentWithTableRemarks(method,"更新");

        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
