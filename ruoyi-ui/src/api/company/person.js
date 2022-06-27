import request from '@/utils/request'

// 查询个体商户法人列表
export function listPerson(query) {
  return request({
    url: '/company/person/list',
    method: 'get',
    params: query
  })
}

// 查询个体商户法人详细
export function getPerson(legalPersonId) {
  return request({
    url: '/company/person/' + legalPersonId,
    method: 'get'
  })
}

// 新增个体商户法人
export function addPerson(data) {
  return request({
    url: '/company/person',
    method: 'post',
    data: data
  })
}

// 修改个体商户法人
export function updatePerson(data) {
  return request({
    url: '/company/person',
    method: 'put',
    data: data
  })
}

// 删除个体商户法人
export function delPerson(legalPersonId) {
  return request({
    url: '/company/person/' + legalPersonId,
    method: 'delete'
  })
}

// 查询个体商户法人列表（不分页 查所有）
export function getAllPerson(query) {
  return request({
    url: '/company/person/all',
    method: 'get',
    params: query
  })
}

export default{getAllPerson,addPerson,updatePerson}