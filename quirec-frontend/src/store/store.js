import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})

const store = new Vuex.Store({
    state:{
        userId: null
    },
    getters: {
        getUserId: state => state.userId //gets user id
    },
    mutations:{
        setUserId(state, id){
            state.userId = id; //sets user id
        }
    },
    plugins: [vuexLocal.plugin]
});

export default store