import request from '@/utils/request'

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
export function del(ticketIds) {
  return request({
    url: 'project/ticket/del/'+ticketIds,
    method: 'put',
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



export default { list , edit, del, getcode , getinfoByUserId ,add,ownlist,TicketByCode}