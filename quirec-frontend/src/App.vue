<template>
  <v-app>
    <Header v-if="$store.getters.getRole==='user'"/>
    <AdminHeader v-if="$store.getters.getRole==='admin'"></AdminHeader>
    <router-view/>
  </v-app>
</template>

<script>
import Header from "./views/Header";
import AdminHeader from "./views/AdminHeader";
export default {
  components: {
    Header,
    AdminHeader
  }
}
</script>

<style>
#app {
  background-color: #757575;
}
</style>