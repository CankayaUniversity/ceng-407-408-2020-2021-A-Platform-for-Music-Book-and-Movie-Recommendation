import Vue from 'vue'
import vuetify from '@/plugins/vuetify.js'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

import router from './router/routes'
import store from "./store/store"
import App from './App.vue'

Vue.config.productionTip = false

new Vue({
  router,
  vuetify,
  store,
  icons: {
    iconfont: 'mdi',

  },
  render: h => h(App),

}).$mount('#app')