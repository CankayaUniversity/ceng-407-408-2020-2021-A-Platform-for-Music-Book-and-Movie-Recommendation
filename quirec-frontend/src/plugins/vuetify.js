import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import 'material-design-icons-iconfont/dist/material-design-icons.css'

Vue.use(Vuetify)

//const opts = {}

export default new Vuetify({
    theme: {
        dark: true,
        themes: {
            dark:{
                primary: '#c2185b',
                secondary: '#212121'
            }
        }
    },
    icons: {
        iconfont: 'md',
    },
})

/*Vue.use(Vuetify, {
    iconfont: 'md'
})
*/