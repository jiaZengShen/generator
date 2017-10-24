package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.service.elements.RemoveMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class RemoveImplMethodGenerator extends AbstractJavaControllerImplMethodGenerator {

    private RemoveMethodGenerator removeMethodGenerator;

    public RemoveImplMethodGenerator(RemoveMethodGenerator removeMethodGenerator){
        this.removeMethodGenerator = removeMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(removeMethodGenerator.getImportedTypes());
        Method method = new Method();
        FullyQualifiedJavaType returnType = new FullyQualifiedJavaType(introspectedTable.getControllerReturnType());
        method.setReturnType(returnType);
        method.setName(introspectedTable.getControllerRemoveId());

        method.setVisibility(JavaVisibility.PUBLIC);


        //参数
        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        Parameter parameter = new Parameter(parameterType, getSmallParameter(parameterType));
        parameter.addAnnotation("@RequestBody");
        parameter.setComment(introspectedTable.getRemarks());
        method.addParameter(parameter);

        //method annotaion
        method.addAnnotation("@ResponseBody");
        method.addAnnotation("@RequestMapping(value = \"/"+introspectedTable.getControllerRemoveId()+"\", method = RequestMethod.POST)");

        setMethodCommentWithTableRemarks(method,"移除");

        String serviceName = getMapperFiledName(topLevelClass);
        method.addBodyLine(returnType.getShortName()+" "+getSmallParameter(returnType)+" = new "+returnType.getShortName()+"();");

        String serviceReturnType =getSmallParameter(removeMethodGenerator.getMethod().getReturnType() );
        method.addBodyLine(serviceReturnType+" value = "+serviceName+"."+introspectedTable.getServiceRemoveId()+"("+parameter.getName()+");");
        method.addBodyLine("//TODO 请将service返回值与前台数据关联");
        method.addBodyLine("return "+getSmallParameter(returnType)+";");





        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
