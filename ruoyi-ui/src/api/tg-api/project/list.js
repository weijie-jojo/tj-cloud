import request from '@/utils/request'
//获取收款列表不分页
export function receiveList2(query) {
  return request({
    url: 'project/receive/tg/getInfoByCode2',
    method: 'get',
    params: query
  })
}
//获取出款列表
export function payList2(query) {
  return request({
    url: '/project/pay/tg/getInfoByCode2',
    method: 'get',
    params: query
  })
}
//甲方详情
export function detailPurch(purchId) {
  return request({
    url: '/project/company/tg/'+purchId,
    method: 'get',
   })
}
//甲方修改
export function editPurch(data) {
  return request({
    url: '/project/company/tg',
    method: 'put',
    data: data
   })
}
//出款详情
export function detailPay(payId) {
  return request({
    url: '/project/pay/tg/'+payId,
    method: 'get',
   
  })
}
//收款详情
export function detailCollect(receiveId) {
  return request({
    url: '/project/receive/tg/'+receiveId,
    method: 'get',
   
  })
}
//获取出款列表
export function payList(query) {
  return request({
    url: '/project/pay/tg/getInfoByCode',
    method: 'get',
    params: query
  })
}
//出款新增
export function addPay(data) {
  return request({
    url: '/project/pay/tg',
    method: 'post',
    data: data
  })
}
//出款列表修改
export function editPay(data) {
  return request({
    url: '/project/pay/tg',
    method: 'put',
    data: data
  })
}

//出款删除
export function delPay(payIds) {
  return request({
    url: 'project/pay/tg/'+payIds,
    method: 'delete',
 })
}

//获取项目收款详情计算已收账款
export function finshReceiveList(query) {
  return request({
    url: 'project/receive/tg/getInfoByCode2',
    method: 'get',
    params: query
  })
}


//获取项目出款详情计算已出账款
export function finshPayList(query) {
  return request({
    url: 'project/pay/tg/getInfoByCode2',
    method: 'get',
    params: query
  })
}

//获取收款列表
export function receiveList(query) {
  return request({
    url: 'project/receive/tg/getInfoByCode',
    method: 'get',
    params: query
  })
}

//收款列表新增
export function addReceive(data) {
  return request({
    url: 'project/receive/tg',
    method: 'post',
    data: data
  })
}

//收款列表修改
export function editReceive(data) {
  return request({
    url: 'project/receive/tg',
    method: 'put',
    data: data
  })
}

//收款删除
export function delReceive(payReceiveIds) {
  return request({
    url: 'project/receive/tg/'+payReceiveIds,
    method: 'delete',
 })
}
//获取收款流水号code 
export function getReceiveCode(query) {
  return request({
    url: '/project/receive/tg/getCode',
    method: 'get',
    params: query
   })
}
//获取出款流水号code 
export function getPayCode(query) {
  return request({
    url: '/project/pay/tg/getCode',
    method: 'get',
    params: query
   })
}
//获取购货单位（甲方）列表分页
export function getPurchPage(query){
  return request({
    url:'/project/company/tg/list1',
    method:'get',
    params:query
  })
}
//获取购货单位（甲方）列表
export function getPuJialist(query) {
  return request({
    url: '/project/company/tg/list',
    method: 'get',
    params: query
  })
}

//获取销货单位（乙方）
export function ownlist(query) {
  return request({
    url: 'project/employed/tg/getByUserIndusty',
    method: 'get',
    params: query
  })
}

//获取项目列表
export function list(query) {
  return request({
    url: 'project/project/tg/list',
    method: 'get',
    params: query
  })
}

//获取项目详情
export function detail(query) {
  return request({
    url: 'project/project/tg/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}

//项目新增
export function add(data) {
  return request({
    url: 'project/project/tg',
    method: 'post',
    data: data
  })
}
//购货单位（甲方）新增
export function addJia(data) {
  return request({
    url: '/project/company/tg',
    method: 'post',
    data: data
  })
}
//获取项目编码
export function getcode(query){
    return request({
        url:'project/project/tg/getCode',
        method:'get',
        params:query   
    })
}
//项目删除
export function del(projectIds) {
  return request({
    url: 'project/project/tg/'+projectIds,
    method: 'delete',
 })
}
//项目修改
export function edit(data) {
  return request({
    url: 'project/project/tg',
    method: 'put',
    data:data
  })
}
//获取项目各个审核列表标签 统计数量
export function getProjectCount(query){
  return request({
    url: 'project/project/tg/getCount',
    method: 'get',
    params: query
  })
}
//获取销货单位（乙方）
export function getinfoByUserId(data){
  return request({
    url:'project/place/tg/getInfoByUserId',
    method:'get',
    params:data 
  })
}

// 新增个体商户进度
export function check(data) {
  return request({
    url: '/project/check/tg',
    method: 'post',
    data: data
  })
}

// 查询个体商户进度
export function checkdetail(query) {
  return request({
    url: '/project/check/tg/list',
    method: 'get',
    params: query
  })
}
//获取票据标签页 统计数量
export function getCount(query){
  return request({
    url: '/project/project/tg/getCount3',
    method: 'get',
    params: query
  })
}


export default {receiveList2,payList2,detailPurch,editPurch,detailPay,detailCollect,getPurchPage,payList,editPay,addPay,delPay,finshReceiveList,finshPayList,addReceive,editReceive,delReceive,receiveList,getReceiveCode,getPayCode,addJia,getPuJialist,getProjectCount ,list , edit, del, getcode, getinfoByUserId ,add,ownlist,detail,check,checkdetail,getCount}