<template>
  <v-row>
    <v-col cols="12" sm="4" md="4">
      <v-card class="mt-15" flat color="#212121">
        <v-card-text>
          <p class="text-h6 text-left ml-7 pink--text">Music</p>
          <ul>
            <v-checkbox
              v-for="item in MusicList"
              :key="item.id"
              color="#c2185b"
              :label="item.title"
              >{{ item.title }}</v-checkbox
            >
          </ul>
          <v-col cols="4">
            <v-img :src="require('@/assets/rounded-headphones.png')"> </v-img>
          </v-col>
        </v-card-text>
      </v-card>
    </v-col>

    <v-col cols="12" sm="4" md="4">
      <v-card class="mt-15" flat color="#212121">
        <v-card-text>
          <p class="text-h6 text-left ml-7 pink--text">Books</p>
          <ul>
            <v-checkbox
              v-for="item in BookList"
              :key="item.id"
              color="#c2185b"
              :label="item.title"
              >{{ item.title }}</v-checkbox
            >
          </ul>
          <v-col cols="4">
            <v-img :src="require('@/assets/book.png')"> </v-img>
          </v-col>
        </v-card-text>
      </v-card>
    </v-col>
    <v-col cols="12" sm="4" md="4">
      <v-card class="mt-15" flat color="#212121">
        <v-card-text>
          <p class="text-h6 text-left ml-7 pink--text">Movies</p>
          <ul>
            <v-checkbox
              v-for="item in MovieList"
              :key="item.id"
              color="#c2185b"
              :label="item.title"
              >{{ item.title }}</v-checkbox
            >
          </ul>
          <v-col cols="4">
            <v-img :src="require('@/assets/clapperboard.png')"> </v-img>
          </v-col>
        </v-card-text>
      </v-card>
      <v-layout align-center justify-center mt-10>
        <v-btn large color="#c2185b" rounded>Submit My Preferences</v-btn>
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
      selected: [],
      MusicList: [
        {
          id: 1,
          title: "Pop",
        },
        {
          id: 2,
          title: "Rock",
        },
        {
          id: 3,
          title: "Country",
        },
        {
          id: 3,
          title: "Blues",
        },
        {
          id: 4,
          title: "Hip Hop",
        },
        {
          id: 5,
          title: "Classic",
        },
      ],
      BookList: [
        {
          id: 6,
          title: "Science",
        },
        {
          id: 7,
          title: "Literature",
        },
        {
          id: 8,
          title: "Philosophy",
        },
        {
          id: 8,
          title: "Self-Help",
        },
        {
          id: 8,
          title: "Psychology",
        },
        {
          id: 8,
          title: "History",
        },
      ],
      MovieList: [
        {
          id: 7,
          title: "Action",
        },
        {
          id: 8,
          title: "Adventure",
        },
        {
          id: 9,
          title: "Comedy",
        },
        {
          id: 9,
          title: "Crime",
        },
        {
          id: 9,
          title: "Drama",
        },
        {
          id: 9,
          title: "Romance",
        },
      ],
    };
  },
};
</script>