const Blog = () => import( /* webpackChunkName: "demo" */ '@/container/blog/index')

/*
 所有container/blog目录下的路径都配置在此路由children下，避免混乱
 */
let routes = [{
  path: '/blog',
  name: 'blog',
  component: Blog,
  children: [{
    path: 'blogdemo',
    component: Blog
  }
  ]
}]


export {
  routes
}
