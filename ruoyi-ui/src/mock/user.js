const Mock = require("mockjs");
let userList = Mock.mock({
  // 属性 list 的值是一个数组，其中含有 1 到 10 个元素
  'list|10': [{
    "selfId|+1": 1, //随机id
    name: '@cname', //随机名称
    nickName: '@last', //随机昵称
    phone: /^1[34578]\d{9}$/, //随机电话号码
    'age|11-99': 1, //年龄
    address: '@county(true)', //随机地址
    email: '@email', //随机邮箱
    isMale: '@boolean', //随机性别
    createTime: '@datetime', //创建时间
  }]
})
export default {
  'get|/getUsers': option => {
    return {
      status: 200,
      message: 'success',
      data: userList
    };
  }
}


