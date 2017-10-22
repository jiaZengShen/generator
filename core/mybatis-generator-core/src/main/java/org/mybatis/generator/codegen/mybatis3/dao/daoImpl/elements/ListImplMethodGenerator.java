package org.mybatis.generator.codegen.mybatis3.dao.daoImpl.elements;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.codegen.mybatis3.dao.elements.GetMethodGenerator;
import org.mybatis.generator.codegen.mybatis3.dao.elements.ListMethodGenerator;

import java.util.Set;
import java.util.TreeSet;

public class ListImplMethodGenerator extends AbstractJavaDaoImplMethodGenerator {

    private ListMethodGenerator listMethodGenerator;

    public ListImplMethodGenerator(ListMethodGenerator listMethodGenerator){
        this.listMethodGenerator = listMethodGenerator;
    }

    @Override
    public  void addInterfaceElements(TopLevelClass topLevelClass){
        Set<FullyQualifiedJavaType> importedTypes = new TreeSet<FullyQualifiedJavaType>();
        importedTypes.addAll(listMethodGenerator.getImportedTypes());
        Method method = copyInterface(listMethodGenerator.getMethod());

        String mapperName = getMapperFiledName(topLevelClass);
        FullyQualifiedJavaType exampleType = new FullyQualifiedJavaType(
                introspectedTable.getExampleType());

        /*
        * UserAuthorityExample example = new UserAuthorityExample();
        UserAuthorityExample.Criteria criteria = example.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andRankBetween(startRank,endRank);
        List<UserAuthority> userAuthorities =  userAuthoritypper.selectByExample(example);
        */
        method.addBodyLine(exampleType.getShortName() + " example = new "+exampleType.getShortName()+"();");
        method.addBodyLine(exampleType.getShortName()+".Criteria criteria = example.createCriteria();");
        method.addBodyLine("List<"+exampleType.getShortName()+"> list = "+mapperName+".selectByExample(example);");
        method.addBodyLine("return list;");


        importedTypes.add(exampleType);



        context.getCommentGenerator().addGeneralMethodComment(method,
                introspectedTable);



        if (context.getPlugins().clientInsertMethodGenerated(method, topLevelClass,
                introspectedTable)) {
            topLevelClass.addImportedTypes(importedTypes);
            topLevelClass.addMethod(method);
        }
    }


}
