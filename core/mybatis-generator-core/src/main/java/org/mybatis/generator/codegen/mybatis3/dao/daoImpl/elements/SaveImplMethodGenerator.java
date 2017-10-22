package org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.dao.elements.SaveMethodGenerator;
import org.mybatis.generator.internal.util.messages.Messages;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SaveImplMethodGenerator extends AbstractJavaDaoImplMethodGenerator {

    private SaveMethodGenerator saveMethodGenerator;

    public SaveImplMethodGenerator(SaveMethodGenerator saveMethodGenerator){
        this.saveMethodGenerator = saveMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(saveMethodGenerator.getImportedTypes());
        Method method = copyInterface(saveMethodGenerator.getMethod());

        String mapperName = getMapperFiledName(topLevelClass);
        method.addBodyLine("return "+mapperName+"."+introspectedTable.getInsertStatementId()+"("+saveMethodGenerator.getParameter().getName()+");");


        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
