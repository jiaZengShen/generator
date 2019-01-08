package com.hoojr.generator;


import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成类
 * Created by fm on 2017/1/10.
 */
public class Generator {

	// 根据命名规范，只修改此常量值即可
	private static String DATABASE = "video";//数据库名称
	private static String TABLE_PREFIX = "douyin_video";//需要生成的表名
	private static String BEFORE_PACKAGE_NAME = "com.hoojr";//包名
	private static String AFTER_PACKAGE_NAME = "video";
	private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";//驱动 ，目前只支持mysql，
	/*
    private static String JDBC_URL = "jdbc:mysql://39.105.154.83:3306/fms_data?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true"; //jdbc 的url
	private static String JDBC_USERNAME = "root";//用户名
	private static String JDBC_PASSWORD = "hoze2018";//密码
*/
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/video?useUnicode=true&amp;characterEncoding=utf-8&amp;autoReconnect=true"; //jdbc 的url
    private static String JDBC_USERNAME = "root";//用户名
    private static String JDBC_PASSWORD = "501821782";//密码


    // 需要insert后返回主键的表配置，key:表名,value:主键名
	private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<String, String>();
	static {
		//LAST_INSERT_ID_TABLES.put("c_sen_ev_bed", "pk_evbed");
	}

	/**
	 * 自动代码生成 ，主函数
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		MybatisGeneratorUtil.generator(JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD,
                "d:/src/main/java/", "d:/src/main/resources/", DATABASE, TABLE_PREFIX, LAST_INSERT_ID_TABLES,BEFORE_PACKAGE_NAME, AFTER_PACKAGE_NAME);
	}

}
