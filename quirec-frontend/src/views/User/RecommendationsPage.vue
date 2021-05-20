<template>
  <v-container>
    <v-flex>
      <v-card class="py-15 px-12 secondary text-center" rounded v-if="dataFetch">
          <p class="text-h6">Music</p>
          <v-row>
            <v-col
                v-for="n in im"
                :key="n"
                cols="10"
                md="3"
            >
              <v-card class="mx-5 my-5 primary" outlined>
                <p>
                  <v-img :src=music[musicIndex+n].cover aspect-ratio="0.7"></v-img>
                  {{ music[musicIndex + n].title }} <br>
                  {{ music[musicIndex + n].artist }}
                </p>
                <v-btn v-on:click="navToDetails('music', music[musicIndex+n].resourceId)" class="secondary white--text"> see
                  details
                </v-btn>
              </v-card>
            </v-col>
          </v-row>
        <v-btn v-on:click="musicIndex -= im"><span class="text-h6 text--primary">←&nbsp;</span></v-btn>
        <v-btn v-on:click="musicIndex += im"><span class="text-h6 text--primary">&nbsp;→</span></v-btn>
      </v-card>

      <v-card class="my-12 py-15 px-12 secondary text-center" rounded v-if="dataFetch">
        <p class="text-h6">Books</p>
        <v-row>
          <v-col
              v-for="n in ib"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <p>
                <v-img :src=books[bookIndex+n].cover aspect-ratio="0.7"></v-img>
                {{ books[bookIndex + n].title }} <br>
                {{ books[bookIndex + n].author }}
              </p>
              <v-btn v-on:click="navToDetails('book', books[bookIndex+n].resourceId)" class="secondary white--text"> see
                details
              </v-btn>
            </v-card>
          </v-col>
        </v-row>
        <v-btn v-on:click="bookIndex -= ib"><span class="text-h6 text--primary">←&nbsp;</span></v-btn>
        <v-btn v-on:click="bookIndex += ib"><span class="text-h6 text--primary">&nbsp;→</span></v-btn>
      </v-card>

      <v-card class="my-12 py-15 px-12 secondary text-center" rounded v-if="dataFetch">
        <p class="text-h6">Movies</p>
        <v-row>
          <v-col
              v-for="n in imv"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <p>
                <v-img :src="'https://www.themoviedb.org/t/p/w1280/' + movies[movieIndex+n].poster"
                       aspect-ratio="0.7"></v-img>
                {{ movies[movieIndex + n].title }} <br>
                Language: {{ movies[movieIndex + n].language }}
              </p>
              <v-btn v-on:click="navToDetails('movies', movies[movieIndex+n].resourceId)" class="secondary white--text"> see
                details
              </v-btn>
            </v-card>
          </v-col>
        </v-row>
        <v-btn v-on:click="movieIndex -= imv"><span class="text-h6 text--primary">←&nbsp;</span></v-btn>
        <v-btn v-on:click="movieIndex += imv"><span class="text-h6 text--primary">&nbsp;→</span></v-btn>
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
        musicIndex: -1,
        dataFetch: false,
        im: 0,
        ib: 0,
        imv: 0,

      }
    },
    async mounted() {
      await this.getItems();
    },
    methods: {
      async getItems() {
        const response = await this.axios.get('http://localhost:9000/quirec-api/recommendation/user/' + this.$store.getters.getUserId);
        let iMusic = 0;
        let iBook = 0;
        let iMovie = 0;

        this.music.splice(0);
        for(let element of response.data.musicRecommendations) {
          this.$set(this.music, iMusic, element)
          ++iMusic
        }

        this.books.splice(0);
        for(let element of response.data.bookRecommendations) {
          this.$set(this.music, iBook, element)
          ++iBook
        }

        this.movies.splice(0);
        for(let element of response.data.movieRecommendations) {
          this.$set(this.music, iMovie, element)
          ++iMovie
        }
        this.dataFetch = true
        if(iMusic < 4)
          this.im = iMusic
        else
          this.im = 4
        if(iBook < 4)
          this.ib = iBook
        else
          this.ib = 4
        if(iMovie < 4)
          this.imv = iMovie
        else
          this.imv = 4

      },
      navToDetails(category, resourceId) {
        this.$router.push({path: 'details/' + category + "/" + resourceId, props: true});
      },
    }
  };
</script>

