import request from '@/utils/request'
//获取票据详情接口
export function getTicketDetail(ticketId){
  return request({
    url: '/project/ticket/tg/'+ticketId,
    method: 'get',
  })
}
export function list(query) {
  return request({
    url: 'project/ticket/tg/list',
    method: 'get',
    params: query
  })
}

export function ownlist(query) {
  return request({
    url: 'project/employed/tg/list',
    method: 'get',
    params: query
  })
}

export function TicketByCode(query) {
  return request({
    url: 'project/project/tg/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}

export function add(data) {
  return request({
    url: '/project/ticket/tg',
    method: 'post',
    data: data
  })
}
export function getcode(){
    return request({
        url:'project/project/tg/getCode',
        method:'get'    
    })
}
export function list2(data){
  return request({
      url:'/project/ticket/tg/list2',
      method:'get',
      params:data 
          
  })
}
export function del(ticketIds) {
  return request({
    url: 'project/ticket/tg/del/'+ticketIds,
    method: 'put',
 })
}
export function delReal(ticketIds) {
  return request({
    url: 'project/ticket/tg/del/'+ticketIds,
    method: 'delete',
 })
}

export function edit(data) {
  return request({
    url: 'project/ticket/tg',
    method: 'put',
    data:data
  })
}

export function getinfoByUserId(data){
  return request({
    url:'project/place/tg/getInfoByUserId',
    method:'get',
    params:data 
  })
}



export default {delReal,getTicketDetail, list , edit, del, getcode , getinfoByUserId ,add,ownlist,TicketByCode,list2}