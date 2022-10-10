import request from '@/utils/request'
//获取票据详情接口
export function getTicketDetail(ticketId){
  return request({
    url: '/project/ticket/tg/'+ticketId,
    method: 'get',
  })
}
//获取票据列表
export function list(query) {
  return request({
    url: 'project/ticket/tg/list',
    method: 'get',
    params: query
  })
}
//获取乙方
export function ownlist(query) {
  return request({
    url: 'project/employed/tg/list',
    method: 'get',
    params: query
  })
}
//票据和项目列表接口
export function TicketByCode(query) {
  return request({
    url: 'project/project/tg/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}
//票据新增
export function add(data) {
  return request({
    url: '/project/ticket/tg',
    method: 'post',
    data: data
  })
}
//获取项目编码
export function getcode(){
    return request({
        url:'project/project/tg/getCode',
        method:'get'    
    })
}
//获取票据列表不分页
export function list2(data){
  return request({
      url:'/project/ticket/tg/list2',
      method:'get',
      params:data 
          
  })
}
//票据删除
export function delReal(ticketIds) {
  return request({
    url: 'project/ticket/tg/'+ticketIds,
    method: 'delete',
 })
}
//票据修改
export function edit(data) {
  return request({
    url: 'project/ticket/tg',
    method: 'put',
    data:data
  })
}
//获取客户
export function getinfoByUserId(data){
  return request({
    url:'project/place/tg/getInfoByUserId',
    method:'get',
    params:data 
  })
}



export default {delReal,getTicketDetail, list , edit, getcode , getinfoByUserId ,add,ownlist,TicketByCode,list2}