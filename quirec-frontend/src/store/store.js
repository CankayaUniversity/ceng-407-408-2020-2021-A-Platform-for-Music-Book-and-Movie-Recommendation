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
        userToken: null,
        role: null,
        browsedMusic: null,
        browsedBook: null,
        browsedMovie: null
    },
    getters: {
        getUserId: state => state.userId,
        getToken: state => state.userToken,
        getRole: state => state.role,
        getBrowsedMusic: state => state.browsedMusic,
        getBrowsedBook: state => state.browsedBook,
        getBrowsedMovie: state => state.browsedMovie,
    },
    mutations:{
        setUserId(state, id) {
            state.userId = id;
        },
        clearUserId(state) {
            state.userId = null;
        },
        setUserToken(state, token) {
            state.userToken = token;
        },
        clearUserToken(state){
            state.userToken = null;
        },
        setRole(state, role) {
            state.role = role
        },
        clearRole(state) {
            state.role = null
        },
        setBrowsedMusic(state, browse){
            state.browsedMusic = browse;
        },
        clearBrowsedMusic(state){
            state.browsedMusic = null
        },
        setBrowsedBook(state, browse){
            state.browsedBook = browse;
        },
        clearBrowsedBook(state){
            state.browsedBook = null
        },
        setBrowsedMovie(state, browse){
            state.browsedMovie = browse;
        },
        clearBrowsedMovie(state){
            state.browsedMovie = null
        },


    },
    plugins: [vuexLocal.plugin]
});

export default store