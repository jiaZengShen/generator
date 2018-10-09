MyBatis Generator (MBG)
=======================
# mybatis 扩展 #
jeanWin 维护 ，fork自 mybatis generator
### 使用说明
###1.quickStart项目中的com.hoojr.generator.Generator的main函数运行即可。###
###2.如何修改参数###
    private static String DATABASE = "hoze_fms";//数据库名称
    private static String TABLE_PREFIX = "cost_indexs";//需要生成的表名
    private static String BEFORE_PACKAGE_NAME = "com.hoze.fms";//包名
    private static String AFTER_PACKAGE_NAME = "base";
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";//驱动 ，目前只支持mysql，
    private static String JDBC_URL = "jdbc:mysql://127.0.0.1:3306/hoze_fms?useUnicode=true&amp;amp;characterEncoding=utf-8&amp;amp;autoReconnect=true"; //jdbc 的url
    private static String JDBC_USERNAME = "root";//用户名
    private static String JDBC_PASSWORD = "501821782";//密码
    其中JDBC_DRIVER 目前只支持mysql，不支持其他数据库。
###3.代码结构
    在mybatis generator官方版本的基础上去掉了冗长的英文注释。在model类中读取数据库中表、字段的注释，并加入代码中。
    利用velocity根据模板生成代码。代码的基类在resource的javaCode中，其中BaseServiceImpl类中利用反射调用mapper的方法。
###4.联系方式
    jeanWin 501821782@qq.com
    欢迎提出bug和修改意见。    
