import request from '@/utils/request'

export function addTravelExpense(params) {
  return request({
    url: 'api/travelExpense/addTravelExpense',
    method: 'post',
    params:params
  })
}
//登录用户的
export function getTravelExpense(params) {
  
  return request({
    url: 'api/travelExpense/getTravelExpense',
    method: 'get',
    params
  })
}
//所有的
export function getAllTravelExpense(params) {
  
  return request({
    url: 'api/travelExpense/getAllTravelExpense',
    method: 'get',
    params
  })
}
export function getCheckTravelExpense(params) {
  
  return request({
    url: 'api/travelExpense/getCheckTravelExpense',
    method: 'get',
    params
  })
}
//删除（伪删）
export function editTravelExpense(expenseId) {
  return request({
    url: 'api/travelExpense/editTravelExpense',
    method: 'put',
    params:expenseId
  })
}
//修改借支单（编辑）
export function editTravelExpense2(params) {
  return request({
    url: 'api/travelExpense/editTravelExpense2',
    method: 'put',
    params:params
  })
}
//撤回操作
export function editTravelExpenseType(params) {
  return request({
    url: 'api/travelExpense/editTravelExpenseType',
    method: 'put',
    params:params
  })
}

//获取报销项目
export function getExpenseItem(params) {
  return request({
    url: 'api/travelExpense/getExpenseItem',
    method: 'get',
    params
  })
}

export function getCode(params) {
  return request({
    url: 'api/travelExpense/getTravelExpenseCode',
    method: 'get',
    params
  })
}
export function editTravelExpenseById(params) {
  return request({
    url: 'api/travelExpense/editTravelExpenseById',
    method: 'put',
    params
  })
}
export default { editTravelExpenseById,getCode,addTravelExpense,getTravelExpense,editTravelExpense,getExpenseItem}
