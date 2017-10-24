package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.service.elements.GetMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class GetImplMethodGenerator extends AbstractJavaControllerImplMethodGenerator {

    private GetMethodGenerator getMethodGenerator;

    public GetImplMethodGenerator(GetMethodGenerator getMethodGenerator){
        this.getMethodGenerator = getMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(getMethodGenerator.getImportedTypes());
        Method method = new Method();
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getControllerReturnType());
        method.setReturnType(returnType);
        method.setName(introspectedTable.getControllerGetId());

        //参数
        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();

        Parameter parameter = parameter =getPrimaryKeyParameter();
        if(parameter == null){
            return;
        }
        parameter.setComment("主键");
        method.addParameter(parameter);

        method.setVisibility(JavaVisibility.PUBLIC);

        //method annotaion
        method.addAnnotation("@ResponseBody");
        method.addAnnotation("@RequestMapping(value = \"/"+introspectedTable.getControllerGetId()+"\", method = RequestMethod.POST)");

        setMethodCommentWithTableRemarks(method,"列出所有");

        String serviceName = getMapperFiledName(topLevelClass);
        method.addBodyLine(returnType.getShortName()+" "+getSmallParameter(returnType)+" = new "+returnType.getShortName()+"();");

        FullyQualifiedJavaType serviceReturnJavaType = getMethodGenerator.getMethod().getReturnType();
        String serviceReturnType =serviceReturnJavaType.getShortName();
        method.addBodyLine(serviceReturnType+" value = "+serviceName+"."+introspectedTable.getServiceGetId()+"("+parameter.getName()+");");
        method.addBodyLine("//TODO 请将service返回值与前台数据关联");
        method.addBodyLine("return "+getSmallParameter(returnType)+";");





        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
