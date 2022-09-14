import request from '@/utils/request'

export function addBorrow(params) {
  return request({
    url: 'invoices/borrow/addBorrow',
    method: 'post',
    params:params
  })
}
//登录用户的
export function getBorrow(params) {
  return request({
    url: 'invoices/borrow/getAllBorrow',
    method: 'get',
    params
  })
}
//登录用户历史单据
export function getBorrow2(params) {
  return request({
    url: 'invoices/borrow/getAllBorrow2',
    method: 'get',
    params
  })
}
//所有的
export function getAllBorrows(params) {
  return request({
    url: 'invoices/borrow/getAllBorrows',
    method: 'get',
    params
  })
}
export function getCheckBorrow(params) {
  return request({
    url: 'invoices/borrow/getCheckBorrow',
    method: 'get',
    params
  })
}
//删除（伪删）
export function editBorrow(borrowId) {
  return request({
    url: 'invoices/borrow/editBorrow',
    method: 'put',
    params:borrowId
  })
}
//修改借支单（编辑）
export function editBorrow2(params) {
  return request({
    url: 'invoices/borrow/editBorrow2',
    method: 'put',
    params:params
  })
}
//撤回操作
export function editBorrowType(params) {
  return request({
    url: 'invoices/borrow/editBorrowType',
    method: 'put',
    params:params
  })
}

export function editBorrowByBorrowId(params) {
  return request({
    url: 'invoices/borrow/editBorrowByBorrowId',
    method: 'put',
    params
  })
}

export function getCode(params) {
  return request({
    url: 'invoices/borrow/getBorrowCode',
    method: 'get',
    params
  })
}

export function getAllCompany() {
  return request({
    url: 'invoices/borrow/getAllCompany',
    method: 'get',
  })
}
export function getAllGetUser() {
  return request({
    url: 'invoices/borrow/getAllGetUser',
    method: 'get',
  })
}

export default {getBorrow2,getCode,editBorrow,getBorrow,addBorrow,getAllGetUser}

