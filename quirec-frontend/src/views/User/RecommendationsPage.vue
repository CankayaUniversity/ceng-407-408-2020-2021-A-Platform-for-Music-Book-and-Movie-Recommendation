<template>
  <v-container>
    <v-flex>
      <v-card class="py-15 px-12 secondary text-center" rounded>
          <p class="text-h6">Music</p>
          <v-row>
            <v-col
                v-for="n in 4"
                :key="n"
                cols="10"
                md="3"
            >
              <v-card class="mx-5 my-5 primary" outlined>

              </v-card>
            </v-col>
          </v-row>
        <v-btn v-on:click="musicIndex -= 4"><span class="text-h6 text--primary">←&nbsp;</span></v-btn>
        <v-btn v-on:click="musicIndex += 4"><span class="text-h6 text--primary">&nbsp;→</span></v-btn>
      </v-card>

      <v-card class="my-12 py-15 px-12 secondary text-center" rounded>
        <p class="text-h6">Books</p>
        <v-row>
          <v-col
              v-for="n in 4"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>

            </v-card>
          </v-col>
        </v-row>
        <v-btn v-on:click="bookIndex -= 4"><span class="text-h6 text--primary">←&nbsp;</span></v-btn>
        <v-btn v-on:click="bookIndex += 4"><span class="text-h6 text--primary">&nbsp;→</span></v-btn>
      </v-card>

      <v-card class="my-12 py-15 px-12 secondary text-center" rounded>
        <p class="text-h6">Movies</p>
        <v-row>
          <v-col
              v-for="n in 4"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>

            </v-card>
          </v-col>
        </v-row>
        <v-btn v-on:click="movieIndex -= 4"><span class="text-h6 text--primary">←&nbsp;</span></v-btn>
        <v-btn v-on:click="movieIndex += 4"><span class="text-h6 text--primary">&nbsp;→</span></v-btn>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
  export default {
    data() {
      return {
        music: [],
        books: [],
        movies: [],
        bookIndex: -1,
        movieIndex: -1,
        musicIndex: -1
      }
    },
    async mounted() {
      await this.getItems();
    },
    methods: {
      async getItems() {
        const music = await this.axios.get('http://localhost:9000/quirec-api/browse/music');
        for (let element of music.data) {
          this.music.push(element)
        }
      },
      navToDetails (resourceId) {
        //TODO: music/book/movie ayrımı yapılmalı
        //TODO: recommendations ya da browser page ayrımı yapılmalı ona göre rate çıkacak
        this.$router.push({ path: 'details/' + resourceId});
      }
    }
  };
</script>

