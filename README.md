## 物品循环

 - API提供了相关的对接接口
 - CORE提供了核心插件
 - SERVICE提供了链接数据库的相关接口
 - OSS业务运用和管理平台，提供B端接口
 - WEB提供C端接口
 - USER用户中心提供用户相关信息的服务
 - PAY支付中心提供支付相关的服务
 - SSO单点登陆管理平台
 
## 服务调用流程图 
![Alt text](./doc/FlowChart.jpeg)

**注：API、CORE为其他服务提供基础调用**

## 容器服务启动时需要注意

#### 运行时服务器的配置

- 在服务器上需要添加配置文件，配置文件路径为/data/config.properties
- 运行时需要在服务器上安装docker
- 在运行容器前需要重新配置网路，配置网路的网段为172.18.0.1/16

#### 部署命令

- 运行时需要使用travis进行构建
- 部署命令：deploy server/service/web/oss/delete_image/pull_image RT1.0.0
- *在发布时需要不要忘记修改版本号*