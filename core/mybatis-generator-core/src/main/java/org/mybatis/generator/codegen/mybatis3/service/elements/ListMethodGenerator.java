package org.mybatis.generator.codegen.mybatis3.service.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.codegen.mybatis3.dao.elements.AbstractJavaDaoMethodGenerator;

public class ListMethodGenerator extends AbstractJavaServiceMethodGenerator {

    @Override
    public  void addInterfaceElements(Interface interfaze){
        method = new Method();

        //设置返回值
        FullyQualifiedJavaType returnType = FullyQualifiedJavaType
                .getNewListInstance();
        FullyQualifiedJavaType listType;
        listType = new FullyQualifiedJavaType(
                introspectedTable.getBaseRecordType());
        returnType.addTypeArgument(listType);
        importedTypes.add(returnType);
        importedTypes.add( FullyQualifiedJavaType.getNewListInstance());
        method.setReturnType(returnType);


        //设置名称
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getServiceListAllId());

        //设置备注
        setMethodCommentWithTableRemarks(method,"列出所有的");



        if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

}
