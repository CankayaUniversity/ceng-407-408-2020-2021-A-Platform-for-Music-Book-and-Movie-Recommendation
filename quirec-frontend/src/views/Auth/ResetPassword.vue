<template>
  <v-container fill-height>
    <v-flex>
      <v-form v-model="valid" ref="form" lazy-validation>
        <v-card class="my-12 py-15 px-12 secondary" rounded>
          <v-card-text class="text-center">
            <p class="text-h3 text-center">Reset Your Password</p>

            <v-text-field
                class="primary"
                v-model="input.email"
                :rules="emailRules"
                label="Enter your email"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-text-field
                class="primary"
                v-model="input.passwordNew"
                :rules="passwordRules"
                label="Enter your new password"
                type="password"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-text-field
                class="primary"
                v-model="input.passwordAgain"
                :rules="passwordRules"
                label="Enter your new password again"
                type="password"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn class="primary" large rounded v-on:click="resetPassword()">Reset Your Password</v-btn>
              </v-layout>
            </v-card-actions>

            <v-alert v-if="successMessage" type="success">Password is successfully reset! <br> Go to login page to start using QuiRec</v-alert>
            <v-alert v-if="errorMessage" type="error">User not found!</v-alert>

          </v-card-text>
        </v-card>
      </v-form>
    </v-flex>
  </v-container>
</template>

<script>
  export default {
    data(){
      return{
        valid: true,
        input: {
          email:"",
          passwordNew:"",
          passwordAgain:""
        },
        emailRules: [
          v => !!v || 'E-mail is required',
          v => /^(([^<>()[\]\\.,;:\s@']+(\.[^<>()\\[\]\\.,;:\s@']+)*)|('.+'))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(v) || 'E-mail must be valid',
        ],
        passwordRules: [
          v => !!v || 'Password is required',
          v => /(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,}/.test(v) || 'Password must contain a lowercase letter, one number, a special character and one uppercase letter',
        ],
        successMessage: false,
        errorMessage: false
      }
    },
    methods: {
      async resetPassword(){
        if(this.$refs.form.validate()) {
          if(this.input.passwordNew  === this.input.passwordAgain) {
            await this.axios.put('http://localhost:9000/quirec-api/user/resetPassword',{
              email: this.input.email,
              username:"",
              password: this.input.passwordNew
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