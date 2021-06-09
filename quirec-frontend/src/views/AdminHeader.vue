<template>
  <v-card
      color="white"
      flat
      height="50px"
      tile
  >
    <v-toolbar dense>
      <v-toolbar-title>
        QuiRec
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn @click="logout" :class="visibleClass">
        Logout
      </v-btn>
    </v-toolbar>
  </v-card>
</template>

<script>
export default {
  name: "AdminHeader.vue",
  methods: {
    async logout() {
      this.$store.commit('clearUserToken')
      this.$store.commit('clearUserId')
      this.$store.commit('clearRole')
      this.$router.push("/login")
    }
  },
  computed: {
    visibleClass() {
      return {
        'd-none': !this.$store.getters.getToken
      }
    }
  }
}
</script>

<style scoped>
</style>