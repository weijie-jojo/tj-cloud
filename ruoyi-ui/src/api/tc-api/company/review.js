import request from '@/utils/request'

// 查询个体户名字审核列表
export function listReview(query) {
  return request({
    url: '/company/review/tc/list',
    method: 'get',
    params: query
  })
}

// 查询个体户名字审核列表(连表)
export function reviewjoinList(query) {
  return request({
    url: '/company/review/tc/joinList',
    method: 'get',
    params: query
  })
}

// 查询个体户名字审核详细
export function getReview(selfId) {
  return request({
    url: '/company/review/tc/' + selfId,
    method: 'get'
  })
}

// 新增个体户名字审核
export function addReview(data) {
  return request({
    url: '/company/review/tc',
    method: 'post',
    data: data
  })
}

// 修改个体户名字审核
export function updateReview(data) {
  return request({
    url: '/company/review/tc',
    method: 'put',
    data: data
  })
}

// 删除个体户名字审核
export function delReview(selfId) {
  return request({
    url: '/company/review/tc/' + selfId,
    method: 'delete'
  })
}
// 获取编号
export function getCode(query) {
  return request({
    url: '/company/review/tc/getSelfCode',
    method: 'get',
    params: query
  })
}
// 根据编号获取
export function getByCode(query) {
  return request({
    url: '/company/review/tc/getByCode',
    method: 'get',
    params: query
  })
}
export default{addReview,getCode,updateReview,getByCode}