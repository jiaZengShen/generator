package org.mybatis.generator.codegen.mybatis3.controller.elements;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.AbstractGenerator;

import java.util.List;

public abstract class AbstractJavaControllerImplMethodGenerator extends AbstractGenerator {
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
