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
package org.mybatis.generator.codegen.mybatis3.service.serviceImpl.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractGenerator;

import java.util.List;

public abstract class AbstractJavaServiceImplMethodGenerator extends AbstractGenerator {
    public abstract   void addInterfaceElements(TopLevelClass interfaze);




    /**
     * 得到mapper的名称
     * @param topLevelClass
     * @return
     */
    protected String getMapperFiledName(TopLevelClass topLevelClass){
        List<Field> fields = topLevelClass.getFields();
        if(fields.size()>0){
            Field field = fields.get(0);
            return field.getName();
        }else {
            return "";
        }
    }

    /**
     * 得到主键列
     * @return
     */
    public IntrospectedColumn getPrimaryKeyColum(){
        List<IntrospectedColumn> columns = introspectedTable.getPrimaryKeyColumns();
        if(columns != null && columns.size()>0){
            IntrospectedColumn column = columns.get(0);
            return column;
        }else {
            return null;
        }
    }


}
