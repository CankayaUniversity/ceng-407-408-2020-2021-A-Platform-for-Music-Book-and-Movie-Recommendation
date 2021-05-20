<template>
  <v-container fill-height>
    <v-row align="center" justify="center">
      <v-col sm="6">
        <v-card class="my-12 elevation-0 ">
          <v-img :src="require('@/assets/QuiRec.png')"> </v-img>
        </v-card>
      </v-col>

      <v-layout align-end d-flex flex-column>
        <v-flex>
          <v-form v-model="valid" ref="form" lazy-validation>
            <v-card class="elevation-12 my-12 py-14 px-10 secondary" width="500px">
              <v-card-text class="text--primary">
                <v-text-field label="Username" v-model="username" :rules="nameRules" prepend-icon="person"></v-text-field>

                <v-text-field label="Email" v-model="email" :rules="emailRules" prepend-icon="mail"></v-text-field>

                <v-text-field label="Password" v-model="password" :rules="passwordRules" type="password" prepend-icon="lock"></v-text-field>

                <v-text-field label="Enter Your Password Again" v-model="passwordAgain" :rules="passwordRules" type="password" prepend-icon="lock"></v-text-field>

                <v-alert v-if="successMessage" type="success">Successfully registered! <br> Go to login page to start using QuiRec</v-alert>
                <v-alert v-if="errorMessage" type="error">Existing email/username on the system! Please try different email/username</v-alert>

                <center><v-btn @click="register" :disabled="!valid" class="primary" large rounded>Sign Up</v-btn></center>
              </v-card-text>

              <v-card-text>
                <div class="text-decoration-none text-center">Already have an account? Login to QUIREC.</div>
                <center><v-btn to="/login" class="primary" large rounded>Login</v-btn></center>
              </v-card-text>
            </v-card>
          </v-form>
        </v-flex>
      </v-layout>
    </v-row>
  </v-container>
</template>

<script>
export default {
  data() {
    return {
      valid: true,
      username: "",
      nameRules: [
        v => !!v || 'Name is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters',
      ],
      email: "",
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^(([^<>()[\]\\.,;:\s@']+(\.[^<>()\\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(v) || 'E-mail must be valid',
      ],
      password: "",
      passwordAgain: "",
      passwordRules: [
        v => !!v || 'Password is required',
        v => /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/.test(v) || 'Password must contain a lowercase letter, one number, a special character and one uppercase letter',
      ],
      successMessage: false,
      errorMessage: false
    }
  },
  methods: {
    async register() {
      if (this.$refs.form.validate()) {
        if (this.password === this.passwordAgain) {
          await this.axios.post('http://localhost:9000/quirec-api/user/register', {
            email: this.email,
            username: this.username,
            password: this.password
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
  }
};
</script>