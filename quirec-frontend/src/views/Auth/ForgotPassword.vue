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

            <v-text-field label="Email" v-model="input.email"></v-text-field>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn @click="forgotpassw" class="primary" large rounded>Reset Your Password</v-btn>
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
    data(){
      return {
        input: {
          email: "",
          username: "",
          password:""
        }
      }
    },
    methods: {
    async forgotpassw(){
      if(this.email !== ""){
        await this.axios.post('http://localhost:9000/quirec-api/user/forgotPassword',{
          email: this.email,
          username: this.username,
          password: this.password
        })
        .then(
            this.$router.push('/login')
        )
      }

    }
    }
  };
</script>
