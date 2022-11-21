###### TODO List
- Service实现类Banmaerp 的APIs
- JPA保存1:1 Banmaerp对象到Mysql中，关联关系在业务层上做（即选品中心扩展字段）
- 定时器拉取Banmaerp 订单，通过redisson加锁，避免多实例重复执行
- @annotation：自动保存到mysql，避免手动写代码
- 补斑马新增的接口
- refreshToken要写上，防止access_token打到限流
- 打包jar，写demo
**** 斑马erp的加密方式有问题，用IP白名单的话需要经常手动添加IP。
- 如果是IP白名单（可配置）的话先完成
- 用restTemplate避免问题


###### 注意事项
- 斑马erp的文档和API实际返回的数据结构是不大一样，特别是日期时间格式是完全不统一，没有遵守统一的格式，会造成序列化出问题。
要嘛单独为涉及时间格式的字段写序列化反序列化接口，要嘛全部用字符串，但是用字符串就无法约束入参合法性

- 斑马erp的加密方式有问题，暂时不支持混合的方式

-