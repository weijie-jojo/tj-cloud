import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'api/place',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'api/place/',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'api/place',
    method: 'put',
    data
  })
}

export function editPlace(params) {
  return request({
    url: 'api/place/editPlace',
    method: 'put',
    params:params
  })
}
//删除渠道
export function delPlace(params) {
  return request({
    url: 'api/place/delPlace',
    method: 'put',
    params:params
  })
}
//改变状态
export function editPlace2(params) {
  return request({
    url: 'api/place/editPlace2',
    method: 'put',
    params:params
  })
}
export function getCode(params) {
  return request({
    url: 'api/place/getCode',
    method: 'get',
    params
  })
}

//分页查询
export function getByPage(params) {
  return request({
    url: 'api/place/getByPage',
    method: 'put',
    params:params
  })
}
export default { add, edit, del ,delPlace,editPlace,editPlace2,getCode}
