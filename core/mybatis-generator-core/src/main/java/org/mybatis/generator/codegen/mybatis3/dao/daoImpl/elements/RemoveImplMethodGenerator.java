package org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.mybatis3.dao.elements.RemoveMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.dao.elements.SaveMethodGenerator;
import org.mybatis.generator.internal.util.JavaBeansUtil;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class RemoveImplMethodGenerator extends AbstractJavaDaoImplMethodGenerator {

    private RemoveMethodGenerator removeMethodGenerator;

    public RemoveImplMethodGenerator(RemoveMethodGenerator removeMethodGenerator){
        this.removeMethodGenerator = removeMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(removeMethodGenerator.getImportedTypes());
        Method method = copyInterface(removeMethodGenerator.getMethod());

        method.setVisibility(JavaVisibility.PUBLIC);


        String mapperName = getMapperFiledName(topLevelClass);
        if(introspectedTable.isSoftDel()){
            //TODO 需要修改为软删除，现在代码是硬删除
            method.addBodyLine("return "+mapperName+"."+introspectedTable.getDeleteByPrimaryKeyStatementId()+
                    "("+
                    removeMethodGenerator.getParameter().getName()
                    +"."+JavaBeansUtil.getJavaBeansGetter(getPrimaryKeyColum(),context,introspectedTable).getName()+"()"
                    +");");
        }else {
            //直接删掉
            method.addBodyLine("return "+mapperName+"."+introspectedTable.getDeleteByPrimaryKeyStatementId()+
                    "("+
                    removeMethodGenerator.getParameter().getName()
                    +"."+JavaBeansUtil.getJavaBeansGetter(getPrimaryKeyColum(),context,introspectedTable).getName()+"()"
                    +" );");
        }


        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
