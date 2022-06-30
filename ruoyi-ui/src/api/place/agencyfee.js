import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/agencyFee',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/agencyFee/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/agencyFee',
    method: 'put',
    data
  })
}

export function selectFeeByCode(params) {
  return request({
    url: 'api/agencyFee/selectFeeByCode',
    method: 'get',
    params:params
  })
}

export default { add, edit, del,selectFeeByCode }
