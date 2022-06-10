import request from '@/utils/request'

export function getAllInvoice(params) {
  return request({
    url: 'api/invoicesMgr/getAllInvoice',
    method: 'get',
    params
  })
}
export function addInvoice(params) {
  return request({
    url: 'api/invoicesMgr/addInvoice',
    method: 'post',
    params:params
  })
}
export default {getAllInvoice,addInvoice}

