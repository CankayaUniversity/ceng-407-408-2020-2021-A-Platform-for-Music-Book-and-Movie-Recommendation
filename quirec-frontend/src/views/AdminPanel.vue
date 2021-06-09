<template>
  <v-container>
    <p class="text-h3 text-center">Admin Panel</p>

    <v-row v-for="n in 1" :key="n">
      <v-col cols="6" sm="4"
             v-for="item of value"
             :key="item.id"
      >
        <v-card class="my-3 primary" dark align="center">
          <v-card-text>
            <div class="display-1 font-weight-thin">{{ item.categoryType }}</div>
            <v-progress-circular
                :rotate="360"
                :size="100"
                :width="15"
                :value="100 *(item.rate/item.voteNumber) /10"
                color="white"
            ></v-progress-circular>
            <div class="display-1 font-weight-thin">{{ item.rate / item.voteNumber }}</div>
          </v-card-text>
        </v-card>
      </v-col>
    </v-row>

    <v-divider></v-divider>
    <v-layout class="d-flex justify-center pa-5 mt-10">
      <v-btn @click="dbUpdate()" class="primary" large rounded>Update Database</v-btn>
      <v-btn @click="warningMessage=true" class="ml-5 primary" large rounded>Model Retrain</v-btn>

    </v-layout>

    <v-alert align="left" v-if="warningMessage" type="warning">
      <v-row align="center">
        <v-col class="grow">
          Do you want to continue? <br> This process may take a long time.
        </v-col>
        <v-col class="shrink">
          <v-btn @click="modelRetrain()" type="success">Yes, I do.</v-btn>
        </v-col>
        <v-col class="shrink">
          <v-btn @click="warningMessage=false" type="error">No, I don't.</v-btn>
        </v-col>
      </v-row>
    </v-alert>
    <v-alert v-if="successMessage" type="success">
      Completed successfully.
    </v-alert>
    <v-alert v-if="errorMessage" type="error">
      An error has occurred.
    </v-alert>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      value: [],
      warningMessage: false,
      successMessage: false,
      errorMessage: false,
    }
  },
  async mounted() {
    await this.getRatings();
  },
  methods: {
    async getRatings() {
      const response1 = await this.axios.get('http://localhost:9000/quirec-api/admin');
      for (let element of response1.data) {
        this.value.push(element)
      }
    },
    async dbUpdate() {
      await this.axios.get('http://localhost:9000/quirec-api/admin/databaseUpdate')
          .then(response => {
            this.successMessage = true;
            this.errorMessage = false;
          })
          .catch(error => {
            this.successMessage = false;
            this.errorMessage = true;
          });

    },
    async modelRetrain() {
      this.warningMessage = false;
      await this.axios.get('http://localhost:9000/quirec-api/admin/modelRetrain')
          .then(response => {
            this.successMessage = true;
            this.errorMessage = false;
          })
          .catch(error => {
            this.successMessage = false;
            this.errorMessage = true;
          });
    }
  },
};
</script>