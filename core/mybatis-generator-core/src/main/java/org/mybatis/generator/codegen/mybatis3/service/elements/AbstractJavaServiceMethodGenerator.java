package org.mybatis.generator.codegen.mybatis3.service.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.codegen.mybatis3.dao.AbstractBaseDaoMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.service.AbstractBaseServiceMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractJavaServiceMethodGenerator extends AbstractBaseServiceMethodGenerator{
    public abstract   void addInterfaceElements(Interface interfaze);

    Method method ;
    Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();

    public Method getMethod() {
        return method;
    }
    public Set<FullyQualifiedJavaType> getImportedTypes(){
        return importedTypes;
    }
    Parameter parameter ;
    public Parameter getParameter(){
        return parameter;
    }
}
