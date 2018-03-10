package org.mybatis.generator.MyTest;

import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MgbTest {
    @Test
    public void test() throws Exception{
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //File configFile = new File("mbg_survey.xml");
        //File configFile = new File("mbg.xml");
        //File configFile = new File("mbg_fms.xml");
        //File configFile = new File("mbg_jiXiao.xml");
        //File configFile = new File("mbg_joke.xml");
        File configFile = new File("mbg_emergency.xml");
        //File configFile = new File("mbg_cost.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
    }
}
