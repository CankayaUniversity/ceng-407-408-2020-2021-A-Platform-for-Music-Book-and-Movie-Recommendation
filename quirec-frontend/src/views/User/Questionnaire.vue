<template>
  <v-row >
    <v-col cols="12" sm="4" md="4">
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
        <span>Checked names: {{ selectedCategories }}</span>
        <v-btn v-on:click="saveCategories" class="#primary" large rounded>Submit My Preferences</v-btn>
      </v-layout>
    </v-col>
  </v-row>
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
    }
  },
  async mounted() {
    await this.getCategories()
  },
  methods:{
    async getCategories(){
      const categories = await this.axios.get('http://localhost:9000/quirec-api/questionnarie/categories')
      for (let element of categories.data) {
        if(element.categoryType === "MUSIC")
          this.MusicList.push(element)
        else if(element.categoryType === "MOVIE")
          this.MovieList.push(element)
        else if(element.categoryType === "BOOK")
          this.BookList.push(element)
      }
    },
    saveCategories() {
      this.axios.post('http://localhost:9000/quirec-api/questionnarie/user/' + this.$store.getters.getUserId,
         this.selectedCategories
      )
    }
  }
};
</script>