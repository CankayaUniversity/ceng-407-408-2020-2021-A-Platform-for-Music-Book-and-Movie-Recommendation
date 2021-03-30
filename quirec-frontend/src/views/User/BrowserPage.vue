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
                v-for="item in MusicList"
                :key="item.id"
                :label="item.title"
                width="300"
                outlined
                color="#c2185b"
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <v-list-item-title class="headline mb-1">
                    {{ item.title }}
                  </v-list-item-title>
                  <v-list-item-subtitle>{{ item.artist }}</v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-avatar
                    tile
                    size="80"
                    color="grey"
                ></v-list-item-avatar>
              </v-list-item>

              <v-card-actions>
                <v-btn to="/DetailsPage" outlined rounded text> Daha fazlasını gör </v-btn>
              </v-card-actions>
            </v-card>
          </v-row>
        </v-card-text>
        <v-pagination
            v-model="pagem"
            :length="6"
            color="#c2185b"
        ></v-pagination>
      </v-card>

      <v-card class="my-12 py-15 px-12" rounded color="#212121">
        <v-card-text class="text-center">
          <p class="text-h6 text-center">Books</p>
          <v-row>
            <v-card
                class="mx-5 my-5"
                v-for="item in MusicList"
                :key="item.id"
                :label="item.title"
                width="300"
                outlined
                color="#c2185b"
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <v-list-item-title class="headline mb-1">
                    {{ item.title }}
                  </v-list-item-title>
                  <v-list-item-subtitle>{{ item.artist }}</v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-avatar
                    tile
                    size="80"
                    color="grey"
                ></v-list-item-avatar>
              </v-list-item>

              <v-card-actions>
                <v-btn to="/DetailsPage" outlined rounded text> Daha fazlasını gör </v-btn>
              </v-card-actions>
            </v-card>
          </v-row>
          <v-pagination
              v-model="pageb"
              :length="6"
              color="#c2185b"
          ></v-pagination>
        </v-card-text>
      </v-card>
      <v-card class="my-12 py-15 px-12" rounded color="#212121">
        <v-card-text class="text-center">
          <p class="text-h6 text-center">Movies</p>
          <v-row>
            <v-card
                class="mx-5 my-5"
                v-for="item in MusicList"
                :key="item.id"
                :label="item.title"
                width="300"
                outlined
                color="#c2185b"
            >
              <v-list-item three-line>
                <v-list-item-content>
                  <v-list-item-title class="headline mb-1">
                    {{ item.title }}
                  </v-list-item-title>
                  <v-list-item-subtitle>{{ item.artist }}</v-list-item-subtitle>
                </v-list-item-content>

                <v-list-item-avatar
                    tile
                    size="80"
                    color="grey"
                ></v-list-item-avatar>
              </v-list-item>

              <v-card-actions>
                <v-btn to="/DetailsPage" outlined rounded text> Daha fazlasını gör </v-btn>
              </v-card-actions>
            </v-card>
          </v-row>
          <v-pagination
              v-model="pagemo"
              :length="6"
              color="#c2185b"
          ></v-pagination>
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
      pagem: 1,
      pageb: 1,
      pagemo: 1,
      MusicList: [
        {
          id: 1,
          title: "Kabahat Bende",
          artist: "Buray, Kehanet",
        },
        {
          id: 2,
          title: "Karar Verdim",
          artist: "Göksel, Arka Bahçem",
        },
        {
          id: 3,
          title: "Kusursuz Gün",
          artist: "Çağrı Sinci, Çığ",
        },
        {
          id: 3,
          title: "Kabahat Bende",
          artist: "Buray, Kehanet",
        },
        {
          id: 4,
          title: "Kabahat Bende",
          artist: "Buray, Kehanet",
        },
        {
          id: 5,
          title: "Kabahat Bende",
          artist: "Buray, Kehanet",
        },
      ],
    };
  },
};
</script>
<style scoped>
.list-cards{

}
</style>
