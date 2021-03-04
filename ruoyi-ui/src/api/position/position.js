import request from '@/utils/request'

/**
 * 查询全部档案
 * @param query
 */
export function listPosition(query) {
  return request({
    url: '/position/position/list',
    method: 'get',
    params: query
  })
}

/**
 * 添加档案
 * @param data
 */
export function addPosition(data) {
  return request({
    url: '/position/position',
    method: 'post',
    params: data
  })
}

/**
 * 查询档案详情
 * @param id
 */
export function queryPosition(id) {
  return request({
    url: '/position/position/' + id,
    method: 'get'
  })
}

/**
 * 修改档案详情
 * @param id
 */
export function updatePosition(query) {
  return request({
    url: '/position/position',
    method: 'put',
    data:query
  })
}

// 删除档案
export function delPosition(ids) {
  return request({
    url: '/position/position/' + ids,
    method: 'delete'
  })
}
// 获取国籍
export function getCountry() {
  return request({
    url: '/position/position/getCountry',
    method: 'get'
  })
}

// 复核
export function upateStatus(ids) {
  return request({
    url: '/position/position/upateStatus/'+ids,
    method: 'delete',

  })
}
