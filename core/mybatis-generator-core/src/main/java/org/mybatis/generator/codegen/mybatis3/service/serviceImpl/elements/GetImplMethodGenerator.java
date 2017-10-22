package org.mybatis.generator.codegen.mybatis3.service.serviceImpl.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements.AbstractJavaDaoImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.service.elements.GetMethodGenerator;


import java.util.Set;
import java.util.TreeSet;

public class GetImplMethodGenerator extends AbstractJavaServiceImplMethodGenerator {

    private GetMethodGenerator getMethodGenerator;

    public GetImplMethodGenerator(GetMethodGenerator getMethodGenerator){
        this.getMethodGenerator = getMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(getMethodGenerator.getImportedTypes());
        Method method = copyInterface(getMethodGenerator.getMethod());

        String daoName = getMapperFiledName(topLevelClass);

        method.addBodyLine("return "+daoName+"."+introspectedTable.getDaoGetId()+
                "("+getMethodGenerator.getParameter().getName()+");");


        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
