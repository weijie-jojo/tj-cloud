import request from '@/utils/request'

//获取票据详情接口
export function getTicketDetail(ticketId){
  return request({
    url: '/project/ticket/'+ticketId,
    method: 'get',
  })
}
export function list(query) {
  return request({
    url: 'project/ticket/list',
    method: 'get',
    params: query
  })
}

export function ownlist(query) {
  return request({
    url: 'project/employed/list',
    method: 'get',
    params: query
  })
}

export function TicketByCode(query) {
  return request({
    url: 'project/project/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}

export function add(data) {
  return request({
    url: '/project/ticket',
    method: 'post',
    data: data
  })
}
export function getcode(){
    return request({
        url:'project/project/getCode',
        method:'get'    
    })
}
export function list2(data){
  return request({
      url:'/project/ticket/list2',
      method:'get',
      params:data 
          
  })
}
export function del(ticketIds) {
  return request({
    url: 'project/ticket/del/'+ticketIds,
    method: 'put',
 })
}

export function delReal(ticketIds) {
  return request({
    url: 'project/ticket/del/'+ticketIds,
    method: 'delete',
 })
}

export function edit(data) {
  return request({
    url: 'project/ticket',
    method: 'put',
    data:data
  })
}

export function getinfoByUserId(data){
  return request({
    url:'project/place/getInfoByUserId',
    method:'get',
    params:data 
  })
}



export default {delReal,getTicketDetail, list , edit, del, getcode , getinfoByUserId ,add,ownlist,TicketByCode,list2}