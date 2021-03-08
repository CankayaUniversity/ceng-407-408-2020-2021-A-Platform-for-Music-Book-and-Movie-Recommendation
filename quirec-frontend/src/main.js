import Vue from 'vue'
import VueRouter from 'vue-router'

import axios from 'axios'; 
import vuetify from '@/plugins/vuetify.js'
import 'material-design-icons-iconfont/dist/material-design-icons.css'
import {store} from "./store/store"
import App from './App.vue'
import Login from './components/Auth/Login'
import Signup from './components/Auth/Signup'
import AdminPanel from './components/AdminPanel'
import BrowserPage from './components/BrowserPage'
import ChangePass from './components/ChangePass'
import DetailsPage from './components/DetailsPage'
import MainPage from './components//MainPage'
import ProfilePage from './components/ProfilePage'
import Questionnaire from './components/Questionnaire'
import ResetPass from './components/ResetPass'

Vue.config.productionTip = false

Vue.use(VueRouter);

axios.defaults.baseURL = "http://localhost:8080/api" ;

const routes = [
  {
    path: '/login',
    component: Login,
    name: 'login'
  },
  {
    path: '/signup',
    component: Signup,
    name: 'signup'
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
    path: '/DetailsPage',
    component: DetailsPage,
    name: 'detailspage'
  },
  {
    path: '/MainPage',
    component: MainPage,
    name: 'mainpage'
  },
  {
    path: '/ProfilePage',
    component: ProfilePage,
    name: 'profilepage'
  },
  {
    path: '/Questionnaire',
    component: Questionnaire,
    name: 'questionnaire'
  },
  {
    path: '/ResetPass',
    component: ResetPass,
    name: 'resetpass'
  }
];
const router = new VueRouter({
  mode: 'history',
  routes: routes,
  base: '/'
})

new Vue({
  router,
  vuetify,
  store,
  icons: {
    iconfont: 'md',
  },
  render: h => h(App),

}).$mount('#app')
