import request from '@/utils/request'

export function getLeaderByUserId(params) {
  return request({
    url: 'invoices/expense/getLeaderByUserId',
    method: 'get',
    params
  })
}
export function getDepts() {
  return request({
    url: 'invoices/expense/getAllDept',
    method: 'get',
  })
}
export function getAllPayway() {
  return request({
    url: 'invoices/expense/getAllPayway',
    method: 'get',
  })
}
export function getAllGetCompany() {
  return request({
    url: 'invoices/expense/getAllGetCompany',
    method: 'get',
  })
}
export function getCardInfoBycompany(userId) {
  return request({
    url: 'invoices/expense/getCardInfoBycompany?userId=' + userId,
    method: 'get',

  })
}
export function getUserByUserName(userName) {
  return request({
    url: 'invoices/expense/getUserByUserName?userName=' + userName,
    method: 'get',

  })
}
export function getBankNameBycardId(bankcardId) {
  return request({
    url: 'invoices/expense/getBankNameBycardId?bankcardId='+bankcardId,
    method: 'get',
  })
}
export function addExpense(params) {
  return request({
    url: 'invoices/expense',
    method: 'post',
    params:params
  })
}
//登录用户的
export function getExpenses(params) {

  return request({
    url: 'invoices/expense/getAllExpense',
    method: 'get',
    params
  })
}
//所有的
export function getAllExpenses(params) {

  return request({
    url: 'invoices/expense/getAllExpenses',
    method: 'get',
    params
  })
}
export function getCheckExpense(params) {

  return request({
    url: 'invoices/expense/getCheckExpense',
    method: 'get',
    params
  })
}
export function delExpense(expenseId) {
  return request({
    url: 'invoices/expense/delExpense',
    method: 'delete',
    params:expenseId
  })
}
//删除（伪删）
export function editExpense(expenseId) {
  return request({
    url: 'invoices/expense/editExpense',
    method: 'put',
    params:expenseId
  })
}
//修改报销单（编辑）
export function editExpense2(params) {
  return request({
    url: 'invoices/expense/editExpense2',
    method: 'put',
    params:params
  })
}
//撤回操作
export function editExpenseType(params) {
  return request({
    url: 'invoices/expense/editExpenseType',
    method: 'put',
    params:params
  })
}

export function checkExpense(expenseId) {
  return request({
    url: 'invoices/expense/checkExpense',
    method: 'put',
    params:expenseId
  })
}
export function getCode(params) {
  return request({
    url: 'invoices/expense/getExpenseCode',
    method: 'get',
    params
  })
}
export function getAllUser(params) {
  return request({
    url: 'invoices/expense/getAllUser',
    method: 'get',
    params
  })
}
export function editExpenseByExpenseId(params) {
  return request({
    url: 'invoices/expense/editExpenseByExpenseId',
    method: 'put',
    params
  })
}
export function getPost(userId) {
  return request({
    url: 'invoices/expense/getPost?userId=' + userId,
    method: 'get',

  })
}
export default {getLeaderByUserId,getUserByUserName, getPost,getCode,checkExpense,editExpense,getExpenses, getDepts,getAllPayway,getAllGetCompany,getCardInfoBycompany,getBankNameBycardId,addExpense}

