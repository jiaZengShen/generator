package org.mybatis.generator.codegen.mybatis3.dao.elements;

import org.mybatis.generator.api.dom.java.*;

import java.util.Set;
import java.util.TreeSet;

public class UpdateMethodGenerator extends AbstractJavaDaoMethodGenerator {

    @Override
    public  void addInterfaceElements(Interface interfaze){
        method = new Method();

        //设置返回值
        method.setReturnType(FullyQualifiedJavaType.getIntInstance());

        //设置名称
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName(introspectedTable.getDaoUpdateId());

        FullyQualifiedJavaType parameterType;
        parameterType = introspectedTable.getRules()
                .calculateAllFieldsClass();


        //添加参数
        importedTypes.add(parameterType);
        parameter = new Parameter(parameterType, getSmallParameter(parameterType));
        parameter.setComment(introspectedTable.getRemarks());
        method.addParameter(parameter); //$NON-NLS-1$

        //设置备注
        setMethodCommentWithTableRemarks(method,"更新");



        if (context.getPlugins().clientInsertMethodGenerated(method, interfaze,
                introspectedTable)) {
            interfaze.addImportedTypes(importedTypes);
            interfaze.addMethod(method);
        }
    }

}
