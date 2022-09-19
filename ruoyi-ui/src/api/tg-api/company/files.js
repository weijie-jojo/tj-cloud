import request from '@/utils/request'

// 查询个体商户列表
export function showTxt(query) {
  return request({
    url: '/company/files/showTxt',
    method: 'get',
    params: query
  })
}
export default {showTxt}