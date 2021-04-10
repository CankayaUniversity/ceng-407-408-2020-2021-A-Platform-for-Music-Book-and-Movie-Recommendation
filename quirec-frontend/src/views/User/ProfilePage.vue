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
              <v-card-actions class="align-left justify-left mx-5">
                <v-btn to="/BrowserPage" large color="#c2185b" rounded>Go Back To Main Page</v-btn>
              </v-card-actions>
            </td>

            <td>
              <v-card-actions class="align-end justify-end mx-10">
                <v-btn to="/Questionnaire" large color="#c2185b" rounded>Change Questionnaire</v-btn>
              </v-card-actions>
            </td>
          </tr>

          <tr>
            <td>
              <v-card-actions class="align-left justify-left mx-5">
                <v-btn to="/DeleteAccount" large color="#c2185b" rounded>Delete My Account</v-btn>
              </v-card-actions>
            </td>
            <td>
              <v-card-actions class="align-end justify-end mx-10">
                <v-btn to="/ChangePass" large color="#c2185b" rounded>Change Password</v-btn>
              </v-card-actions>
            </td>
          </tr>
        </v-simple-table>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "profilepage",
  data() {
    return {
      username: "",
      email: ""
    };
  },
  async mounted() {
    this.getUserInfo();
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