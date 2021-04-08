<template>
  <v-container>
    <v-flex>
      <v-layout class="d-flex justify-end pa-2">
        <v-btn to="/ProfilePage" class="ml-10" large color="#c2185b" rounded>Profile</v-btn>
      </v-layout>

      <v-card class="py-15 px-12" rounded color="#212121">
        <v-card-text class="text-center">
          <p class="text-h6 text-center">Music</p>
          <v-row>
            <v-card
                class="mx-5 my-5"
                outlined
                color="#c2185b"
            >
             <v-data-table
                :items="music"
                :items-per-gage="10"
             >
             </v-data-table>
            </v-card>
          </v-row>
        </v-card-text>
      </v-card>

      <v-card class="my-12 py-15 px-12" rounded color="#212121">
        <v-card-text class="text-center">
          <p class="text-h6 text-center">Books</p>
          <v-row>
            <v-card
                class="mx-5 my-5"
                outlined
                color="#c2185b"
            >
              <v-data-table
                :items="books"
                :items-per-page="10"
              >
              </v-data-table>
            </v-card>
          </v-row>
        </v-card-text>
      </v-card>
      <v-card class="my-12 py-15 px-12" rounded color="#212121">
        <v-card-text class="text-center">
          <p class="text-h6 text-center">Movies</p>
          <v-row>
            <v-card
                class="mx-5 my-5"
                outlined
                color="#c2185b"
            >
              <v-data-table
                :items="movies"
                :items-per-page="10"
              >
             </v-data-table>
            </v-card>
          </v-row>
        </v-card-text>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "browserpage",
  data() {
    return {
      music: [],
      books: [],
      movies: []
    }
  },
  async mounted () {
    this.getItems();
  },
  methods: {
    async getItems() {
        this.books = [];
        const response2 = await this.axios.get('http://localhost:9000/quirec-api/browse/book');
        for(let element of response2.data){
            this.books.push(element)
        }
    }
  }
};
</script>
<style scoped>
</style>
