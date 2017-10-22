package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
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
        Method method = new Method();
        method.setReturnType(new FullyQualifiedJavaType(introspectedTable.getControllerType()));
        method.setName(introspectedTable.getControllerSaveId());

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
        method.addAnnotation("@RequestMapping(value = \"/"+introspectedTable.getControllerSaveId()+"\", method = RequestMethod.POST)");


        String serviceName = getMapperFiledName(topLevelClass);
        method.addBodyLine("return "+serviceName+"."+introspectedTable.getServiceSaveId()+"("+parameter.getName()+");");


        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
