<template>
  <v-container>
    <v-flex>
      <v-layout class="d-flex justify-end pa-2">
        <v-btn large color="#c2185b" rounded>Login</v-btn>
        <v-btn class="ml-10" large color="#c2185b" rounded>Sign Up</v-btn>
      </v-layout>

      <v-card class="my-15 mx-15 elevation-0">
        <v-img :src="require('@/assets/QuiRec.png')"></v-img>
      </v-card>

      <p class="text-center text-h4 font-weight-bold">
        A Quick, handy recommender.<br/>
        One application with countless Movies, Music, Books.
      </p>
      <v-row class="mt-15">
        <v-col cols="4">
          <v-img :src="require('@/assets/clapperboard.png')"></v-img>
        </v-col>
        <v-col cols="4">
          <v-img :src="require('@/assets/rounded-headphones.png')"></v-img>
        </v-col>
        <v-col cols="4">
          <v-img :src="require('@/assets/book.png')"></v-img>
        </v-col>
      </v-row>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "mainpage",
};
</script>