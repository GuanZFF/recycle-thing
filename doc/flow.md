### 项目使用流程

#### 发布部署流程
##### 服务器安装配置
- nginx静态资源服务器配置（C端index.html页面）
- docker环境
- 配置文件config.properties内容可见同级目录下的此文件，此文件的服务器目录（/data/config.properties）

##### 发布配置
- 修改的代码push到GitHub上
- GitHub账号绑定travis-ci，注:travis-ci上需要配置docker.hub的账号
- 在本地配置shell运行环境然后运行根目录下的deploy.sh文件，此文件需要参数 服务名称 版本号

此流程你push的代码会自动编译打包，然后打好的包会push到docker.hub上，运行部署命令会自动把需要部署的image下载部署（注：deploy中的部署服务器的ip）

##### 发布部署是注意事项
- 提交代码时版本号的修改
- 部署时修改命令参数，重启的项目名称和版本号

#### 数据库准备
- 建立recycle-thing数据库
- 执行文件sql.sql文件
- 数据库表sso-user中会用默认的B端用户登陆用户名密码以及权限信息

#### redis配置
- 此项目中部分服务的运行需要使用redis，项目中产生的key以RECYCLE开头

#### 项目产生的文件存储
- 项目运行过程中上传的图片需要阿里云的对象存储，所以需要开通阿里云对象存储账号，账号信息存放至config.properties文件中

#### 前端配置
- 目前前端的静态资源配置都在阿里云的对象存储上（包括B端和C端）
- 配置完前端静态资源还需要配置服务器上的index（B端在oss项目中，C的在nginx的启动文件中）
