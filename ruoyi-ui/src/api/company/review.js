import request from '@/utils/request'

// 查询个体户名字审核列表
export function listReview(query) {
  return request({
    url: '/company/review/list',
    method: 'get',
    params: query
  })
}

// 查询个体户名字审核详细
export function getReview(selfId) {
  return request({
    url: '/company/review/' + selfId,
    method: 'get'
  })
}

// 新增个体户名字审核
export function addReview(data) {
  return request({
    url: '/company/review',
    method: 'post',
    data: data
  })
}

// 修改个体户名字审核
export function updateReview(data) {
  return request({
    url: '/company/review',
    method: 'put',
    data: data
  })
}

// 删除个体户名字审核
export function delReview(selfId) {
  return request({
    url: '/company/review/' + selfId,
    method: 'delete'
  })
}
// 获取编号
export function getCode(query) {
  return request({
    url: '/company/review/getSelfCode',
    method: 'get',
    params: query
  })
}
// 根据编号获取
export function getByCode(query) {
  return request({
    url: '/company/review/getByCode',
    method: 'get',
    params: query
  })
}
export default{addReview,getCode,updateReview,getByCode}