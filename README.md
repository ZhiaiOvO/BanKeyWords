# BanKeyWords - Minecraft敏感词过滤插件

[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 📖 功能特性
- 实时监控玩家聊天内容
- 正则表达式匹配敏感词
- 自动替换敏感词为星号
- 支持命令管理敏感词库
- 热重载配置文件
- 权限系统集成

## 🛠️ 安装指南
1. 将编译后的jar文件放入服务器的`plugins`目录
2. 重启服务器
3. 自动生成`keywords.yml`配置文件（位于`plugins/BanKeyWords/`）

## 📝 命令列表
| 命令           | 权限节点                  | 描述                 |
|----------------|--------------------------|----------------------|
| /addkeywords   | bankeywords.addkeywords  | 添加新的敏感词       |
| /bkwreload     | bankeywords.reload       | 重载配置文件         |
| /keywordlist   | bankeywords.keywordlist  | 查看当前敏感词列表   |

## ⚙️ 配置说明
`keywords.yml`文件格式示例(不用加引号)：
```yaml
words:
- 违禁词1
- 违禁词2
```


