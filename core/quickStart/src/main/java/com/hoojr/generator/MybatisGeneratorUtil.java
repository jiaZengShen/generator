package com.hoojr.generator;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 代码生成类
 * Created by hoze on 2017/1/10.
 */
public class MybatisGeneratorUtil {

	// generatorConfig模板路径
	private static String generatorConfig_vm = "/template/generatorConfig.vm";
	// Service模板路径
	private static String service_vm = "/template/Service.vm";
	// ServiceMock模板路径
	private static String serviceMock_vm = "/template/ServiceMock.vm";
	// ServiceImpl模板路径
	private static String serviceImpl_vm = "/template/ServiceImpl.vm";

	/**
	 * 根据模板生成generatorConfig.xml文件
	 * @param jdbcDriver   驱动路径
	 * @param jdbcUrl      链接
	 * @param jdbcUsername 帐号
	 * @param jdbcPassword 密码
	 * @param database      数据库
	 * @param tablePrefix  表前缀
	 */
	public static void generator(
			String jdbcDriver,
			String jdbcUrl,
			String jdbcUsername,
			String jdbcPassword,
			String javaPath ,
			String xmlPath ,
			String database,
			String tablePrefix,
			Map<String, String> lastInsertIdTables,String beforePackageName,String afterPackageName) throws Exception{

		String os = System.getProperty("os.name");
		if (os.toLowerCase().startsWith("win")) {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath().replaceFirst("/", "");
			service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath().replaceFirst("/", "");
			serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath().replaceFirst("/", "");
			serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath().replaceFirst("/", "");
		} else {
			generatorConfig_vm = MybatisGeneratorUtil.class.getResource(generatorConfig_vm).getPath();
			service_vm = MybatisGeneratorUtil.class.getResource(service_vm).getPath();
			serviceMock_vm = MybatisGeneratorUtil.class.getResource(serviceMock_vm).getPath();
			serviceImpl_vm = MybatisGeneratorUtil.class.getResource(serviceImpl_vm).getPath();
		}

		//String targetProject = module + "-dao";
		//String basePath = MybatisGeneratorUtil.class.getResource("/").getPath().replace(module+"/target/classes/", "").replaceFirst("/", "");

		String generatorConfigXml ="generatorConfig.xml";// MybatisGeneratorUtil.class.getResource("/").getPath().replace("/target/classes/", "") + "/src/main/resources/generatorConfig.xml";
		String sql = "SELECT table_name ,TABLE_COMMENT  FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = '" + database + "' AND table_name= '" + tablePrefix + "';";


		System.out.println("========== 开始生成generatorConfig.xml文件 ==========");
		List<Map<String, Object>> tables = new ArrayList<Map<String, Object>>();
		try {
			VelocityContext context = new VelocityContext();
			Map<String, Object> table;

			// 查询定制前缀项目的所有表
			JdbcUtil jdbcUtil = new JdbcUtil(jdbcDriver, jdbcUrl, jdbcUsername, jdbcPassword);
			List<Map> result = jdbcUtil.selectByParams(sql, null);
			for (Map map : result) {
				System.out.println(map.get("TABLE_NAME"));
				table = new HashMap<String, Object>(2);
				table.put("table_name", map.get("TABLE_NAME"));
				table.put("model_name", lineToHump(ObjectUtils.toString(map.get("TABLE_NAME"))));
				table.put("table_comment",map.get("TABLE_COMMENT"));
				tables.add(table);
			}
			jdbcUtil.release();

			String targetProject = javaPath;
			String  targetProjectSqlMap= xmlPath; //com.fh.msg.pub.data.api.base.dao.model.PubDic;
			context.put("tables", tables);
			context.put("generator_javaModelGenerator_targetPackage", beforePackageName + "."+afterPackageName+".dao.model");
			context.put("generator_sqlMapGenerator_targetPackage", beforePackageName + "."+afterPackageName+".dao.mapper");
			context.put("generator_javaClientGenerator_targetPackage", beforePackageName + "."+afterPackageName+".dao.mapper");
			context.put("targetProject", targetProject);
			context.put("targetProject_sqlMap", targetProjectSqlMap);
			context.put("generator_jdbc_password", jdbcPassword);
			context.put("last_insert_id_tables", lastInsertIdTables);
			context.put("generator_jdbc_username",jdbcUsername);
			context.put("generator_jdbc_url",jdbcUrl);
			context.put("generator_jdbc_driver",jdbcDriver);
			VelocityUtil.generate(generatorConfig_vm,xmlPath, generatorConfigXml, context);

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("========== 结束生成generatorConfig.xml文件 ==========");

		System.out.println("========== 开始运行MybatisGenerator ==========");
		List<String> warnings = new ArrayList<String>();
		File configFile = new File(xmlPath + generatorConfigXml);
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}

		System.out.println("========== 结束运行MybatisGenerator ==========");

		System.out.println("========== 开始生成Service ==========");
		String ctime = new SimpleDateFormat("yyyy/M/d").format(new Date());
		String servicePath = javaPath  + beforePackageName.replaceAll("\\.", "/")+ "/"+afterPackageName.replaceAll("\\.", "/")+"/rpc/api";
		String serviceImplPath = javaPath  + beforePackageName.replaceAll("\\.", "/")+"/"+ afterPackageName.replaceAll("\\.", "/")+"/rpc/service/impl";
		for (int i = 0; i < tables.size(); i++) {
			String model = lineToHump(ObjectUtils.toString(tables.get(i).get("table_name")));
			String modelComment = ObjectUtils.toString(tables.get(i).get("table_comment")) ;
			String service =  model + "Service.java";

			String serviceMock = model + "ServiceMock.java";
			String serviceImpl =  model + "ServiceImpl.java";
			// 生成service
			File serviceFile = new File(service);
			if (!serviceFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", beforePackageName+"."+afterPackageName);
				context.put("beforePackage", beforePackageName);
				context.put("afterPackage", afterPackageName);
				context.put("model", model);
				context.put("model_comment",modelComment);
				context.put("ctime", ctime);
				VelocityUtil.generate(service_vm,servicePath, service, context);
				System.out.println(service);
			}
			// 生成serviceMock
			File serviceMockFile = new File(serviceMock);
			if (!serviceMockFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", beforePackageName+"."+afterPackageName);
				context.put("beforePackage", beforePackageName);
				context.put("afterPackage", afterPackageName);
				context.put("model", model);
				context.put("ctime", ctime);
                context.put("model_comment",modelComment);
				VelocityUtil.generate(serviceMock_vm,servicePath, serviceMock, context);
				System.out.println(serviceMock);
			}
			// 生成serviceImpl
			File serviceImplFile = new File(serviceImpl);
			if (!serviceImplFile.exists()) {
				VelocityContext context = new VelocityContext();
				context.put("package_name", beforePackageName+"."+afterPackageName);
				context.put("beforePackage", beforePackageName);
				context.put("afterPackage", afterPackageName);
				context.put("model", model);
                context.put("model_comment",modelComment);
				context.put("mapper", toLowerCaseFirstOne(model));
				context.put("ctime", ctime);
				VelocityUtil.generate(serviceImpl_vm,serviceImplPath, serviceImpl, context);
				System.out.println(serviceImpl);
			}
		}
		System.out.println("========== 结束生成Service ==========");
	}

	// 递归删除非空文件夹
	public static void deleteDir(File dir) {
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				deleteDir(files[i]);
			}
		}
		dir.delete();
	}


    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String lineToHump(String str) {
       Pattern linePattern = Pattern.compile("_(\\w)");
        if (null == str || "".equals(str)) {
            return str;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);

        str = sb.toString();
        str = str.substring(0, 1).toUpperCase() + str.substring(1);

        return str;
    }

    /**
     * 首字母转小写
     * @param s
     * @return
     */
    public static String toLowerCaseFirstOne(String s) {
        if (StringUtils.isBlank(s)) {
            return s;
        }
        if (Character.isLowerCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

}
