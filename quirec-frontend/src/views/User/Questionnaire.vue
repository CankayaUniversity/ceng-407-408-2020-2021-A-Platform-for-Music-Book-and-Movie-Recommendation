<template>
  <v-container>
    <v-row>
      <v-col cols="12" sm="4" md="4">
        <v-card class="mt-15 secondary" flat>
          <v-card-text>
            <p class="text-h6 ml-7 pink--text">Music</p>
            <ul>
              <v-checkbox
                  v-model="selectedCategories"
                  v-for="item in MusicList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
              ></v-checkbox>
            </ul>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" sm="4" md="4">
        <v-card class="mt-15 secondary" flat>
          <v-card-text>
            <p class="text-h6 ml-7 pink--text">Books</p>
            <ul>
              <v-checkbox
                  v-model="selectedCategories"
                  v-for="item in BookList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
              ></v-checkbox>
            </ul>
          </v-card-text>
        </v-card>
      </v-col>

      <v-col cols="12" sm="4" md="4">
        <v-card class="mt-15 secondary" flat>
          <v-card-text>
            <p class="text-h6 ml-7 pink--text">Movies</p>
            <ul>
              <v-checkbox
                  v-model="selectedCategories"
                  v-for="item in MovieList"
                  :key="item.id"
                  :label="item.name"
                  :value="item"
              ></v-checkbox>
            </ul>
          </v-card-text>
        </v-card>

        <v-layout align-center justify-end mt-10>
          <v-btn v-on:click="secondPage()" class="#primary" large rounded>Next Page</v-btn>
        </v-layout>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      selectedCategories: [],
      MusicList: [],
      BookList: [],
      MovieList: []
    }
  },
  async mounted() {
    await this.getCategories()
  },
  methods: {
    async getCategories() {
      const categories = await this.axios.get('http://localhost:9000/quirec-api/questionnaire/categories')
      for (let element of categories.data) {
        if (element.categoryType === "MUSIC")
          this.MusicList.push(element)
        else if (element.categoryType === "MOVIE")
          this.MovieList.push(element)
        else if (element.categoryType === "BOOK")
          this.BookList.push(element)
      }
    },
    secondPage() {
      this.$store.commit('setSelectedCategories', this.selectedCategories);
      this.$router.push('/questionnaireTwo');
    }
  }
};
</script>