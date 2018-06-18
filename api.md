# README
#数据库课设&企业java#
如无特殊说明，return的属性名称和数据库中属性名称相同
所有链接都有前缀`http://localhost:8080`
可以通过
`[api test](http://localhost:8080/api_test.html)`
来测试返回的格式

**添加了jwt支持（jjwt实现）**
在login时服务器返回token，只需要在每次的请求中在head里包含此token，服务器会在需要验证的api做验证。
前台也应该做验证，比如对仅有管理员可访问的api，在请求前检查是否是管理员访问。
页面跳转，后续会提供专门的验证api，会返回token是否正常和此用户的个人信息，由前端验证是否允许跳转。

### update list

增加了：
根据用户id获取已经看过的电影
根据用户id获取全部订单（票号）(订票时间降序）
根据用户id获取未上映的订单 （订票时间降序）
返回未上映的电影

更新了数据库属性 需要重新导入数据库
新的数据库中 测试管理员账户为
```
email: admin@admin
password : admin
```
测试用户账户为
```
email:user@user
password: user
```

- - - -
添加了用id获取用户信息
更新了用户id获取订单的格式

- - - -
添加了获取全部用户的接口
自动排片的接口
检查能否自动排片的接口
更新了数据库
根据场次id返回电影信息和影院信息
- - - -
更新了自动排片的方式
添加了手动排片

添加了删除用户
更改了获取所有用户的内容
- - - -
添加了模糊查询（用户/电影）
- - - -
添加了影院经纬度标记

添加了统计接口：
返回正在上映影片的排片量和观看量
返回年度前X的电影名及其票房
- - - -
添加excel导出
- - - -
添加了统计接口：返回最受欢迎的X个类型及票房
更新了统计接口：统计接口会返回movieName了
添加了上传excel的描述
- - - -

[cinema](https://github.com/demerzel1/MCMS/blob/master/README.md#cinema)
[comment](https://github.com/demerzel1/MCMS/blob/master/README.md#comment)
[movie](https://github.com/demerzel1/MCMS/blob/master/README.md#movie)
[order](https://github.com/demerzel1/MCMS/blob/master/README.md#order)
[time](https://github.com/demerzel1/MCMS/blob/master/README.md#time)
[user](https://github.com/demerzel1/MCMS/blob/master/README.md#user)
[picture](https://github.com/demerzel1/MCMS/blob/master/README.md#picture)
## Token
POST `/token`

头部带token即可
会返回token状态，以下几种。
![](api/0DC29015-B75A-416A-898C-8DC7774329D6.png)


## 统计
#### 获取正在上映影片的排片量和观看量
POST `/movie/getCntListAndOrder`
```
{
"date":"2018-06-18"
}
```
return:
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": [
            {
                "cntOrder": 7,
                "movieId": 9,
                "boxOffice": 280,
                "movieName": "唐伯虎点秋香",
                "cntTime": 27
            },
            {
                "cntOrder": 23,
                "movieId": 15,
                "boxOffice": 920,
                "movieName": "大话西游之大圣娶亲",
                "cntTime": 27
            },
            {
                "cntOrder": 15,
                "movieId": 17,
                "boxOffice": 600,
                "movieName": "天堂电影院",
                "cntTime": 27
            },
            {
                "cntOrder": 12,
                "movieId": 35,
                "boxOffice": 480,
                "movieName": "触不可及",
                "cntTime": 27
            },
            {
                "cntOrder": 8,
                "movieId": 44,
                "boxOffice": 240,
                "movieName": "教父",
                "cntTime": 27
            },
            {
                "cntOrder": 0,
                "movieId": 49,
                "boxOffice": 0,
                "movieName": "大闹天宫",
                "cntTime": 0
            },
            {
                "cntOrder": 0,
                "movieId": 61,
                "boxOffice": 0,
                "movieName": "巴黎淘气帮",
                "cntTime": 0
            },
            {
                "cntOrder": 10,
                "movieId": 64,
                "boxOffice": 300,
                "movieName": "谍影重重",
                "cntTime": 27
            }
        ]
    }
}
```


#### 返回年度前X的电影名及其票房（会返回电影名了，同上，这个就不改了）
POST `/movie/getTopX`
```
{
"top":"5"
}
```
return:(默认按票房从高到低排序）
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": [
            {
                "cntOrder": 23,
                "movieId": 15,
                "boxOffice": 920,
                "cntTime": 27
            },
            {
                "cntOrder": 17,
                "movieId": 63,
                "boxOffice": 680,
                "cntTime": 27
            },
            {
                "cntOrder": 17,
                "movieId": 13,
                "boxOffice": 680,
                "cntTime": 31
            },
            {
                "cntOrder": 16,
                "movieId": 41,
                "boxOffice": 640,
                "cntTime": 27
            },
            {
                "cntOrder": 15,
                "movieId": 28,
                "boxOffice": 600,
                "cntTime": 27
            }
        ]
    }
}
```

#### 返回类型及票房
POST `/movie/getTopType`
```
{
"top":5
}
```
return:
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": [
            {
                "剧情": 12000
            },
            {
                "爱情": 4360
            },
            {
                "奇幻": 3520
            },
            {
                "冒险": 3330
            },
            {
                "喜剧": 3210
            }
        ]
    }
}
```

#### excel导入
POST `/excel/uploadMovieExcel`
传入一个MultipartFile格式的excel
会自动导入
具体excel里面应该怎么写不太记得了..回头看一下

#### excel导出
GET `/excel/getCinema`
`/excel/getUser`
`/excel/getMovie`
`/excel/getTime`
四个同理
return:
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": "/static/0141d88748dc4c46a25eb9b1424aceae.xls"
    }
}
```
直接将返回值前加loaclhost:8080即可下载
在用户点击导出后，直接将用户跳转到如
`localhost:8080/static/0141d88748dc4c46a25eb9b1424aceae.xls`
即可下载

## cinema
#### 获取全部影院信息
GET  `/cinema/getAll`  
return:
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": [
            {
                "id": 1,
                "name": "松江万达电影院",
                "address": "上海市松江区",
                "cityId": 1
            },
            {
                "id": 2,
                "name": "松江地中海影城",
                "address": "s上海市松江区",
                "cityId": 1
            }
        ]
    }
}
```
#### 根据城市id返回电影院信息
POST `/cinema/getByCity`  {“cid”:1}

#### 根据影院id返回信息
POST  `/cinema/getById` {“cid”:1}

#### 通过影片，城市，日期查询影院
POST `/cinema/getByMovieCityDate`
输入json属性为 `mid,cityid,day`
返回影院list

#### 通过影院返回厅信息
POST `/cinema/getHall`
`{“cid”:}`
返回影厅list（包含id和影厅号）


## comment
#### 根据电影返回评论
POST `/comment/getByMid` {“mid”:1}

#### 添加评论
POST `/comment/add`
```
{
"userId":1,
"movieId":3,
"content":"123123",
"grade":10
}
```
如果该用户已评论过此电影，则返回失败。

#### 更改
#### 删除
传入参数为添加评论的参数加上评论id（评论id会在查询时返回）
#### 根据电影和用户返回评论
POST `/comment/getByMovieAndUser`
```
{
"mid":,
"uid":
}
```
如果有会返回评论本身，没有理论上返回null。
## movie
#### 获取全部电影信息
GET 	`/movie/getAll`

#### 根据电影id获取电影信息
POST `/movie/getById` {“mid”:1}

#### 根据日期获取该日期上映的影片
POST `/movie/getByDate` {“day”:”2018-04-12”}

#### 添加影片
POST   `/movie/add`  {“name”:”xx”,”englishname”:”xx”,…}属性和数据库名称相同

![](api/748867F2-E7EC-40A2-8814-650FC0EB61C4.png)
不需要全部有，只有name也可添加

#### 更新影片
POST `/movie/update` 传入参数和添加影片相同，但要包含id（保证存在）

#### 通过id删除影片
POST `/movie/delete` {“mid”:1}

会返回删除成功/删除失败/电影不存在（message内）

#### 获取用户看过的电影
POST `/movie/getWatched` {“uid”:1}
返回列表 内为电影实体

#### 获取未上映的电影
GET `movie/getNotOn`
返回未上映的电影列表

#### 模糊查询名字/搜索
POST `/movie/getByStr`
`{"str":""}`
返回模糊查询得到的结果
查询 “心”
![](api/1A6F5302-2A6F-434B-AF91-927652FEDEAD.png)


## order
#### 买票
POST `/order/add`.
传入： 
tid :场次id
cnt : 购买票数
seat  座位列表 内为[{“row”:2,”col”:3},{“row”:2,”col”:4}] 格式
money :总金额
uid：购买用户

如传入：
```
{
"tid":1,
"uid":1,
"cnt":2,
"seat":[{"row":1,"col":5},{"row":1,"col":6}],
"money":50
}
```

返回：
```
{
    "message": "success",
    "code": 200,
    "data": {
        "1": {
            "id": 15,
            "orderTime": 1523934598932,
            "watchTime": "2018-04-05",
            "cost": 30,
            "userId": 1,
            "timeId": 1,
            "seatId": 5
        },
        "2": {
            "id": 16,
            "orderTime": 1523934599217,
            "watchTime": "2018-04-05",
            "cost": 30,
            "userId": 1,
            "timeId": 1,
            "seatId": 6
        }
    }
}
```
data中map 的key为编号，value为信息。
id为票编号，ordertime为毫秒数表示的时间，watchtime为电影上映时间

不成功则返回：
```
{
    "message": "所选座位已被购买",
    "code": 400,
    "data": {}
}
```

#### 返回用户的全部订单（订票时间降序）
POST `/order/getByUserId`
{“uid”:1}
返回格式如
![](api/00726660-6DD8-4A60-833E-FF471086BD81.png)
key值就是上面的字符串


#### 返回用户未观看的订单（订票时间降序）
POST `/order/getNotWatch`
{“uid”:1}
同上


## time
#### 根据电影id,影院id,日期，返回此日电影场次信息
POST `/time/getTime` 
```
{
    "cid":1,
    "mid":1,
    "day":"2018-02-14"
}
```
返回格式如下：
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": [
            {
                "start_time": 1518611833000,
                "time_id": 3,
                "cost": 50,
                "hall_number": 2
            },
            {
                "start_time": 1518580867000,
                "time_id": 6,
                "cost": 100,
                "hall_number": 1
            }
        ]
    }
}
```
hall_number 为 几号厅
time_id 为场次id

