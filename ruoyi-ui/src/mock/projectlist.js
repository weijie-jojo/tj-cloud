const Mock = require("mockjs");
let userList = Mock.mock({
  // 属性 list 的值是一个数组，其中含有 1 到 10 个元素
  'list|25': [{
    "selfId|+1": 1, //随机id
    firstParty: '@cname', //随机名称
    secondParty: '@cname', //随机昵称
    projectName:'@cparagraph(2)',
    username:'@cname',
    "endStatus|1": [
      "1",
      "0",
      
    ],
    // phone: /^1[34578]\d{9}$/, //随机电话号码
    // 'age|11-99': 1, //年龄
    // address: '@county(true)', //随机地址
    // email: '@email', //随机邮箱
    // isMale: '@boolean', //随机性别
    createTime: '@datetime', //创建时间
  }]
})
Mock.mock(/\/getProjectList*?/, 'get', (options) => {
  console.log(options);
//  console.log(decodeURI(options.url));
  let submitList=userList;
  let lengths=userList.list.length;

  console.log(submitList.list.length);
  let url=decodeURI(options.url);
  
  var temp2 = url.split('?')[1];
  const query =new URLSearchParams('?'+temp2);
  let secondParty= query.get('secondParty');
  let projectTime= query.get('projectTime');
  let projectStatus=query.get('projectStatus');
  let pageNum=query.get('pageNum');
  let pageSize=query.get('pageSize');
  console.log(pageNum);
  console.log(pageSize);
  if(pageNum*1-1<1){
    pageNum=pageNum*1-1;
  }else if(pageNum*1>0){
    pageNum=pageSize*1*(pageNum*1-1);
    pageSize=pageSize*(pageNum*1)-1;
  }
  
  
    let newlist=[];  
    let flag=1;
    for(let i in submitList.list){
      // if(projectTime){
      //   if(submitList[i].projectTime==projectTime){

      //   }
      // }
      if(secondParty){
        if(submitList.list[i].secondParty==secondParty){
          newlist.push(submitList.list[i]);
          flag=2;
        }else{
          flag=2;
        }
      } 
      if(projectStatus){
        if(submitList.list[i].projectStatus==projectStatus){
          newlist.push(submitList.list[i]);
          flag=2;
        } else{
          flag=2;
        }
      }           
    }
   
    if(flag==2){
      submitList={
        list:newlist
      };
      console.log(222);
      lengths=newlist.list.length;
    }
    
  
 
   

  return {
      code: 200,
      msg:'查询成功',
      rows: submitList.list.slice(pageNum,pageSize),
      total:lengths
    };
})
export default {

  // 'get|/getProjectList': option => {
  //   console.log(option);
  //   return {
  //     code: 200,
  //     msg:'查询成功',
  //     rows: userList,
  //     total:userList.list.length
  //   };
  // }
}


