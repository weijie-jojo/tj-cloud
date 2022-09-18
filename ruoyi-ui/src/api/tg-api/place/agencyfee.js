import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'place/agencyFee/tg',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'place/agencyFee/tg',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'place/agencyFee/tg',
    method: 'put',
    data
  })
}

export function selectFeeByCode(params) {
  return request({
    url: 'place/agencyFee/tg/selectFeeByCode',
    method: 'get',
    params:params
  })
}

export default { add, edit, del,selectFeeByCode }
