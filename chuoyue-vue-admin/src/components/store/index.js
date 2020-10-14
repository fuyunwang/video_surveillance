import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    count: 0,
    devices: {},
    videoConfigs: {
      2: {
        id: '1',
        url: 'http://qi2c9qbdt.hb-bkt.clouddn.com/person_detect.mp4',
        volume: 1,
        autoplay: true
      },
      1: {
        id: '2',
        url: 'http://qi2c9qbdt.hb-bkt.clouddn.com/zhenhuan.mp4',
        volume: 1,
        autoplay: true
      },
      3: {
        id: '3',
        url: 'http://qi2c9qbdt.hb-bkt.clouddn.com/qifengle.mp4',
        volume: 1,
        autoplay: true
      },
    },
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
    setVideoConfigs(state,configs){
      configs.forEach(item => {
        Vue.set(state.videoConfigs,item.id,item)
      })
    }
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
