<template>
  <v-container fill-height>
    <v-flex>
      <v-form>
        <v-card class="my-12 py-15 px-12 secondary" rounded>
          <v-card-text class="text-center">
            <p class="text-h3 text-center">Reset Your Password</p>

            <v-text-field
                class="primary"
                v-model="input.email"
                label="Enter your email"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-text-field
                class="primary"
                v-model="input.passwordNew"
                label="Enter your new password"
                type="password"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-text-field
                class="primary"
                v-model="input.passwordAgain"
                label="Enter your new password again"
                type="password"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn class="primary" large rounded v-on:click="resetPassword()">Change Your Password</v-btn>
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
      return{
        input: {
          email:"",
          passwordNew:"",
          passwordAgain:""
        }
    }
    },
    methods: {
      async resetPassword(){
        if(this.input.email != null && this.input.passwordNew != null && this.input.passwordAgain != null) {
          if(this.input.passwordNew  === this.input.passwordAgain) {

            await this.axios.put('http://localhost:9000/quirec-api/user/resetPassword',{
              email: this.input.email,
              username:"",
              password: this.input.passwordn
            })
            .then(
                this.$router.push("/login")
            )
            .catch(error =>{
              alert("Incorrect op.");
              console.log(error);
            })
          }
        }
      }
    }
  };
</script>