/**
 *    Copyright 2006-2018 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
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
