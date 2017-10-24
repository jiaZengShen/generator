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
