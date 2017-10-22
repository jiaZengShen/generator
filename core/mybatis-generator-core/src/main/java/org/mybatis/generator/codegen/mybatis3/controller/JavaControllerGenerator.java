package org.mybatis.generator.codegen.mybatis3.controller;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.controller.elements.*;
import org.mybatis.generator.codegen.mybatis3.service.JavaServiceGenerator;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class JavaControllerGenerator extends AbstractJavaClientGenerator {

    JavaServiceGenerator javaServiceGenerator;

    public JavaControllerGenerator(JavaServiceGenerator javaServiceGenerator) {
        super(false);
        this.javaServiceGenerator = javaServiceGenerator;
    }



    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getControllerType());
        TopLevelClass topLevelClass = new TopLevelClass(type);
        topLevelClass.addImportedType("org.springframework.beans.factory.annotation.Autowired");
        topLevelClass.addImportedType("org.springframework.stereotype.Controller");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.RequestMapping");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.RequestMethod");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.ResponseBody");
        topLevelClass.addImportedType("org.springframework.web.bind.annotation.RequestBody");
        topLevelClass.addImportedType(introspectedTable.getControllerReturnType());
        topLevelClass.addAnnotation("@Controller");
        topLevelClass.addAnnotation("@RequestMapping(value = \"/"+getSmallParameter(introspectedTable.getRules()
                .calculateAllFieldsClass())+"\")");
        topLevelClass.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(topLevelClass);

        String rootInterface = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }



        //添加成员变量
        Field field = new Field();
        FullyQualifiedJavaType serviceType = new FullyQualifiedJavaType(
                introspectedTable.getServiceInterfaceType());
        topLevelClass.addImportedType(serviceType);
        field.setType(serviceType);
        field.setName(getSmallParameter(serviceType));
        field.addAnnotation("@Autowired");
        topLevelClass.addField(field);

        //添加方法。
        initializeAndExecuteGenerator(new SaveImplMethodGenerator(javaServiceGenerator.getSaveMethodGenerator()),topLevelClass);
        initializeAndExecuteGenerator(new RemoveImplMethodGenerator(javaServiceGenerator.getRemoveMethodGenerator()),topLevelClass);
        initializeAndExecuteGenerator(new UpdateImplMethodGenerator(javaServiceGenerator.getUpdateMethodGenerator()),topLevelClass);
        initializeAndExecuteGenerator(new GetImplMethodGenerator(javaServiceGenerator.getGetMethodGenerator()),topLevelClass);
        initializeAndExecuteGenerator(new ListImplMethodGenerator(javaServiceGenerator.getListMethodGenerator()),topLevelClass);


        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().clientGenerated(null, topLevelClass,
                introspectedTable)) {
            answer.add(topLevelClass);
        }

        List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }

        return answer;
    }





    protected void initializeAndExecuteGenerator(
            AbstractJavaControllerImplMethodGenerator methodGenerator,
            TopLevelClass interfaze) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(interfaze);
    }

    public List<CompilationUnit> getExtraCompilationUnits() {
        return null;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }

    public String getSmallParameter(FullyQualifiedJavaType type){
        String str = type.getShortName();

        if(Character.isLowerCase(str.charAt(0)))
            return str;
        else
            return (new StringBuilder()).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1)).toString();
    }
}