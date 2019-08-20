1 进入WeBASE-Collect-Bee-core目录下，执行../gradlew clean bootJar;
2  在WeBASE-Collect-Bee-core 目录下 cd dist/config  修改相应配置
 修改system.nodeStr=node1@127.0.0.1:20800 
 修改sharding.jdbc.datasource.ds.jdbc-url=jdbc:mysql://10.107.118.64:3306/bac_bee1?autoReconnect=true&useSSL=false&serverTimezone=GMT%2b8&useUnicode=true&characterEncoding=UTF-8
     sharding.jdbc.datasource.ds.username=root
     sharding.jdbc.datasource.ds.password=123456
   拷贝节点证书；
3  sh start.sh
4 打开 {ip}:8899界面;





  *** 新增java时，db parse两个组件需要替换, core下面config目录***