import request from '@/utils/request'

// 查询个体户注册申请信息列表
export function listInfo(query) {
  return request({
    url: '/company/info/tc/list',
    method: 'get',
    params: query
  })
}

// 查询个体户注册申请信息详细
export function getInfo(id) {
  return request({
    url: '/company/info/tc/' + id,
    method: 'get'
  })
}

// 新增个体户注册申请信息
export function addInfo(data) {
  return request({
    url: '/company/tc/info',
    method: 'post',
    data: data
  })
}

// 修改个体户注册申请信息
export function updateInfo(data) {
  return request({
    url: '/company/info/tc',
    method: 'put',
    data: data
  })
}

// 删除个体户注册申请信息
export function delInfo(id) {
  return request({
    url: '/company/info/tc' + id,
    method: 'delete'
  })
}
export default {addInfo,updateInfo}