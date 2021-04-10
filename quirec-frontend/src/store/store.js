import Vue from 'vue';
import Vuex from 'vuex';
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex);

const vuexLocal = new VuexPersistence({
    storage: window.localStorage
})

const store = new Vuex.Store({
    state:{
        userId: null,
        userToken: null
    },
    getters: {
        getUserId: state => state.userId ,//gets user id
        getToken: state => state.userToken //gets user Token
    },
    mutations:{
        setUserId(state, id){
            state.userId = id; //sets user id
        },
        setUserToken(state, token){
            state.userToken = token; //sets user token
        },
        clearUserToken(state){
            state.userToken = null;
        },
        clearUserId(state){
            state.userId = null;
        }
    },
    plugins: [vuexLocal.plugin]
});

export default store