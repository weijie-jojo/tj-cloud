import request from '@/utils/request'

// 查询纳税账户

export function listInfo(query) {
  return request({
    url: '/company/payTaxInfo/list',
    method: 'get',
    params: query
  })
}

// 查询纳税账户

export function getInfo(taxId) {
  return request({
    url: '/company/payTaxInfo/' + taxId,
    method: 'get'
  })
}

// 新增纳税账户

export function addInfo(data) {
  return request({
    url: '/company/payTaxInfo',
    method: 'post',
    data: data
  })
}

// 修改纳税账户

export function updateInfo(data) {
  return request({
    url: '/company/payTaxInfo',
    method: 'put',
    data: data
  })
}

// 删除纳税账户

export function delInfo(taxId) {
  return request({
    url: '/company/payTaxInfo' + taxId,
    method: 'delete'
  })
}

// 查询纳税账户
export function all() {
  return request({
    url: '/company/payTaxInfo/all',
    method: 'get',
  
  })
}
export default{all}