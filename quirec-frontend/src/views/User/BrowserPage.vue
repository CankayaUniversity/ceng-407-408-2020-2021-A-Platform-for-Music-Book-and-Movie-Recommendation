<template>
  <v-container>
    <v-flex>
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
                  :headers="headerms"
                  :items="music"
                  :items-per-page="10"
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
                  :headers="headerb"
                  :items="books"
                  :items-per-page="10"
              >
                <template v-slot:item.cover="{ item }">
                  <v-img :src= item.cover >
                  </v-img>
              </template>
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
                  :headers="headerm"
                  :items="movies"
                  :items-per-page="10"
              >
                <template v-slot:item.poster="{ item }">
                  <v-img width="100px" height="150px" :src= "'https://www.themoviedb.org/t/p/w1280/' + item.poster ">
                  </v-img>
                </template>
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
      headerms:[{
        text: 'Title',
        align: 'start',
        filterable: false,
        value: 'title',
      },
        {
          text: 'Artist', value: 'artist'
      }],
      headerm: [{
        text: 'Title',
        align: 'start',
        filterable: false,
        value: 'title',
      },
        {
          text: 'Language', value: 'language'
        },
        {text: 'Poster', value:'poster'}

      ],
      headerb: [{
        text: 'Title',
        align: 'start',
        filterable: false,
        value: 'title',

      },
        {text: 'Author', value: 'author'},
        {text: 'Cover of the Book', value:'cover'}

      ],
      music: [],
      books: [],
      movies: []
    }
  },
  async mounted() {
    this.getItems();
  },
  methods: {
    async getItems() {

      const response1 = await this.axios.get('http://localhost:9000/quirec-api/browse/music');
      for (let element of response1.data) {
        this.music.push(element)

      }

      const response2 = await this.axios.get('http://localhost:9000/quirec-api/browse/book');

      for (let element of response2.data) {
        this.books.push(element)
      }

      const response3 = await this.axios.get('http://localhost:9000/quirec-api/browse/movie');
      let i = 0;
      for (let element of response3.data) {
        this.movies.push(element)


      }
    }
  }
};
</script>
<style scoped>
</style>

