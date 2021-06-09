<template>
  <v-container>
    <v-flex>
      <v-card class="py-15 px-12 secondary text-center" rounded>
        <p class="text-h6" v-if="musicNum!==0">Music</p>
        <v-row>
          <v-col
              v-for="n in musicNum"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <v-img :src=music[musicIndex+n].cover aspect-ratio="0.7"></v-img>
              {{ music[musicIndex + n].title }} <br>
              {{ music[musicIndex + n].artist }}
              <v-rating
                  @input="musicrate(musicIndex+n , ratingm[musicIndex+n])"
                  v-model="ratingm[musicIndex+n]"
                  background-color="black"
                  color="black"
                  dense
                  half-increments
                  hover
                  size="25"
              ></v-rating>
              <span class="grey--text text--lighten-2 caption mx-3">
                ({{ ratingm[musicIndex + n] }})
              </span>
            </v-card>
          </v-col>
        </v-row>

        <p class="text-h6" v-if="bookNum!==0">Book</p>
        <v-row>
          <v-col
              v-for="n in bookNum"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <v-img :src=books[bookIndex+n].cover aspect-ratio="0.7"></v-img>
              {{ books[bookIndex + n].title }} <br>
              {{ books[bookIndex + n].artist }}
              <v-rating
                  @input="bookrate(bookIndex+n , ratingb[bookIndex + n])"
                  v-model="ratingb[bookIndex + n]"
                  background-color="black"
                  color="black"
                  dense
                  half-increments
                  hover
                  size="25"
              ></v-rating>
              <span class="grey--text text--lighten-2 caption mx-3">
                ({{ ratingb[bookIndex + n] }})
              </span>
            </v-card>
          </v-col>
        </v-row>

        <p class="text-h6" v-if="movieNum!==0">Movie</p>
        <v-row>
          <v-col
              v-for="n in movieNum"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <v-img :src="'https://www.themoviedb.org/t/p/w1280/' + movies[movieIndex+n].poster"
                     aspect-ratio="0.7"></v-img>
              {{ movies[movieIndex + n].title }} <br>
              {{ movies[movieIndex + n].artist }}
              <v-rating
                  @input="movierate(movieIndex+n , ratingmv[movieIndex + n])"
                  v-model="ratingmv[movieIndex + n]"
                  background-color="black"
                  color="black"
                  dense
                  half-increments
                  hover
                  size="25"
              ></v-rating>
              <span class="grey--text text--lighten-2 caption mx-3">
                ({{ ratingmv[movieIndex + n] }})
              </span>
            </v-card>
          </v-col>
        </v-row>

        <v-layout align-center justify-end mt-10>
          <v-btn v-on:click="submit()" class="primary" large rounded>Submit Preferences</v-btn>
        </v-layout>
        <br/>
        <v-alert v-if="successMessage" type="success"> Questionnaire has filled successfully, see the recommendations.
          <br/>
          <v-btn to="/recommendations">Go to Recommendations</v-btn>
        </v-alert>
        <v-alert v-if="errorMessage" type="error">
          An error has occurred.
        </v-alert>

      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      successMessage: false,
      errorMessage: false,
      bookNum: 0,
      movieNum: 0,
      musicNum: 0,
      ratingm: [],
      ratingb: [],
      ratingmv: [],
      rates: [],
      mrates: [],
      brates: [],
      mvrates: [],
      mrateIndex: 0,
      brateIndex: 0,
      mvrateIndex: 0,
      musicIndex: -1,
      bookIndex: -1,
      movieIndex: -1,
      music: [],
      books: [],
      movies: []
    }
  },
  async mounted() {
    await this.getItems()
  },
  methods: {
    async getItems() {
      let im = 0, ib = 0, imv = 0;
      let countM = 0, countB = 0, countMv = 0;
      const selectedCategories = this.$store.getters.getSelectedCategories;
      for (let element of selectedCategories) {
        if (element.categoryType === "MUSIC") {
          this.axios.get('http://localhost:9000/quirec-api/browse/musicSearch/' + element.name)
              .then(response => {
                this.musicIndex = -1;
                countM = 0;
                for (let element of response.data) {
                  if (countM >= 4) break;
                  this.$set(this.music, im, element)
                  ++im;
                  countM++;
                }
                this.musicNum = im;
              })
        } else if (element.categoryType === "BOOK") {
          this.axios.get('http://localhost:9000/quirec-api/browse/bookSearch/' + element.name)
              .then(response => {
                this.bookIndex = -1;
                countB = 0;
                for (let element of response.data) {
                  if (countB >= 4) break;
                  this.$set(this.books, ib, element)
                  ++ib;
                  countB++;
                }
                this.bookNum = ib;
              })
        } else if (element.categoryType === "MOVIE") {
          this.axios.get('http://localhost:9000/quirec-api/browse/movieSearch/' + element.name)
              .then(response => {
                this.movieIndex = -1;
                countMv = 0;
                for (let element of response.data) {
                  if (countMv >= 4) break;
                  this.$set(this.movies, imv, element)
                  ++imv;
                  countMv++;
                }
                this.movieNum = imv;
              })
        }
      }
    },
    musicrate(index, rating) {
      this.mrates.push({
        categoryId: this.music[index].categories[0].id,
        itemId: this.music[index].id,
        userRating: rating
      })
      this.mrateIndex++;
    },
    bookrate(index, rating) {
      this.brates.push({
        categoryId: this.books[index].categories[0].id,
        itemId: this.books[index].id,
        userRating: rating
      })
      this.brateIndex++;
    },
    movierate(index, rating) {
      this.mvrates.push({
        categoryId: this.movies[index].categories[0].id,
        itemId: this.movies[index].id,
        userRating: rating
      })
      this.mvrateIndex++;
    },
    async submit() {
      if (this.mrates.length !== 0) {
        for (let element of this.mrates)
          this.rates.push(element)
      }
      if (this.brates.length !== 0) {
        for (let element of this.brates)
          this.rates.push(element)
      }
      if (this.mvrates.length !== 0) {
        for (let element of this.mvrates)
          this.rates.push(element)
      }

      await this.axios.post("http://localhost:9000/quirec-api/questionnaire/user/" + this.$store.getters.getUserId, this.rates)
          .then(response => {
            this.successMessage = true;
            this.errorMessage = false;
          })
          .catch(error => {
            this.successMessage = false;
            this.errorMessage = true;
          });
    }
  }
}
</script>
