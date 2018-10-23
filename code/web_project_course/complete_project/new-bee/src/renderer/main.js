import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import { axiosIntercept } from './utils/request'

// 将Axios扩展到Vue原型链中
Vue.prototype.$http = axiosIntercept(Vue)
Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  render: h => h(App)
})
