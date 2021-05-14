<template>
  <v-container fill-height>
    <v-flex>
      <v-card height="400px">
        <v-simple-table class="px-10 py-10">
          <template v-slot:default>
            <thead>
              <tr>
                <th class="text-left">Song Name</th>
                <th class="text-left">Singer</th>
                <th class="text-left">Album</th>
              </tr>
            </thead>

            <tbody>
              <tr >
                <td>{{ songname }}</td>
                <td>{{ singer }}</td>
                <td>{{ albumname }}</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>

        <v-card-actions class="align-end justify-end mx-10 mb-3">
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

        <v-card-actions class="align-end justify-end mr-15">
          <v-btn large color="#c2185b" rounded>Go Back To Main Page</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "detailspage",
  data() {
    return {
      songname: "",
      singer: "",
      albumname: "",
      rating: "",
      info: ""
    };
  },
  async mounted(){
    await this.getDetails();
  },
  methods: {
    async getDetails() {
     // this.axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';
      const response = ((await this.axios.get('https://api.deezer.com/track/' + this.$router.history.current.params.resourceId + "")).headers)
      this.details.songname = response.data.title 


    }
  }
};
</script>


https://www.googleapis.com/books/v1/volumes/params.resourceId

