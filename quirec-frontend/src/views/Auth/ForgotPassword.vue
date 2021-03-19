<template>
  <v-container>
    <v-flex>
      <v-layout class="d-flex justify-end pa-2">
        <v-btn to="/login" large color="#c2185b" rounded>Login</v-btn>
        <v-btn to="/register" class="ml-10" large color="#c2185b" rounded>Sign Up</v-btn>
      </v-layout>

      <v-form>
        <v-card class="my-12 py-15 px-12" rounded color="#212121">
          <v-card-text 
            class="text-center"
          >
            <p class="text-h3 text-center">Reset Password</p>
            
            <p class="text-center">
              Enter your e-mail address that you used to register. We will send
              you an e-mail includes a link to reset your password.
            </p>

            <v-text-field label="Email" v-model="input.email" color="#c2185b">
            </v-text-field>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn large color="#c2185b" rounded>Reset Your Password</v-btn>
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
    name: "ForgotPassword",
    data(){
        return {
            input: {
                email: ""
            }
        }
    },
    methods: {

    }
};
</script>
