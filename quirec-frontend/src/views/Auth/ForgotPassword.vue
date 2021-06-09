<template>
  <v-container>
    <v-flex>
      <v-layout class="d-flex justify-end pa-2">
        <v-btn to="/login" class="primary" large rounded>Login</v-btn>
        <v-btn to="/register" class="primary ml-10" large rounded>Sign Up</v-btn>
      </v-layout>

      <v-form>
        <v-card class="my-12 py-15 px-12 secondary" rounded>
          <v-card-text class="text-center">
            <p class="text-h3 text-center">Reset Password</p>

            <p class="text-center">
              Enter your e-mail address that you used to register. We will send
              you an e-mail includes a link to reset your password.
            </p>

            <v-text-field label="Email" v-model="input.email" :rules="emailRules"></v-text-field>

            <v-alert v-if="successMessage" color="pink darken-1">Password resetting mail is sent!</v-alert>
            <v-alert v-if="errorMessage" type="error">User not found!</v-alert>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn v-on:click="forgotPassword()" class="primary" large rounded>Reset Your Password</v-btn>
              </v-layout>
            </v-card-actions>
          </v-card-text>
        </v-card>
      </v-form>
    </v-flex>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      input: {
        email: "",
        username: "",
        password: ""
      },
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^(([^<>()[\]\\.,;:\s@']+(\.[^<>()\\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(v) || 'E-mail must be valid',
      ],
      successMessage: false,
      errorMessage: false
    }
  },
  methods: {
    async forgotPassword() {
      if (this.email !== "") {
        await this.axios.post('http://localhost:9000/quirec-api/user/forgotPassword', {
          email: this.input.email,
          username: "",
          password: ""
        })
            .then(response => {
              this.errorMessage = false
              this.successMessage = true
            })
            .catch(error => {
              this.successMessage = false
              this.errorMessage = true
            })
      }
    }
  }
};
</script>