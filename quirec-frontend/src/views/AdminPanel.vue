<template>
  <v-container>
    <p class="text-h3 text-center">Admin Panel</p>

    <v-row
        v-for="item of value"
        :key="item.id"
    >
      <v-col cols="12" sm="6" md="6">
        <v-card class="my-10" color="#c2185b" dark>
          <v-card-text>
            <div class="display-1 font-weight-thin">{{item.categoryType}}</div>
          </v-card-text>
          <v-card-text>
            <v-sheet color="rgba(0, 0, 0, .12)">
              <v-sparkline
                  color="rgba(255, 255, 255, .7)"
                  height="100"
                  padding="24"
                  stroke-linecap="round"
                  smooth
              >
                <template v-slot:label="item"> {{ item.value.rate }}% </template>
              </v-sparkline>
            </v-sheet>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-layout class="d-flex justify-end pa-5 mt-10">
      <v-btn to="/Login" class="pa-5 justify-end" large color="#212121" rounded>Log Out</v-btn>
      <v-divider></v-divider>
      <v-btn @click="dbUpdate()" large color="#c2185b" rounded>Update Database</v-btn>
      <v-btn class="ml-5" large color="#c2185b" rounded>Model Retrain</v-btn>
    </v-layout>

    <br />
  </v-container>
</template>

<script>
export default {
  name: "adminpanel",
  data() {
    return {
      value: [],
    }
  },
  async mounted() {
    await this.getRatings();
  },
  methods: {
    async getRatings(){
      const response1 = await this.axios.get('http://localhost:9000/quirec-api/admin');
      for(let element of response1.data){
        this.value.push(element)
      }
    },
    async dbUpdate(){
      await this.axios.post('http://localhost:9000/quirec-api/admin/databaseUpdate');
      this.$router.push("/browserpage")
    }
  },
};
</script>