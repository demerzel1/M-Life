# README
#数据库课设&企业java#
如无特殊说明，return的属性名称和数据库中属性名称相同
所有链接都有前缀`http://localhost:8080`
可以通过
`[api test](http://localhost:8080/api_test.html)`
来测试返回的格式

添加了jwt支持（jjwt实现）

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
POST `/movie/getByMovieCityDate`
输入json属性为 `mid,cityid,day`
返回影院list



## comment
#### 根据电影返回评论
POST `/comment/getByMid` {“mid”:1}

## movie
#### 获取全部电影信息
GET 	`/movie/getAll`

#### 根据电影id获取电影信息
POST `/movie/getById` {“mid”:1}

#### 根据日期获取该日期上映的影片
POST `/movie/getByDate` {“day”:”2018-04-12”}

#### 添加影片
POST   `/movie/add`  {“name”:”xx”,”englishname”:”xx”,…}属性和数据库名称相同

![](README/748867F2-E7EC-40A2-8814-650FC0EB61C4.png)
不需要全部有，只有name也可添加

#### 更新影片
POST `/movie/update` 传入参数和添加影片相同，但要包含id（保证存在）

#### 通过id删除影片
POST `/movie/delete` {“mid”:1}

会返回删除成功/删除失败/电影不存在（message内）


## order
#### 买票
POST `/order/add`.
传入： 
tid :场次id
cnt : 购买票数
seat  座位列表 内为[{"row":2,"col":3},{"row":2,"col”:4}] 格式
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