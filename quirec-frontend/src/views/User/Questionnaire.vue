<template>
  <v-container>
  <v-container v-if="!showPartTwo">
  <v-row>
    <v-col cols="12" sm="4" md="4"  >
      <v-card class="mt-15 secondary" flat>
        <v-card-text>
          <p class="text-h6 text-left ml-7 pink--text">Music</p>
          <ul>
            <v-checkbox
              v-model="selectedCategories"
              v-for="item in MusicList"
              :key="item.id"
              color="#c2185b"
              :label="item.name"
              :value="item"
              >{{ item.name }}</v-checkbox
            >
          </ul>
          <v-col cols="4">
            <v-img :src="require('@/assets/rounded-headphones.png')"> </v-img>
          </v-col>
        </v-card-text>
      </v-card>
    </v-col>

    <v-col cols="12" sm="4" md="4">
      <v-card class="mt-15 secondary" flat>
        <v-card-text>
          <p class="text-h6 text-left ml-7 pink--text">Books</p>
          <ul>
            <v-checkbox
              v-model="selectedCategories"
              v-for="item in BookList"
              :key="item.id"
              color="#c2185b"
              :label="item.name"
              :value="item"
              >{{ item.name }}</v-checkbox
            >
          </ul>
          <v-col cols="4">
            <v-img :src="require('@/assets/book.png')"> </v-img>
          </v-col>
        </v-card-text>
      </v-card>
    </v-col>

    <v-col cols="12" sm="4" md="4">
      <v-card class="mt-15 secondary" flat>
        <v-card-text>
          <p class="text-h6 text-left ml-7 pink--text">Movies</p>
          <ul>
            <v-checkbox
              v-model="selectedCategories"
              v-for="item in MovieList"
              :key="item.id"
              color="#c2185b"
              :label="item.name"
              :value="item"
              >{{ item.name }}</v-checkbox
            >
          </ul>
          <v-col cols="4">
            <v-img :src="require('@/assets/clapperboard.png')"> </v-img>
          </v-col>
        </v-card-text>
      </v-card>
      <v-layout align-center justify-center mt-10>
        <v-btn @click="show()" class="#primary" large rounded>Submit My Preferences</v-btn>
      </v-layout>
    </v-col>
  </v-row>
  </v-container>
  <v-container v-else-if="showPartTwo">
    <v-flex>
      <v-card class="py-15 px-12 secondary text-center" rounded>
        <p class="text-h6">Music</p>
        <v-row>
          <v-col
              v-for="n in musicNum"
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
              <v-rating
                  @click="$emit(rate(movieIndex+n , rating))"
                  v-model="rating"
                  background-color="#c2185b"
                  color="black"
                  dense
                  half-increments
                  hover
                  size="25"
              ></v-rating>

              <span class="grey--text text--lighten-2 caption mx-3">
            ({{ rating }})
          </span>
            </v-card>
          </v-col>
        </v-row>
      </v-card>
      <v-card class="py-15 px-12 secondary text-center" rounded>
        <p class="text-h6">Book</p>
        <v-row>
          <v-col
              v-for="n in bookNum"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <p>
                <v-img :src=books[bookIndex+n].cover aspect-ratio="0.7"></v-img>
                {{ books[bookIndex + n].title }} <br>
                {{ books[bookIndex + n].artist }}
              </p>
              <v-rating
                  @click="$emit(rate(movieIndex+n , rating))"
                  v-model="rating"
                  background-color="#c2185b"
                  color="black"
                  dense
                  half-increments
                  hover
                  size="25"
              ></v-rating>

              <span class="grey--text text--lighten-2 caption mx-3">
            ({{ rating }})
          </span>
            </v-card>
          </v-col>
        </v-row>
      </v-card>
      <v-card class="py-15 px-12 secondary text-center" rounded @ratechange="rate()">
        <p class="text-h6">Movie</p>
        <v-row>
          <v-col
              v-for="n in movieNum"
              :key="n"
              cols="10"
              md="3"
          >
            <v-card class="mx-5 my-5 primary" outlined>
              <p>
                <v-img :src="'https://www.themoviedb.org/t/p/w1280/' + movies[movieIndex+n].poster"
                       aspect-ratio="0.7"></v-img>
                {{ movies[movieIndex + n].title }} <br>
                {{ movies[movieIndex + n].artist }}
              </p>
              <v-rating
                  @click="$emit(rate(movieIndex+n , rating))"
                  v-model="rating"
                  background-color="#c2185b"
                  color="black"
                  dense
                  half-increments
                  hover
                  size="25"
              ></v-rating>

              <span class="grey--text text--lighten-2 caption mx-3">
            ({{ rating }})
          </span>
            </v-card>
          </v-col>
        </v-row>
      </v-card>
    </v-flex>
  </v-container>
  </v-container>
