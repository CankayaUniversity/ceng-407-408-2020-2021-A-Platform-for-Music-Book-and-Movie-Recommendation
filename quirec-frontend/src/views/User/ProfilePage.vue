<template>
  <v-container fill-height>
    <v-flex>
      <v-card height="400px">
        <div class="text-decoration-none text-center text-h4">USER PROFILE</div>
        <v-simple-table class="mx-5 my-10 align-center justify-center">
          <template v-slot:default>
            <tbody>
            <tr>
              <td class="text-left">Username</td>
              <td class="text-left">{{ username }}</td>
            </tr>
            <tr>
              <td>E-mail</td>
              <td>{{ email }}</td>
            </tr>
            </tbody>
          </template>
        </v-simple-table>
        <v-simple-table>
          <tr>
            <td>
              <v-btn to="/questionnaire" class="mx-5 primary float-left" large rounded>Change Questionnaire</v-btn>
            </td>
            <td>
              <center>
                <v-btn to="/change-password" class="mx-5 primary" large rounded>Change Password</v-btn>
              </center>
            </td>
            <td>
              <v-btn to="/delete-account" class="mx-5 primary float-right" large rounded>Delete My Account</v-btn>
            </td>
          </tr>
        </v-simple-table>
        <br><br>
        <v-btn to="/recommendations" class="float-right mx-5 primary" large rounded>Return to recommendations</v-btn>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      username: "",
      email: ""
    };
  },
  async mounted() {
    await this.getUserInfo();
  },
  methods: {
    async getUserInfo() {
      const response = await this.axios.get('http://localhost:9000/quirec-api/user/' + this.$store.getters.getUserId)
      this.username = response.data.username;
      this.email = response.data.email;
    }
  }
};
</script>