import request from '@/utils/request'

//获取项目类别
export function list(query) {
  return request({
    url: 'project/project/list',
    method: 'get',
    params: query
  })
}
//获取流水号code
export function getReceiveCode() {
  return request({
    url: '/project/receive/getCode',
    method: 'get',
    
  })
}
//获取甲方列表
export function getPuJialist(query) {
  return request({
    url: '/project/company/list',
    method: 'get',
    params: query
  })
}

//获取项目详情
export function detail(query) {
  return request({
    url: 'project/project/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}
//获取乙方
export function ownlist(query) {
  return request({
    url: 'project/employed/getByUserIndusty',
    method: 'get',
    params: query
  })
}
//项目新增
export function add(data) {
  return request({
    url: 'project/project',
    method: 'post',
    data: data
  })
}
//甲方新增
export function addJia(data) {
  return request({
    url: '/project/company',
    method: 'post',
    data: data
  })
}
//获取项目编码
export function getcode(query){
    return request({
        url:'project/project/getCode',
        method:'get',
        params:query   
    })
}
//项目删除
export function del(projectIds) {
  return request({
    url: 'project/project/'+projectIds,
    method: 'delete',
 })
}
//项目修改
export function edit(data) {
  return request({
    url: 'project/project',
    method: 'put',
    data:data
  })
}
//获取项目各个审核列表标签 统计数量
export function getProjectCount(query){
  return request({
    url: 'project/project/getCount',
    method: 'get',
    params: query
  })
}
//获取乙方
export function getinfoByUserId(data){
  return request({
    url:'project/place/getInfoByUserId',
    method:'get',
    params:data 
  })
}

// 新增个体商户进度
export function check(data) {
  return request({
    url: '/project/check',
    method: 'post',
    data: data
  })
}

// 查询个体商户进度
export function checkdetail(query) {
  return request({
    url: '/project/check/list',
    method: 'get',
    params: query
  })
}
//获取票据标签页 统计数量
export function getCount(query){
  return request({
    url: '/project/project/getCount3',
    method: 'get',
    params: query
  })
}


export default {getReceiveCode,addJia,getPuJialist,getProjectCount ,list , edit, del, getcode , getinfoByUserId ,add,ownlist,detail,check,checkdetail,getCount}