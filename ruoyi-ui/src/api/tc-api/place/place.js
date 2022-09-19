import request from '@/utils/request'

export function add(data) {
  return request({
    url: 'place/place/tc',
    method: 'post',
    data
  })
}

export function del(ids) {
  return request({
    url: 'place/place/tc',
    method: 'delete',
    data: ids
  })
}

export function edit(data) {
  return request({
    url: 'place/place/tc',
    method: 'put',
    data
  })
}

export function getPlaceCount(query){
  return request({
    url: 'place/place/tc/getCount',
    method: 'get',
    params: query
  })
}
export function editPlace(params) {
  return request({
    url: 'place/place/tc/editPlace',
    method: 'put',
    params:params
  })
}
//删除渠道
export function delPlace(params) {
  return request({
    url: 'place/place/tc/delPlace',
    method: 'put',
    params:params
  })
}
//改变状态
export function editPlace2(params) {
  return request({
    url: 'place/place/tc/editPlace2',
    method: 'put',
    params:params
  })
}
export function getCode(params) {
  return request({
    url: 'place/place/tc/getCode',
    method: 'get',
    params
  })
}

//分页查询
export function getByPage(params) {
  return request({
    url: 'place/place/tc/getByPage',
    method: 'get',
    params:params
  })
}
export default { add, edit, del ,delPlace,editPlace,editPlace2,getCode,getByPage,getPlaceCount}
