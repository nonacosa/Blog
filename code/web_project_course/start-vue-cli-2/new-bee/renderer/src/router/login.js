const Login = () => import( /* webpackChunkName: "demo" */ '@/container/login/index')

/*
 所有container/login目录下的路径都配置在此路由children下，避免混乱
 */
let routes = [{
  path: '/login',
  name: 'login',
  component: Login,
  children: [{
    path: 'logindemo',
    component: Login
  }
  ]
}]


export {
  routes
}
