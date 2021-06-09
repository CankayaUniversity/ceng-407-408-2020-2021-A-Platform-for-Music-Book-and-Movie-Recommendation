<template>
  <v-container fill-height>
    <v-flex>
      <v-card height="400px">
        <v-simple-table class="px-10 py-10">
          <template v-slot:default>
            <thead>
            <tr>
              <th>Image</th>
              <th>Name</th>
              <th>Producer</th>
            </tr>
            </thead>

            <tbody>
            <tr>
              <td>
                <v-img :src="cover" aspect-ratio="1"></v-img>
              </td>
              <td>{{ name }}</td>
              <td>{{ producer }}</td>
            </tr>
            </tbody>
          </template>
        </v-simple-table>

        <v-card-actions class="align-end justify-end mx-10 mb-3" v-if="showRating">
          Did you enjoy this recommendation?
          <v-rating
              class="ml-5"
              v-model="rating"
              background-color="#c2185b"
              color="#c2185b"
              dense
              half-increments
              hover
              size="25"
          ></v-rating>

          <span class="grey--text text--lighten-2 caption mx-3">
            ({{ rating }})
          </span>
          <v-btn class="primary" v-on:click="rate(rating)">
            Rate
          </v-btn>
        </v-card-actions>

        <br/>
        <v-card-actions class="align-end justify-end mx-10 mb-3">
          <v-btn v-on:click="back()" rounded>Return to previous page</v-btn>
        </v-card-actions>
      </v-card>

      <v-dialog width="200" v-model="successMessage" type="success">Thanks for the feedback!
      </v-dialog>
      <v-dialog class="alert" width="500" v-model="errorMessage" type="error">An error occurred, please try again later
      </v-dialog>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      name: "",
      producer: "",
      rating: 0,
      cover: "",
      showRating: false,
      category: "",
      successMessage: false,
      errorMessage: false,
    };
  },
  async mounted() {
    await this.getDetails();
  },
  methods: {
    async getDetails() {
      this.showRating = this.$store.getters.getShowRating;
      this.category = this.$router.history.current.params.category;
      if (this.category.toLowerCase() === 'music') {
        const response = await this.axios.get('https://cors-anywhere.herokuapp.com/https://api.deezer.com/track/' + this.$router.history.current.params.resourceId)
        this.name = response.data.title
        this.producer = response.data.artist.name
        this.cover = response.data.album.cover_xl
      } else if (this.category.toLowerCase() === 'book') {
        const response = await this.axios.get('https://cors-anywhere.herokuapp.com/https://www.googleapis.com/books/v1/volumes/' + this.$router.history.current.params.resourceId + "")
        this.name = response.data.volumeInfo.title
        this.producer = response.data.volumeInfo.authors[0]
        this.cover = response.data.volumeInfo.imageLinks.smallThumbnail
      } else if (this.category.toLowerCase() === 'movie') {
        const response = await this.axios.get('https://cors-anywhere.herokuapp.com/https://api.themoviedb.org/3/movie/' + this.$router.history.current.params.resourceId + "?api_key=7f613a475981323801d5497ba77b29b5")
        this.name = response.data.original_title
        this.producer = response.data.production_companies[0].name
        this.cover = 'https://www.themoviedb.org/t/p/w1280/' + response.data.poster_path
      }
    },
    async rate(value) {
      await this.axios.post('http://localhost:9000/quirec-api/recommendation/user/' + this.$store.getters.getUserId + '/rate', {
        type: this.category,
        itemId: this.$store.getters.getItemId,
        rate: value
      })
          .then(response => {
            this.successMessage = true;
            this.errorMessage = false;
          })
          .catch(error => {
            this.successMessage = false;
            this.errorMessage = true;
          })
    },
    back() {
      this.$router.go(-1);
    }
  }
};
</script>