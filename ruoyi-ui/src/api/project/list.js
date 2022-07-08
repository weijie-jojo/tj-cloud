import request from '@/utils/request'

export function list(query) {
  return request({
    url: 'project/project/list',
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
export function add(query) {
  return request({
    url: 'project/project',
    method: 'post',
    params: query
  })
}
export function getcode(){
    return request({
        url:'project/project/getCode',
        method:'get'    
    })
}
export function del(projectIds) {
  return request({
    url: 'project/project/'+projectIds,
    method: 'delete',
 })
}

export function edit(data) {
  return request({
    url: 'place/agencyFee',
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



export default { list , edit, del, getcode , getinfoByUserId ,add,ownlist}