package org.mybatis.generator.codegen.mybatis3.service.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;

public class GetMethodGenerator extends AbstractJavaServiceMethodGenerator {

    @Override
    public  void addInterfaceElements(Interface interfaze){
        method = new Method();

        //设置返回值
        FullyQualifiedJavaType returnType;
        returnType = introspectedTable.getRules()
                .calculateAllFieldsClass();
        method.setReturnType(returnType);
        importedTypes.add(returnType);

        //设置名称
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getServiceGetId());





        //添加参数
        parameter =getPrimaryKeyParameter();
        if(parameter == null){
            return;
        }
        parameter.setComment("主键");
        method.addParameter(parameter); //$NON-NLS-1$

        //设置备注
        setMethodCommentWithTableRemarks(method,"根据主键得到对应的");



        if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

}
