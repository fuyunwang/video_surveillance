import request from '@/utils/request'

export function getDisposedAlarmList(data) {
  return request({
    url: 'tb-department-solved/getbypage',
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
