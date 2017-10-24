package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.service.elements.ListMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class ListImplMethodGenerator extends AbstractJavaControllerImplMethodGenerator {

    private ListMethodGenerator listMethodGenerator;

    public ListImplMethodGenerator(ListMethodGenerator listMethodGenerator){
        this.listMethodGenerator = listMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(listMethodGenerator.getImportedTypes());
        Method method = new Method();
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getControllerReturnType());
        method.setReturnType(returnType);
        method.setName(introspectedTable.getControllerListId());

        //参数
        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        Parameter parameter = new Parameter(parameterType, getSmallParameter(parameterType));
        //parameter.addAnnotation("@RequestBody");
        //parameter.setComment(introspectedTable.getRemarks());
        //method.addParameter(parameter);

        //method annotaion
        method.addAnnotation("@ResponseBody");
        method.addAnnotation("@RequestMapping(value = \"/"+introspectedTable.getControllerListId()+"\", method = RequestMethod.POST)");

        setMethodCommentWithTableRemarks(method,"列出所有");

        method.setVisibility(JavaVisibility.PUBLIC);

        String serviceName = getMapperFiledName(topLevelClass);
        method.addBodyLine(returnType.getShortName()+" "+getSmallParameter(returnType)+" = new "+returnType.getShortName()+"();");

        FullyQualifiedJavaType serviceReturnJavaType = listMethodGenerator.getMethod().getReturnType();
        String serviceReturnType =serviceReturnJavaType.getShortName();
        method.addBodyLine(serviceReturnType+" value = "+serviceName+"."+introspectedTable.getServiceListAllId()+"("+");");
        method.addBodyLine("//TODO 请将service返回值与前台数据关联");
        method.addBodyLine("return "+getSmallParameter(returnType)+";");





        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
