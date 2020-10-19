import Cookies from 'js-cookie'

const TokenKey = 'Chuoyue-Token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, 'Bearer ' + token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
