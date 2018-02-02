import Vue from 'vue/dist/vue.js'
import Router from 'vue-router'
import MainEditor from '@/components/MainEditor'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainEditor',
      component: MainEditor
    }
  ]
})
