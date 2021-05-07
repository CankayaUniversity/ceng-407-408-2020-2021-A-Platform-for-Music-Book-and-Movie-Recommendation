import Vue from 'vue'
import VueRouter from 'vue-router'
import store from '../store/store'
import axios from 'axios'
import VueAxios from 'vue-axios'

import MainPage from '../views/MainPage.vue'
import Login from '../views/Auth/Login.vue'
import Register from '../views/Auth/Register.vue'
import ProfilePage from '../views/User/ProfilePage.vue'
import Questionnaire from '../views/User/Questionnaire.vue'
import BrowserPage from '../views/User/BrowserPage.vue'
import DetailsPage from '../views/User/DetailsPage.vue'
import AdminPanel from '../views/AdminPanel.vue'
import ChangePassword from '../views/User/ChangePass.vue'
import ForgotPassword from '../views/Auth/ForgotPassword.vue'
import DeleteAccount from '../views/User/DeleteAccount.vue'

Vue.use(VueAxios, axios)
Vue.use(VueRouter);

const routes = [
  {
    path: '/main-page',
    component: MainPage,
    name: 'mainPage'
  },
  {
    path: '/login',
    component: Login,
    name: 'login'
  },
  {
    path: '/register',
    component: Register,
    name: 'register'
  },
  {
    path: '/profile',
    component: ProfilePage,
    name: 'profilePage'
  },
  {
    path: '/questionnaire',
    component: Questionnaire,
    name: 'questionnaire'
  },
  {
    path: '/browse',
    component: BrowserPage,
    name: 'browserPage'
  },
  {
    path: '/details',
    component: DetailsPage,
    name: 'detailsPage'
  },
  {
    path: '/admin-panel',
    component: AdminPanel,
    name: 'adminPanel'
  },
  {
    path: '/change-password',
    component: ChangePassword,
    name: 'changePassword'
  },
  {
    path: '/forgot-password',
    component: ForgotPassword,
    name: 'forgotPassword'
  },
  {
    path: '/delete-account',
    component: DeleteAccount,
    name: 'deleteAccount'
  }
]

const router = new VueRouter({
  mode: 'history',
  routes: routes,
  base: '/'
})

export default router

router.beforeEach((to, from, next) => {
  if(['register','mainPage', 'login', 'forgotPassword'].includes(to.name)) {
    next()
  }
  else {
    if(store.getters.getToken) {
      next()
    }
    else next("/login")
  }
})