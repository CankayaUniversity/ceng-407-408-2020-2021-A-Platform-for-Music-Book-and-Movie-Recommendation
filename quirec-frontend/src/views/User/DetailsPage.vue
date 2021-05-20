<template>
<v-container fill-height>
  <v-flex>
    <v-card height="400px">
      <v-simple-table class="px-10 py-10">
        <template v-slot:default>
          <thead>
          <tr>
            <th class="text-left">Name</th>
            <th class="text-left">Producer</th>
            <th class="text-left">Image</th>
          </tr>
          </thead>

          <tbody>
          <tr >
            <td>{{ name }}</td>
            <td>{{ producer }}</td>
            <td>
              <v-img :src="cover" aspect-ratio="1.5"></v-img>
            </td>
          </tr>
          </tbody>
        </template>
      </v-simple-table>

      <v-card-actions class="align-end justify-end mx-10 mb-3" v-if="showRating">
        Rate
        <v-rating
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
      </v-card-actions>

      <v-card-actions class="align-end justify-end mr-15 secondary">
        <v-btn v-on:click="back()" rounded>Return to previous page</v-btn>
      </v-card-actions>
    </v-card>
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
        showRating: false
      };
    },
    async mounted(){
      await this.getDetails();
    },
    methods: {
      async getDetails() {
        this.showRating = this.$router.history.current.props;

        const category = this.$router.history.current.params.category;

        if(category === 'music') {
          const response = await this.axios.get('https://cors-anywhere.herokuapp.com/https://api.deezer.com/track/' + this.$router.history.current.params.resourceId)
          this.name = response.data.title
          this.producer = response.data.artist.name
          this.cover = response.data.album.cover_xl
        }

        else if(category === 'book') {
          const response = await this.axios.get('https://cors-anywhere.herokuapp.com/https://www.googleapis.com/books/v1/volumes/' + this.$router.history.current.params.resourceId + "")
          this.name = response.data.volumeInfo.title
          this.producer = response.data.volumeInfo.authors[0]
          this.cover = response.data.imageLinks.smallThumbnail
        }

        else if(category === 'movie') {
          const response = await this.axios.get('https://cors-anywhere.herokuapp.com/https://www.api.themoviedb.org/3/movie/' + this.$router.history.current.params.resourceId + "?api_key=7f613a475981323801d5497ba77b29b5")
          this.name = response.data.original_title
          this.producer = response.data.productiom_companies[0].name
          this.cover = 'https://www.themoviedb.org/t/p/w1280/' + response.data.poster_path
        }
      },
      back() {
        this.$router.go(-1);
      }
    }
  };
</script>
