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
              <tr v-for="item in details" :key="item.name">
                <td>{{ item.songname }}</td>
                <td>{{ item.singer }}</td>
                <td>{{ item.albumname }}</td>
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
      details: [
        {
          songname: "Gozlerine Teslimim",
          singer: "Sıla",
          albumname: "Joker",
        },
      ],
      rating: 4.3,
    };
  },
};
</script>