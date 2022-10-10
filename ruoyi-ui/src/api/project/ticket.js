import request from '@/utils/request'

//获取票据详情接口
export function getTicketDetail(ticketId){
  return request({
    url: '/project/ticket/'+ticketId,
    method: 'get',
  })
}
//票据列表
export function list(query) {
  return request({
    url: 'project/ticket/list',
    method: 'get',
    params: query
  })
}
//获取乙方接口
export function ownlist(query) {
  return request({
    url: 'project/employed/list',
    method: 'get',
    params: query
  })
}
//票据详情接口
export function TicketByCode(query) {
  return request({
    url: 'project/project/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}
//添加票据
export function add(data) {
  return request({
    url: '/project/ticket',
    method: 'post',
    data: data
  })
}
//获取项目编码
export function getcode(){
    return request({
        url:'project/project/getCode',
        method:'get'    
    })
}
//获取票据列表 不分页
export function list2(data){
  return request({
      url:'/project/ticket/list2',
      method:'get',
      params:data 
          
  })
}

//删除票据
export function delReal(ticketIds) {
  return request({
    url: 'project/ticket/'+ticketIds,
    method: 'delete',
 })
}
//票据修改
export function edit(data) {
  return request({
    url: 'project/ticket',
    method: 'put',
    data:data
  })
}
//获取客户
export function getinfoByUserId(data){
  return request({
    url:'project/place/getInfoByUserId',
    method:'get',
    params:data 
  })
}



export default {delReal,getTicketDetail, list , edit, getcode , getinfoByUserId ,add,ownlist,TicketByCode,list2}