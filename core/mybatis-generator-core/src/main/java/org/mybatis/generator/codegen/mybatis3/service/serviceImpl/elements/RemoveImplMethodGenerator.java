package org.mybatis.generator.codegen.mybatis3.service.serviceImpl.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements.AbstractJavaDaoImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.service.elements.RemoveMethodGenerator;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.Set;
import java.util.TreeSet;

public class RemoveImplMethodGenerator extends AbstractJavaServiceImplMethodGenerator {

    private RemoveMethodGenerator removeMethodGenerator;

    public RemoveImplMethodGenerator(RemoveMethodGenerator removeMethodGenerator){
        this.removeMethodGenerator = removeMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(removeMethodGenerator.getImportedTypes());
        Method method = copyInterface(removeMethodGenerator.getMethod());

        String daoName = getMapperFiledName(topLevelClass);

        method.addBodyLine("return "+daoName+"."+introspectedTable.getDaoRemoveId()+"("+removeMethodGenerator.getParameter().getName()+");");

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
