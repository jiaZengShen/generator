package org.mybatis.generator.codegen.mybatis3.service.serviceImpl.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements.AbstractJavaDaoImplMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.service.elements.SaveMethodGenerator;


import java.util.Set;
import java.util.TreeSet;

public class SaveImplMethodGenerator extends AbstractJavaServiceImplMethodGenerator {

    private SaveMethodGenerator saveMethodGenerator;

    public SaveImplMethodGenerator(SaveMethodGenerator saveMethodGenerator){
        this.saveMethodGenerator = saveMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(saveMethodGenerator.getImportedTypes());
        Method method = copyInterface(saveMethodGenerator.getMethod());

        String daoName = getMapperFiledName(topLevelClass);
        method.addBodyLine("return "+daoName+"."+introspectedTable.getDaoSaveId()+"("+saveMethodGenerator.getParameter().getName()+");");
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
