# flowable-sample

## 安装flowable-ui
- docker安装flowable-ui
```
docker run -d --name fu -p 8080:8080 flowable/flowable-ui
```
- 访问地址： http://ip:8080/flowable-ui
- 账号: admin 密码: test

## 测试

```
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='user';
INSERT INTO `t_user` (`id`, `name`) VALUES ('2', 'ls');

```


## 参考
- https://www.bilibili.com/video/BV1gnkJYJEbg/
- https://blog.csdn.net/qq_34162294/article/details/143806673
- https://blog.51cto.com/u_16213663/10188533
