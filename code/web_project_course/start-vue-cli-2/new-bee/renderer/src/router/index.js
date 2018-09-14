import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import { routes as loginRoutes } from './login'
Vue.use(Router)



let route = [
  {
    path: '/',
    name: 'HelloWorld',
    component: HelloWorld
  }
]


route = route.concat(loginRoutes)

export default new Router({
  routes: route,
  linkActiveClass: 'active'
})