import request from '@/utils/request'

export function getList(data) {
  return request({
    url: 'tb-device/getbypage',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data: {
      pagenum: data.pagenum,
      pagesize: data.pagesize
    },
    transformRequest: [function(data) {
      let ret = ''
      for (const it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }]
  })
}

export function changeDeviceAlgorithmState(data) {
  return request({
    url: 'tb-device/change_state',
    method: 'post',
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    },
    data: {
      id: data.id,
      state: data.state
    }
  })
}
