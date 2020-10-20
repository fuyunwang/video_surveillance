import request from '@/utils/request'

export function getDepartmentList(data) {
  return request({
    url: 'tb-department/getbypage',
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

export function getDepartmentInfo(id) {
  return request({
    url: 'tb-department-solved/getbyid',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data: {
      id: id
    },
    transformRequest: [function (data) {
      let ret = ''
      for (const it in data) {
        ret += encodeURIComponent(it) + '=' + encodeURIComponent(data[it]) + '&'
      }
      return ret
    }]
  })
}

export function disposalAlarm(params) {
  const { departmentId, contact, note} = params
  return request({
    url: 'tb-department-solved/dispose',
    method: 'post',
    headers: {
      'Content-Type': 'application/json;charset=utf-8'
    },
    data: {
      departmentId: departmentId,
      contact: contact,
      note: note
    }
  })
}

