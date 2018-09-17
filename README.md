MyBatis Generator (MBG)
=======================

[![Build Status](https://travis-ci.org/mybatis/generator.svg?branch=master)](https://travis-ci.org/mybatis/generator)
[![Coverage Status](https://coveralls.io/repos/mybatis/generator/badge.svg?branch=master&service=github)](https://coveralls.io/github/mybatis/generator?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/561964c6a193340f2800033c/badge.svg?style=flat)](https://www.versioneye.com/user/projects/561964c6a193340f2800033c)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.mybatis.generator/mybatis-generator)
[![License](http://img.shields.io/:license-apache-brightgreen.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

![mybatis-generator](http://mybatis.github.io/images/mybatis-logo.png)

Code generator for MyBatis and iBATIS.

It will generate code for all versions of MyBatis, and versions of iBATIS after version 2.2.0. It will introspect a database table (or many tables) and will generate artifacts that can be used to access the table(s). This lessens the initial nuisance of setting up objects and configuration files to interact with database tables. MBG seeks to make a major impact on the large percentage of database operations that are simple CRUD (Create, Retrieve, Update, Delete).
 
使用方法：clone 项目完毕后，使用idea或者eclipse打开项目，
在mybatis-generator-core项目中，参照已有的xml文件建立新的工程文件，
在test目录下的org.mybatis.generator.MyTest.MgbTest中运行test即可完成
