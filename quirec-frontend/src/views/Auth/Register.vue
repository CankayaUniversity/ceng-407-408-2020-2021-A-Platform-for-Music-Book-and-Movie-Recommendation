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
            <v-card class="elevation-12 my-12 py-14 px-10" color="#212121">
              <v-card-text>
                <v-text-field label="Username" v-model="input.username" color="#c2185b"></v-text-field>

                <v-text-field label="Email" v-model="input.email" color="#c2185b"></v-text-field>

                <v-text-field label="Password" v-model="input.password" type="password" color="#c2185b"></v-text-field>

                <v-text-field label="Enter Your Password Again" v-model="input.passworda" type="password" color="#c2185b"></v-text-field>

                <center><v-btn v-on:click="register()" large color="#c2185b" rounded>Sign Up</v-btn></center>
              </v-card-text>

              <v-card-text>
                <div class="text-decoration-none text-center">Already have an account? Login to QUIREC.</div>
                <center><v-btn to="/login" large color="#c2185b" rounded>Login</v-btn></center>
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
    name: "Signup",
    data(){
        return {
            input: {
                  username: "",
                  email: "",
                  password: "",
                  passworda: ""
            }
        }
    },
    methods: {
        async register(){
            if(this.input != ""){
                if(this.input.password == this.input.passworda){
                    let currentObj = this;
                    await this.axios.post('http://localhost:9000/quirec-api/user/register', {
                        email: this.input.email,
                        username: this.input.username,
                        password: this.input.password
                    })
                    .then(
                        this.$router.push('/')
                    )
                }
            }
        }
    }
  };
</script>
