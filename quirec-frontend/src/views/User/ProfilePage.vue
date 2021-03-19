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
                <td class="text-left">yarenipek</td>
              </tr>
              <tr>
                <td>E-mail</td>
                <td>yarenipek1337@gmail.com</td>
              </tr>
            </tbody>
          </template>
        </v-simple-table>
        <v-card-actions class="align-end justify-end mx-10">
          <v-btn to="/Questionnaire" large color="#c2185b" rounded>Change Questionnaire</v-btn>
        </v-card-actions>
        <v-card-actions class="align-end justify-end mx-10">
          <v-btn to="/ChangePass" large color="#c2185b" rounded>Change Password</v-btn>
        </v-card-actions>
        <v-card-actions class="align-left justify-left mx-5">
          <v-btn large color="#c2185b" rounded>Go Back To Main Page</v-btn>
        </v-card-actions>
      </v-card>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  name: "profilepage",
  data() {
    return {};
  },
};
</script>