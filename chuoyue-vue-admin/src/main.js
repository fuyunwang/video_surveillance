import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/element.js'
import './assets/css/global.css'
import './assets/fonts/iconfont.css'
import store from './components/store'

import axios from 'axios'

import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

Vue.config.productionTip = false

axios.defaults.baseURL = 'http://localhost:10086/chuoyue/'

axios.interceptors.request.use(config => {
  NProgress.start()
  // console.log(config)
  config.headers.Authorization = 'Bearer ' + window.sessionStorage.getItem('token')
  // 在最后必须 return config
  return config
})

axios.interceptors.response.use(config => {
  NProgress.done()
  return config
})

Vue.prototype.$http = axios

new Vue({
  router,
  render: h => h(App),
  store
}).$mount('#app')
