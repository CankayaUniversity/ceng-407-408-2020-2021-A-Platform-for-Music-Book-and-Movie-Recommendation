<template>
  <v-container fill-height>
    <v-row align="center" justify="center">

      <v-col sm="6">
        <v-card class="my-12 elevation-0">
          <v-img :src="require('@/assets/QuiRec.png')"> </v-img>
        </v-card>
      </v-col>

      <v-layout align-end d-flex flex-column>
        <v-flex>
          <v-form>
            <v-card 
              class="elevation-12 my-12 py-12 px-10 secondary" 
            >
              <v-card-text>         
                <v-text-field label="Username or Email" v-model="input.email" color="#c2185b" prepend-icon="person"></v-text-field>
                
                <v-text-field label="Password" v-model="input.password" type="password" color="#c2185b" prepend-icon="lock"></v-text-field>
                
                <div class="text-decoration-underline text-center"> <a href="ForgotPassword"> Forgot Password? </a></div>
                
                <center><v-btn v-on:click="login()" large color="#c2185b" rounded>Login</v-btn></center>
              </v-card-text>

              <v-divider> </v-divider>

              <v-card-text>
                <div class="text-decoration-none text-center"> Don't have an account? Sign up to QUIREC.</div>
                <center><v-btn to="/register" large class="primary" rounded>Sign Up</v-btn></center>
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
    data(){
        return {
            input: {
                  email: "",
                  password: ""
            }
        }
    },
    methods: {
        async login() {
            if(this.input.email != "" && this.input.password != null){
                this.axios.post('http://localhost:9000/quirec-api/user/login', {
                    email: this.input.email,
                    username: "",
                    password: this.input.password
                })
                .then(response => {
                  this.$store.commit('setUserId', response.data.userId)

                  this.$router.push("/profilePage")
                })
                .catch(error => {
                    console.log(error);
                })
            }
        }
    }
  };
</script>
