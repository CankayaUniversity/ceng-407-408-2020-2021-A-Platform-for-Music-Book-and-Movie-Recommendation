import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Auth/Login.vue'
import Register from '../views/Auth/Register.vue'
import AdminPanel from '../views/AdminPanel.vue'
import BrowserPage from '../views/User/BrowserPage.vue'
import ChangePass from '../views/User/ChangePass.vue'
import DetailsPage from '../views/User/DetailsPage.vue'
import MainPage from '../views/MainPage.vue'
import ProfilePage from '../views/User/ProfilePage.vue'
import Questionnaire from '../views/User/Questionnaire.vue'
import ForgotPassword from '../views/Auth/ForgotPassword.vue'
//import Header from '../views/Auth/Header.vue'
import store from '../store/store'
import axios from 'axios'
import VueAxios from 'vue-axios'
import DeleteAccount from "@/views/User/DeleteAccount";

Vue.use(VueAxios, axios)
Vue.use(VueRouter);

const routes = [
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
    path: '/adminpanel',
    component: AdminPanel,
    name: 'adminpanel'
  },
  {
    path: '/BrowserPage',
    component: BrowserPage,
    name: 'browserpage'
  },
  {
    path: '/ChangePass',
    component: ChangePass,
    name: 'changepass'
  },
  {
    path: '/detailsPage',
    component: DetailsPage,
    name: 'detailspage'
  },
  {
    path: '/MainPage',
    component: MainPage,
    name: 'mainpage'
  },
  {
    path: '/profilePage',
    component: ProfilePage,
    name: 'profilepage'
  },
  {
    path: '/Questionnaire',
    component: Questionnaire,
    name: 'questionnaire'
  },
  {
    path: '/ForgotPassword',
    component: ForgotPassword,
    name: 'ForgotPassword'
  },
  {
    path: '/DeleteAccount',
    component: DeleteAccount,
    name: 'deleteaccount'
  }
]

const router = new VueRouter({
  mode: 'history',
  routes: routes,
  base: '/'
})


export default router

router.beforeEach((to, from, next) => {
  if (['register','mainpage', 'login'].includes(to.name)) {
    next()
  }
  else {
    if(store.getters.getToken ) {
     // console.log(store.getters.getToken)
      //store.commit('setAuthHeader');
      next()
    }
    else next("/login")
  }
})