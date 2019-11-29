import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import Home from '@/components/Home'
import Enterprise from '@/components/pages/base/Enterprise'
import Student from '@/components/pages/base/Student'
import Tutor from '@/components/pages/base/Tutor'
import LoginLog from '@/components/pages/base/LoginLog'
import OperateLog from '@/components/pages/base/OperateLog'

Vue.use(Router)

let router = new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login,
      hidden: true
    },
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: { requireAuth: true },
      children: [
        {
          path: '/home',
          redirect: 'enterprise'
        },
        {
          path: 'enterprise',
          name: '企业信息',
          component: Enterprise,
          meta: {
            keepAlive: false, // 添加该字段，表示进入这个路由是需要登录的
            requireAuth: true // 此组件不需要被缓存
          }
        },
        {
          path:"student",
          name:'学生信息',
          component:Student,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        {
          path:"tutor",
          name:'导师信息',
          component:Tutor,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        {
          path:"sys/loginlog",
          name:'登录日志',
          component:LoginLog,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
        {
          path:"sys/operatelog",
          name:'操作日志',
          component:OperateLog,
          hidden: true,
          meta:{keepAlive:false, requireAuth: true}
        },
      ]
    },
    
  ],
  linkActiveClass: 'is-active'
})

export default router