#### 根据场次id获取该场次已被购买的座位
POST `/time/getSaledSeat`  {“tid”:1}

返回格式如下
返回的是该场次已经被购买的座位
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": [
            {
                "id": 3,
                "row": 1,
                "col": 3,
                "hallId": 1
            },
            {
                "id": 2,
                "row": 1,
                "col": 2,
                "hallId": 1
            },
            {
                "id": 1,
                "row": 1,
                "col": 1,
                "hallId": 1
            },
            {
                "id": 4,
                "row": 1,
                "col": 4,
                "hallId": 1
            }
        ]
    }
}
```

#### 根据影院、日期返回当日该影院所有电影的场次
POST  `/time/getByCidAndDate`
传入
```
{
"cid":1,
"day":"2018-02-14"
}
```

返回：
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": {
            "1": [
                {
                    "id": 3,
                    "startTime": 1518611833000,
                    "endTime": 1523651835000,
                    "cost": 50,
                    "movieId": 1,
                    "hallId": 1
                },
                {
                    "id": 6,
                    "startTime": 1518580867000,
                    "endTime": 1523851264000,
                    "cost": 100,
                    "movieId": 1,
                    "hallId": 1
                }
            ],
            "3": []
        }
    }
}
```
第二层data中为一个map
key值为电影id，value为一个list，为date对应的日期当天的电影场次

