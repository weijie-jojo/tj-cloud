import request from '@/utils/request'

export function getDepts() {
  return request({
    url: 'api/expense/getAllDept',
    method: 'get',
  })
}
export function getAllPayway() {
  return request({
    url: 'api/expense/getAllPayway',
    method: 'get',
  })
}
export function getAllGetCompany() {
  return request({
    url: 'api/expense/getAllGetCompany',
    method: 'get',
  })
}
export function getCardInfoBycompany(userId) {
  return request({
    url: 'api/expense/getCardInfoBycompany?userId=' + userId,
    method: 'get',
    
  })
}
export function getBankNameBycardId(bankcardId) {
  return request({
    url: 'api/expense/getBankNameBycardId?bankcardId='+bankcardId,
    method: 'get',
  })
}
export function addExpense(params) {
  return request({
    url: 'api/expense/addExpense',
    method: 'post',
    params:params
  })
}
//登录用户的
export function getExpenses(params) {
  
  return request({
    url: 'api/expense/getAllExpense',
    method: 'get',
    params
  })
}
//所有的
export function getAllExpenses(params) {
  
  return request({
    url: 'api/expense/getAllExpenses',
    method: 'get',
    params
  })
}
export function getCheckExpense(params) {
  
  return request({
    url: 'api/expense/getCheckExpense',
    method: 'get',
    params
  })
}
export function delExpense(expenseId) {
  return request({
    url: 'api/expense/delExpense',
    method: 'delete',
    params:expenseId
  })
}
//删除（伪删）
export function editExpense(expenseId) {
  return request({
    url: 'api/expense/editExpense',
    method: 'put',
    params:expenseId
  })
}
//修改报销单（编辑）
export function editExpense2(params) {
  return request({
    url: 'api/expense/editExpense2',
    method: 'put',
    params:params
  })
}
//撤回操作
export function editExpenseType(params) {
  return request({
    url: 'api/expense/editExpenseType',
    method: 'put',
    params:params
  })
}

export function checkExpense(expenseId) {
  return request({
    url: 'api/expense/checkExpense',
    method: 'put',
    params:expenseId
  })
}
export function getCode(params) {
  return request({
    url: 'api/expense/getExpenseCode',
    method: 'get',
    params
  })
}
export function getAllUser(params) {
  return request({
    url: 'api/expense/getAllUser',
    method: 'get',
    params
  })
}
export function editExpenseByExpenseId(params) {
  return request({
    url: 'api/expense/editExpenseByExpenseId',
    method: 'put',
    params
  })
}
export default { getCode,checkExpense,editExpense,getExpenses, getDepts,getAllPayway,getAllGetCompany,getCardInfoBycompany,getBankNameBycardId,addExpense}

