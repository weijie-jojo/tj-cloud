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

export default {addEmployed}