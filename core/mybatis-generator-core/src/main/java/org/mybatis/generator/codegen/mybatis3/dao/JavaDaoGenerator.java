package org.mybatis.generator.codegen.mybatis3.dao;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.codegen.AbstractJavaClientGenerator;
import org.mybatis.generator.codegen.AbstractXmlGenerator;
import org.mybatis.generator.codegen.mybatis3.dao.elements.*;
import org.mybatis.generator.codegen.mybatis3.xmlmapper.XMLMapperGenerator;
import org.mybatis.generator.config.PropertyRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.messages.Messages.getString;

public class JavaDaoGenerator extends AbstractJavaClientGenerator {

    public JavaDaoGenerator() {
        super(false);
    }



    @Override
    public List<CompilationUnit> getCompilationUnits() {
        progressCallback.startTask(getString("Progress.17", //$NON-NLS-1$
                introspectedTable.getFullyQualifiedTable().toString()));
        CommentGenerator commentGenerator = context.getCommentGenerator();

        FullyQualifiedJavaType type = new FullyQualifiedJavaType(
                introspectedTable.getDAOInterfaceType());
        Interface interfaze = new Interface(type);
        interfaze.setVisibility(JavaVisibility.PUBLIC);
        commentGenerator.addJavaFileComment(interfaze);

        String rootInterface = introspectedTable
                .getTableConfigurationProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        if (!stringHasValue(rootInterface)) {
            rootInterface = context.getJavaClientGeneratorConfiguration()
                    .getProperty(PropertyRegistry.ANY_ROOT_INTERFACE);
        }

        if (stringHasValue(rootInterface)) {
            FullyQualifiedJavaType fqjt = new FullyQualifiedJavaType(
                    rootInterface);
            interfaze.addSuperInterface(fqjt);
            interfaze.addImportedType(fqjt);
        }
        saveMethodGenerator = new SaveMethodGenerator();
        initializeAndExecuteGenerator(saveMethodGenerator,interfaze);
        removeMethodGenerator = new RemoveMethodGenerator();
        initializeAndExecuteGenerator(removeMethodGenerator,interfaze);
        updateMethodGenerator = new UpdateMethodGenerator();
        initializeAndExecuteGenerator(updateMethodGenerator,interfaze);
        listMethodGenerator = new ListMethodGenerator();
        initializeAndExecuteGenerator(listMethodGenerator,interfaze);
        getMethodGenerator = new GetMethodGenerator();
        initializeAndExecuteGenerator(getMethodGenerator,interfaze);

        List<CompilationUnit> answer = new ArrayList<CompilationUnit>();
        if (context.getPlugins().clientGenerated(interfaze, null,
                introspectedTable)) {
            answer.add(interfaze);
        }

        List<CompilationUnit> extraCompilationUnits = getExtraCompilationUnits();
        if (extraCompilationUnits != null) {
            answer.addAll(extraCompilationUnits);
        }

        return answer;
    }

    private SaveMethodGenerator saveMethodGenerator;
    private RemoveMethodGenerator removeMethodGenerator;
    private UpdateMethodGenerator updateMethodGenerator;
    private ListMethodGenerator listMethodGenerator;
    private GetMethodGenerator getMethodGenerator;

    public UpdateMethodGenerator getUpdateMethodGenerator() {
        return updateMethodGenerator;
    }

    public ListMethodGenerator getListMethodGenerator() {
        return listMethodGenerator;
    }

    public GetMethodGenerator getGetMethodGenerator() {
        return getMethodGenerator;
    }

    public SaveMethodGenerator getSaveMethodGenerator(){
        return saveMethodGenerator;
    }

    public RemoveMethodGenerator getRemoveMethodGenerator() {
        return removeMethodGenerator;
    }

    protected void initializeAndExecuteGenerator(
            AbstractJavaDaoMethodGenerator methodGenerator,
            Interface interfaze) {
        methodGenerator.setContext(context);
        methodGenerator.setIntrospectedTable(introspectedTable);
        methodGenerator.setProgressCallback(progressCallback);
        methodGenerator.setWarnings(warnings);
        methodGenerator.addInterfaceElements(interfaze);
        ;
    }

    public List<CompilationUnit> getExtraCompilationUnits() {
        return null;
    }

    @Override
    public AbstractXmlGenerator getMatchedXMLGenerator() {
        return new XMLMapperGenerator();
    }
}