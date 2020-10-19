import { getList } from '@/api/devices'

const state = {
  total: 0,
  size: 0,
  current: 1,
  orders: [],
  pages: 1,
  records: []
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
  }
}

const actions = {
  // user login
  getDevices({ commit }, params) {
    const { pagenum, pagesize } = params
    return new Promise((resolve, reject) => {
      getList({ pagenum: pagenum, pagesize: pagesize }).then(response => {
        const { data: res } = response
        const { records, total, size, current, orders, pages } = res
        commit('SET_RECORDS', records)
        commit('SET_TOTAL', total)
        commit('SET_SIZE', size)
        commit('SET_CURRENT', current)
        commit('SET_ORDERS', orders)
        commit('SET_PAGES', pages)
        resolve(res)
      }).catch(error => {
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
