import request from '@/utils/request'

export function list(query) {
  return request({
    url: 'project/project/list',
    method: 'get',
    params: query
  })
}

export function detail(query) {
  return request({
    url: 'project/project/selectProjectJoinTicketByCode',
    method: 'get',
    params: query
  })
}
export function ownlist(query) {
  return request({
    url: 'project/employed/getByUserIndusty',
    method: 'get',
    params: query
  })
}
export function add(data) {
  return request({
    url: 'project/project',
    method: 'post',
    data: data
  })
}
export function getcode(query){
    return request({
        url:'project/project/getCode',
        method:'get',
        params:query   
    })
}
export function del(projectIds) {
  return request({
    url: 'project/project/'+projectIds,
    method: 'put',
 })
}

export function edit(data) {
  return request({
    url: 'project/project',
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



export default { list , edit, del, getcode , getinfoByUserId ,add,ownlist,detail}