import request from '@/utils/request'

// 查询个体商户列表
export function listEmployed(query) {
  return request({
    url: '/company/employed/tc/list',
    method: 'get',
    params: query
  })
}

// 查询个体商户详细
export function getEmployed(selfId) {
  return request({
    url: '/company/employed/tc/' + selfId,
    method: 'get'
  })
}

// 新增个体商户
export function addEmployed(data) {
  return request({
    url: '/company/employed/tc',
    method: 'post',
    data: data
  })
}

// 获取工商表格
export function getWord(data) {
  return request({
    url: '/company/files/tc/getWord',
    method: 'post',
    params: data
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
    url: '/company/check/tc/list',
    method: 'get',
    params: query
  })
}


// 新增个体商户进度
export function check(data) {
  return request({
    url: '/company/check/tc',
    method: 'post',
    data: data
  })
}

// 修改个体商户
export function updateEmployed(data) {
  return request({
    url: '/company/employed/tc',
    method: 'put',
    data: data
  })
}

// 删除个体商户
export function delEmployed(selfIds) {
  return request({
    url: '/company/employed/tc/' + selfIds,
    method: 'delete'
  })
}

// 查询个体商户列表
export function joinList(query) {
  return request({
    url: '/company/employed/tc/joinList',
    method: 'get',
    params: query
  })
}
export function getCount(query){
  return request({
    url: '/company/employed/tc/getCount',
    method: 'get',
    params: query
  })
}

export function getCounts(query){
  return request({
    url: '/company/employed/tc/getCounts',
    method: 'get',
    params: query
  })
}


// 查询个体商户列表
export function joinList2(query) {
  return request({
    url: '/company/employed/tc/joinList2',
    method: 'get',
    params: query
  })
}

// 查询个体商户列表
export function joinListEnd(query) {
  return request({
    url: '/company/employed/tc/joinListEnd',
    method: 'get',
    params: query
  })
}

// 查询个体商户列表
export function getAllUser() {
  return request({
    url: '/company/employed/tc/getAllUser',
    method: 'get'
  })
}
export default {getAllUser,addEmployed,updateEmployed,check,checkdetail,getLeaderByUserId,delEmployed,getWord,joinList2,joinList,getCount,getCounts}