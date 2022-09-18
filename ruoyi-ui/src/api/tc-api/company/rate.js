import request from '@/utils/request'

// 查询个体户行业类型税率列表
export function listRate(query) {
  return request({
    url: '/company/rate/tc/list',
    method: 'get',
    params: query
  })
}

// 查询个体户行业类型税率详细
export function getRate(industryId) {
  return request({
    url: '/company/rate/tc/' + industryId,
    method: 'get'
  })
}

// 新增个体户行业类型税率
export function addRate(data) {
  return request({
    url: '/company/rate/tc',
    method: 'post',
    data: data
  })
}

// 修改个体户行业类型税率
export function updateRate(data) {
  return request({
    url: '/company/rate/tc',
    method: 'put',
    data: data
  })
}

// 删除个体户行业类型税率
export function delRate(industryId) {
  return request({
    url: '/company/rate/tc/' + industryId,
    method: 'delete'
  })
}

// 查询个体户行业类型税率列表（不分页 查所有）
export function getAllRate(query) {
  return request({
    url: '/company/rate/tc/all',
    method: 'get',
    params: query
  })
}
export default {getAllRate}