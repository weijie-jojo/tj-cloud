import request from '@/utils/request'

export function getAllCheck(params) {
  return request({
    url: 'invoices/checkInvoices/getAllCheck',
    method: 'get',
    params
  })
}
export function addCheckInvoices(params) {
  return request({
    url: 'invoices/checkInvoices/addCheckInvoices',
    method: 'post',
    params:params
  })
}
export default {getAllCheck}

