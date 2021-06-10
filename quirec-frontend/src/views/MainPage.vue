<template>
  <v-container>
    <v-flex>
      <v-layout class="d-flex justify-end pa-2">
        <v-btn to="/login" large color="#c2185b" rounded>Login</v-btn>
        <v-btn to="/register" class="ml-10" large color="#c2185b" rounded>Sign Up</v-btn>
      </v-layout>
      <v-row align="center" justify="space-around">
        <v-card class="my-15 mx-15 elevation-0 " max-width="700">
          <v-img align-center :src="require('@/assets/QuiRec.png')"></v-img>
        </v-card>

      </v-row>

      <p class="text-center text-h4 font-weight-bold">
        A Quick, handy recommender.<br/>
        One application with countless Movies, Music, Books.
      </p>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "mainpage",
};
</script>
