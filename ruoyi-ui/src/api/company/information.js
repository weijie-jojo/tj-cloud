import request from '@/utils/request'

// 查询员工信息列表
export function listInformation(query) {
  return request({
    url: '/company/information/list',
    method: 'get',
    params: query
  })
}

// 查询员工信息详细
export function getInformation(userId) {
  return request({
    url: '/company/information/' + userId,
    method: 'get'
  })
}

// 新增员工信息
export function addInformation(data) {
  return request({
    url: '/company/information',
    method: 'post',
    data: data
  })
}

// 修改员工信息
export function updateInformation(data) {
  return request({
    url: '/company/information',
    method: 'put',
    data: data
  })
}

// 删除员工信息
export function delInformation(userId) {
  return request({
    url: '/company/information/' + userId,
    method: 'delete'
  })
}

// 查询员工信息列表（不分页 查所有）
export function getAllInformation(query) {
  return request({
    url: '/company/information/all',
    method: 'get',
    params: query
  })
}

export default {getAllInformation,getInformation}