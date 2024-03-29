import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    devices: {},

  },
  mutations: {
    add(state) {
      state.count++
    },
    sub(state) {
      state.count--
    },
    addN(state, step) {
      state.count += step
    },
    setDevices(state,devices){
      devices.forEach(item => {
        Vue.set(state.devices,item.id,item)
      })
    },

  },
  actions: {
    /* 延迟操作必须在actions代码块中执行 */
    addAsync(context) {
      setTimeout(() => {
        context.commit('add')
      }, 1000)
    }
  },
  getters: {
    showCount(state) {
      return 'Count的值:' + state.count
    }
  }
}
)
