<template>
  <v-app>
    <Header/>
    <router-view/>
  </v-app>
</template>

<script>
import Header from "./views/Header";
export default {
  components: {
    Header
  }
}
</script>

<style>
#app {
  background-color: #757575;
}
</style>