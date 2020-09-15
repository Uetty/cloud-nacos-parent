
命名空间id： `473c483f-b4da-44a5-8bc9-92d77e4dd5c6`
配置列表：

|  DataId | Group  |  归属应用  |  操作  |
| ---- | ---- | ---- | ---- |
| application.yml | dev |  |  |
| application.yml | test1 |  |  |


application.yml  dev  配置内容：
```yaml
server:
  port: 9090

spring:
  resources:
    static-locations: 'classpath:/static'
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: 'jdbc:mysql://localhost:3306/springboot_sample?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true'
    username: root
    password: 123456


mybatis:
  # mappers文件
  mapper-locations: classpath*:/mappers/*.xml

# mybatis 分页插件
pagehelper:
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  params: count=countSql

app:
  tip: 'this is dev profile'
```
 
application.yml  test1  配置内容：
```yaml
server:
  port: 9090

spring:
  resources:
    static-locations: 'classpath:/static'
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: 'jdbc:mysql://localhost:3306/springboot_sample?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf-8&autoReconnect=true'
    username: root
    password: 123456


mybatis:
  # mappers文件
  mapper-locations: classpath*:/mappers/*.xml

# mybatis 分页插件
pagehelper:
  helperDialect: mysql
  reasonable: true
  supportMethodsArguments: true
  params: count=countSql

app:
  tip: 'this is test1 profile'
```