</template>


<script>
export default {
  name: "questionnaire",

  data() {
    return {
      singleSelect: false,
      selectedCategories: [],
      MusicList: [],
      BookList: [],
      MovieList: [],
      showPartTwo:false,
      musicIndex: -1,
      bookIndex: -1,
      movieIndex: -1,
      music: [],
      books: [],
      movies: [],
      dataFetch: false,
      bookNum: 0,
      movieNum: 0,
      musicNum: 0,
      rating:0,
      rates: [{
        categoryId: "",
        itemId: "",
        userRating:"",
      }
      ],
      rateIndex:0,
    }
  },
  async mounted() {
    await this.getCategories()
  },
  methods:{
    rate(index, rating){

        this.rates[this.rateIndex].categoryId = this.movies[index].categories.id;
        this.rates[this.rateIndex].itemId = this.movies[index].id;
        this.rates[this.rateIndex].userRating = rating;
      console.log(
          this.rates[0].userRating)
    },
    show(){
      this.showPartTwo = true
      this.getItems();
    },
    async getCategories(){
      this.showPartTwo = false
      const categories = await this.axios.get('http://localhost:9000/quirec-api/questionnaire/categories')
      for (let element of categories.data) {
        if(element.categoryType === "MUSIC")
          this.MusicList.push(element)
        else if(element.categoryType === "MOVIE")
          this.MovieList.push(element)
        else if(element.categoryType === "BOOK")
          this.BookList.push(element)
      }
      this.dataFetch = true

    },
    saveCategories() {
      this.axios.post('http://localhost:9000/quirec-api/questionnaire/user/' + this.$store.getters.getUserId,
         this.selectedCategories)
          .then(response => {
            this.showPartTwo = true
            this.getItems();
          })

    },
    async getItems() {
      let im = 0;
      for (let element of this.selectedCategories) {
        if(element.categoryType === "MUSIC"){
          this.axios.get('http://localhost:9000/quirec-api/browse/musicSearch/' + element.name).then(
              response => {
                console.log(response.data)
                this.music.splice(0);
                this.musicIndex = -1;
                for (let element of response.data) {
                    this.$set(this.music, im, element)
                    ++im;
                }
                if(im > 4)
                  this.musicNum = 4;
                else
                  this.musicNum = im;
              })
        }
        else if(element.categoryType === "BOOK"){
          let ib = 0;
          this.axios.get('http://localhost:9000/quirec-api/browse/bookSearch/' + element.name).then(
              response => {
                console.log(response.data)
                this.books.splice(0);
                this.bookIndex = -1;
                for (let element of response.data) {
                  this.$set(this.books, ib, element)
                  ++ib;
                }
                if(ib > 4)
                 this.bookNum = 4;
                else
                  this.bookNum = ib;
              })
        }
        else if(element.categoryType === "MOVIE"){
          let imv = 0;
          this.axios.get('http://localhost:9000/quirec-api/browse/movieSearch/' + element.name).then(
              response => {
                console.log(response.data)
                this.movies.splice(0);
                this.movieIndex = -1;
                for (let element of response.data) {
                  this.$set(this.movies, imv, element)
                  ++imv;
                }
                if(imv > 4)
                  this.movieNum = 4;
                else
                  this.movieNum = imv;
              })
        }
      }
    },
  }
};
</script>