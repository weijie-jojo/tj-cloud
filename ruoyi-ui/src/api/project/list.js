import request from '@/utils/request'

export function list(query) {
  return request({
    url: 'project/project/list',
    method: 'get',
    params: query
  })
}
export function getcode(){
    return request({
        url:'project/project/getCode',
        method:'get'    
    })
}
export function del(ids) {
  return request({
    url: 'place/agencyFee/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'place/agencyFee',
    method: 'put',
    data
  })
}



export default { list , edit, del,getcode }