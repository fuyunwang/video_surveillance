import { getDepartmentList, getDepartmentInfo, disposalAlarm } from "@/api/department"
import Vue from 'vue'

const state = {
  total: 0,
  size: 0,
  current: 1,
  orders: [],
  pages: 1,
  records: [],
  currentDepartment: {},
  deviceList: {}
}

const mutations = {
  SET_TOTAL: (state, total) => {
    state.total = total
  },
  SET_SIZE: (state, size) => {
    state.size = size
  },
  SET_CURRENT: (state, current) => {
    state.current = current
  },
  SET_ORDERS: (state, orders) => {
    state.orders = orders
  },
  SET_PAGES: (state, pages) => {
    state.pages = pages
  },
  SET_RECORDS: (state, records) => {
    state.records = records
  },
  SET_CURRENT_DEPARTMENT: (state, currentDepartment) => {
    state.currentDepartment = currentDepartment
  },
  SET_DEVICE_LIST: () => {
    state.records.forEach(record => {
      Vue.set(state.deviceList, record.id, record)
    })
  }
}

const actions = {
  getDepartmentList({ commit }, params) {
    const { pagenum, pagesize } = params
    return new Promise((resolve, reject) => {
      getDepartmentList({ pagenum: pagenum, pagesize: pagesize }).then(response => {
        const { data: res } = response
        const { records, total, size, current, orders, pages } = res
        commit('SET_RECORDS', records)
        commit('SET_TOTAL', total)
        commit('SET_SIZE', size)
        commit('SET_CURRENT', current)
        commit('SET_ORDERS', orders)
        commit('SET_PAGES', pages)
        commit('SET_DEVICE_LIST', records)
        resolve(res)
      }).catch(error => {
        reject(error)
      })
    })
  },
  getDepartmentInfo({ commit }, id) {
    return new Promise((resolve, reject) => {
      getDepartmentInfo(id).then(response => {
        const { data: res } = response
        commit('SET_CURRENT_DEPARTMENT', res)
        resolve(res)
      })
        .catch(error => {
          reject(error)
        })
    })
  },
  disposeAlarm({ commit }, params) {
    return new Promise((resolve, reject) => {
      disposalAlarm(params).then(response => {
        resolve(response)
      })
        .catch(error => {
          reject(error)
        })
    })
  }
}

export default {
  namespaced: true,
  state,
  mutations,
  actions
}
