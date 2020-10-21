import request from '@/utils/request'

export function login(data) {
  return request({
    url: 'cyLogin',
    method: 'post',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    },
    data: {
      username: data.username,
      password: data.password,
      code: data.code
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

export function getInfo(token) {
  return request({
    url: 'tb-agent/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function getMenuList() {
  return request({
    url: 'tb-menu-info/list',
    method: 'post'
  })
}

export function getValidateCode() {
  return request({
    url: 'captcha',
    method: 'get'
  })
}
