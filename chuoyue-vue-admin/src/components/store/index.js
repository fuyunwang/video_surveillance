import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state:{
    count: 0
  },
  mutations:{
    add(state) {
      state.count++
    },
    sub(state) {
      state.count--
    },
    addN(state, step) {
      state.count += step
    }
  },
  actions:{
    /*延迟操作必须在actions代码块中执行*/
    addAsync(context){
      setTimeout(()=>{
        context.commit('add')
      },1000)
    }
  },
  getters: {
    showCount(state){
      return "Count的值:" + state.count;
    }
  }
  }
)
