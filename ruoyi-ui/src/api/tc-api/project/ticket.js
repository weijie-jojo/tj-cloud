import request from '@/utils/request'

export function list(query) {
  return request({
    url: 'project/ticket/tc/list',
    method: 'get',
    params: query
  })
}

export function ownlist(query) {
  return request({
    url: 'project/employed/tc/list',
    method: 'get',
    params: query
  })
}

export function TicketByCode(query) {
  return request({
    url: 'project/project/tc/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}

export function add(data) {
  return request({
    url: '/project/ticket/tc',
    method: 'post',
    data: data
  })
}
export function getcode(){
    return request({
        url:'project/project/tc/getCode',
        method:'get'    
    })
}
export function list2(data){
  return request({
      url:'/project/ticket/tc/list2',
      method:'get',
      params:data 
          
  })
}
export function del(ticketIds) {
  return request({
    url: 'project/ticket/tc/del/'+ticketIds,
    method: 'put',
 })
}

export function edit(data) {
  return request({
    url: 'project/ticket/tc',
    method: 'put',
    data:data
  })
}

export function getinfoByUserId(data){
  return request({
    url:'project/place/tc/getInfoByUserId',
    method:'get',
    params:data 
  })
}



export default { list , edit, del, getcode , getinfoByUserId ,add,ownlist,TicketByCode,list2}