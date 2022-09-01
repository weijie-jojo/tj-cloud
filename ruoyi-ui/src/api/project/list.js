import request from '@/utils/request'

export function list(query) {
  return request({
    url: 'project/project/list',
    method: 'get',
    params: query
  })
}
export function getPuJialist(query) {
  return request({
    url: '/project/company/list',
    method: 'get',
    params: query
  })
}

export function detail(query) {
  return request({
    url: 'project/project/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}
export function ownlist(query) {
  return request({
    url: 'project/employed/getByUserIndusty',
    method: 'get',
    params: query
  })
}
export function add(data) {
  return request({
    url: 'project/project',
    method: 'post',
    data: data
  })
}
export function addJia(data) {
  return request({
    url: '/project/company',
    method: 'post',
    data: data
  })
}
export function getcode(query){
    return request({
        url:'project/project/getCode',
        method:'get',
        params:query   
    })
}
export function del(projectIds) {
  return request({
    url: 'project/project/'+projectIds,
    method: 'delete',
 })
}

export function edit(data) {
  return request({
    url: 'project/project',
    method: 'put',
    data:data
  })
}

export function getProjectCount(query){
  return request({
    url: 'project/project/getCount',
    method: 'get',
    params: query
  })
}

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

export function getCount(query){
  return request({
    url: '/project/project/getCount3',
    method: 'get',
    params: query
  })
}


export default {addJia,getPuJialist,getProjectCount ,list , edit, del, getcode , getinfoByUserId ,add,ownlist,detail,check,checkdetail,getCount}