#### 根据场次id返回电影信息和影院信息
POST `/time/getMovieByTimeId`
{“tid”:1}

返回信息为
![](api/20177F5B-D3F7-40E6-A6C1-B7D04F374422.png)


#### 自动排片
POST `/time/autoAdd`
```
{
"day":"",
"mid":,  //电影id
"hid":,  //厅id
"cost":,
"cnt": //要排的场数
}
```

![](api/7D6D24BC-BD50-47CF-A267-5E41AE62D648.png)

返回对此厅的排片list
每个厅每天自动排片只能排3场
开始时间分别为8:30,12:30,18:30
从早到晚自动排，比如要排一场，会优先排8:30的。
排片前需先调用checkauto检查可排片的个数
#### 检查能否自动排片
POST  `/time/checkAuto`
```
{
"day":,
"hid":,
"mid":
}
```
会返回当天剩余场次的个数
![](api/0280CA18-5ABC-435B-B2B7-954989527BBE.png)

#### 手动排片
POST `/time/add`
```
{
	"beginTime":"2018-05-15 08:20:00",
	"endTime":"2018-05-15 09:20:00",
	"mid":7,
  "hid":3,
	"cost":30
}
```
严格按照”yyyy-MM-dd hh:MM:ss”的格式传时间，否则会报错。

