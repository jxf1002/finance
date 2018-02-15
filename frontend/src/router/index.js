import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Family from '@/components/Family'
import User from '@/components/User'
import Account from '@/components/Account'
import Book from '@/components/Book'
import Login from '@/components/Login'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '',
      name: 'Index1',
      meta: {
        requireAuth: true
      },
      component: Index
    },
    {
      path: '/',
      name: 'Index2',
      meta: {
        requireAuth: true
      },
      component: Index
    },
    {
      path: '/family',
      name: 'Family',
      meta: {
        requireAuth: true
      },
      component: Family
    },
    {
      path: '/user',
      name: 'User',
      meta: {
        requireAuth: true
      },
      component: User
    },
    {
      path: '/account',
      name: 'Account',
      meta: {
        requireAuth: true
      },
      component: Account
    },
    {
      path: '/book',
      name: 'Book',
      meta: {
        requireAuth: true
      },
      component: Book
    }
  ]
})
