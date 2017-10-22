package org.mybatis.generator.codegen.mybatis3.dao.elements;

import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractGenerator;
import org.mybatis.generator.codegen.mybatis3.dao.AbstractBaseDaoMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractJavaDaoMethodGenerator extends AbstractBaseDaoMethodGenerator {
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
