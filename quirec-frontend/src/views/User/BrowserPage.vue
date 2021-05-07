<template>
  <v-container>
    <v-flex>
      <v-card class="py-15 px-12 secondary text-center" rounded>
          <p class="text-h6">Music</p>
          <v-row>
            <v-card class="mx-5 my-5 primary" outlined>
            </v-card>
          </v-row>
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
              <p>
                <v-img :src= books[bookIndex+n].cover aspect-ratio="0.7"></v-img>
                {{books[bookIndex+n].title}} <br>
                Author: {{books[bookIndex+n].author}}
              </p>
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
              <p>
                <v-img :src= "'https://www.themoviedb.org/t/p/w1280/' + movies[movieIndex+n].poster" aspect-ratio="0.7"></v-img>
                {{movies[movieIndex+n].title}} <br>
                Language: {{movies[movieIndex+n].language}}
              </p>
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
        bookIndex: 0,
        movieIndex: 0
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

        const books = await this.axios.get('http://localhost:9000/quirec-api/browse/book');
        for (let element of books.data) {
          this.books.push(element)
        }

        const movies = await this.axios.get('http://localhost:9000/quirec-api/browse/movie');
        for (let element of movies.data) {
          this.movies.push(element)
        }
      }
    }
  };
</script>

