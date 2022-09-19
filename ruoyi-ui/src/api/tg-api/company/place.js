import request from '@/utils/request'

// 查询平台渠道商列表
export function listPlace(query) {
  return request({
    url: '/company/place/tg/list',
    method: 'get',
    params: query
  })
}

// 查询平台渠道商详细
export function getPlace(placeId) {
  return request({
    url: '/company/place/tg/' + placeId,
    method: 'get'
  })
}
// 根据渠道商编号查询代理费详情
export function selectFeeByCode(query) {
  return request({
    url: 'place/agencyFee/tg/selectFeeByCode',
    method: 'get',
    params: query
  })
}

// 新增平台渠道商
export function addPlace(data) {
  return request({
    url: '/company/place/tg',
    method: 'post',
    data: data
  })
}

// 修改平台渠道商
export function updatePlace(data) {
  return request({
    url: '/company/place/tg',
    method: 'put',
    data: data
  })
}

// 删除平台渠道商
export function delPlace(placeId) {
  return request({
    url: '/company/place/tg/' + placeId,
    method: 'delete'
  })
}

// 查询平台渠道商详细(根据userid)
export function getPlaceByUserId(query) {
  return request({
    url: '/company/place/tg/getPlaceByUserId',
    method: 'get',
    params: query
  })
}
export default {getPlaceByUserId,selectFeeByCode}