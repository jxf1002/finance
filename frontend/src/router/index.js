import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import Family from '@/components/Family'
import User from '@/components/User'
import Account from '@/components/Account'
import Book from '@/components/Book'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Index',
      component: Index
    },
    {
      path: '/family',
      name: 'Family',
      component: Family
    },
    {
      path: '/user',
      name: 'User',
      component: User
    },
    {
      path: '/account',
      name: 'Account',
      component: Account
    },
    {
      path: '/book',
      name: 'Book',
      component: Book
    }
  ]
})
