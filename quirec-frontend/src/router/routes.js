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
import ChangePassword from '../views/User/ChangePassword.vue'
import ForgotPassword from '../views/Auth/ForgotPassword.vue'
import DeleteAccount from '../views/User/DeleteAccount.vue'
import ResetPassword from '../views/Auth/ResetPassword.vue'
import RecommendationsPage from "../views/User/RecommendationsPage";

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
    name: 'profilePage',
    meta: { authorize: 'user' }
  },
  {
    path: '/questionnaire',
    component: Questionnaire,
    name: 'questionnaire',
    meta: { authorize: 'user' }
  },
  {
    path: '/browse',
    component: BrowserPage,
    name: 'browserPage',
    meta: { authorize: 'user' }
  },
  {
    path: '/details/:category/:resourceId',
    component: DetailsPage,
    name: 'detailsPage',
    props: { showRating: false },
    meta: { authorize: 'user' }
  },
  {
    path: '/admin-panel',
    component: AdminPanel,
    name: 'adminPanel',
    meta: { authorize: 'admin' }
  },
  {
    path: '/change-password',
    component: ChangePassword,
    name: 'changePassword',
    meta: { authorize: 'user' }
  },
  {
    path: '/forgot-password',
    component: ForgotPassword,
    name: 'forgotPassword'
  },
  {
    path: '/delete-account',
    component: DeleteAccount,
    name: 'deleteAccount',
    meta: { authorize: 'user' }
  },
  {
    path: '/reset-password',
    component: ResetPassword,
    name: 'resetPassword'
  },
  {
    path: '/recommendations',
    component: RecommendationsPage,
    name: 'recommendationsPage',
    meta: { authorize: 'user' }
  }
]

const router = new VueRouter({
  mode: 'history',
  routes: routes,
  base: '/'
})

export default router

router.beforeEach((to, from, next) => {
  if(['register','mainPage', 'login', 'forgotPassword', "resetPassword"].includes(to.name)) {
    next()
  }
  else {
    const { authorize } = to.meta;
    if(store.getters.getToken) {
      if(authorize !== store.getters.getRole)
        next({ path: '/login'});
      else next()
    }
    else next("/login")
  }
})