import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home'
import Welcome from '../components/Welcome'
import Users from '../components/user/AlertManage'
import Rights from '../components/power/Rights'
import Roles from '../components/power/Roles'
import UserEdit from '../components/user/edit/UserEdit'
import ChartFirst from '../components/chart/ChartFirst'
import RoleEdit from '../components/power/edit/RoleEdit'
import Category from '../components/goods/Category'
import AlertFirstManage from '../components/alert/AlertFirstManage'
import SettingsFirstManage from '../components/settings/SettingsFirstManage'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login
  },
  {
    path: '/home',
    component: Home,
    redirect: '/welcome',
    children: [
      {
        path: '/welcome',
        component: Welcome
      },
      {
        path: '/portal/first',
        component: Users
      },
      {
        path: '/portal/second',
        component: Rights
      },
      {
        path: '/alert/first',
        component: AlertFirstManage
      },
      {
        path: '/settings/first',
        component: SettingsFirstManage
      },
      {
        path: '/system/first',
        component: ChartFirst
      },
      {
        path: '/roles/edit',
        component: RoleEdit
      },
      {
        path: '/categories',
        component: Category
      }
    ]
  }
]

const router = new VueRouter({
  routes
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login') return next()

  const tokenStr = window.sessionStorage.getItem('token')

  if (!tokenStr) return next('/login')

  next()
})

export default router