![](api/4A5B3633-51CC-4E59-ACF3-348A92162B54.png)


## user
#### 注册
POST `/user/register` 
格式如
```
{
"name":"11111",
"email":"11111",
"password":"11111"
"tel":"111"
}
```
前面三个必须有，tel可以没有

如注册成功，返回如下
```
{
    "message": "用户注册成功",
    "code": 200,
    "data": {
        "data": {
            "id": 19,
            "name": "11111",
            "password": "11111",
            "tel": null,
            "money": 0,
            "isAdmin": 0,
            "email": "11111",
            "avatar": null
        }
    }
}
```

如果注册不成功（email已经存在）
```
{
    "message": "违反主键/唯一约束条件",
    "code": 400,
    "data": {}
}
```


#### 登陆
POST `/user/login`  {“email”:”xx”,”password”:”xx”}

成功会返回信息
```
{
    "message": "success",
    "code": 200,
    "data": {
        "data": {
            "id": 19,
            "name": "11111",
            "password": "11111",
            "tel": null,
            "money": 0,
            "isAdmin": 0,
            "email": "11111",
            "avatar": null
        }
    }
}
```
不成功会返回
```
{
    "message": "用户名/密码错误",
    "code": 400,
    "data": {}
}
```

#### 更改信息
POST `/user/update`
同注册

#### 获取全部用户
GET `/user/getAll`
返回list 包含所有用户信息（非管理员用户）

#### 删除用户
POST `/user/delete`
```
{
"uid":1
}
```
返回成功或失败

#### 模糊查询用户
POST `/user/getByStr`

```
{
"str":"",
"type":
}
```
type 
1 名称
2 邮箱
3 电话

![](api/F5B09ACE-40E3-4226-88EE-C74175BEF571.png)


## picture
#### 上传海报
POST `/pic/uploadPost` 
返回url
```
“{"message":"success","code":200,"data":{"data”:”/static/images/posts/d2fa22e624d34f8ca6227807ef6fd522.jpeg"}}”
```

在返回的url前加localhost:8080 就可以访问
`localhost:8080/static/images/posts/d2fa22e624d34f8ca6227807ef6fd522.jpeg`

#### 上传头像
POST `/pic/uploadAvatar`
