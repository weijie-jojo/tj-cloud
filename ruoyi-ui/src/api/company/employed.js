import request from '@/utils/request'

// 查询个体商户列表
export function listEmployed(query) {
  return request({
    url: '/company/employed/list',
    method: 'get',
    params: query
  })
}

// 查询个体商户详细
export function getEmployed(selfId) {
  return request({
    url: '/company/employed/' + selfId,
    method: 'get'
  })
}

// 新增个体商户
export function addEmployed(data) {
  return request({
    url: '/company/employed',
    method: 'post',
    data: data
  })
}
//查询办理中数据
export function getLeaderByUserId(query){
  return request({
    url:'/system/user/getLeaderByUserId',
    method: 'GET',
    params: query
  })
}
    
// 查询个体商户进度
export function checkdetail(query) {
  return request({
    url: '/company/check/list',
    method: 'get',
    params: query
  })
}


// 新增个体商户进度
export function check(data) {
  return request({
    url: '/company/check',
    method: 'post',
    data: data
  })
}

// 修改个体商户
export function updateEmployed(data) {
  return request({
    url: '/company/employed',
    method: 'put',
    data: data
  })
}

// 删除个体商户
export function delEmployed(selfId) {
  return request({
    url: '/company/employed/' + selfId,
    method: 'delete'
  })
}

// 查询个体商户列表
export function joinList(query) {
  return request({
    url: '/company/employed/joinList',
    method: 'get',
    params: query
  })
}

// 查询个体商户列表
export function joinListEnd(query) {
  return request({
    url: '/company/employed/joinListEnd',
    method: 'get',
    params: query
  })
}
export default {addEmployed,updateEmployed,check,checkdetail,getLeaderByUserId}