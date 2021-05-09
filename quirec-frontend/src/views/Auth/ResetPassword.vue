<template>
  <v-container fill-height>
    <v-flex>
      <v-form>
        <v-card class="my-12 py-15 px-12" rounded color="#212121">
          <v-card-text class="text-center">
            <p class="text-h3 text-center">Reset Your Password</p>

            <v-text-field
                color="#c2185b"
                v-model="input.email"
                label="Enter your email"

                prepend-icon="lock"
            >
            </v-text-field>

            <v-text-field
                color="#c2185b"
                v-model="input.passwordn"
                label="Enter your new password"
                type="password"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-text-field
                color="#c2185b"
                v-model="input.passworda"
                label="Enter your new password again"
                type="password"
                prepend-icon="lock"
            >
            </v-text-field>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn large color="#c2185b" rounded v-on:click="passwReset()"
                >Change Your Password</v-btn
                >
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
  name: "changepass",
  data(){
    return{
      input:{
        username:"",
        email:"",
        password:"",
        passworda:"",
        passwordn:""
      }


    }

  },
  methods: {
    async passwReset(){
      if(this.input.email != null && this.input.passworda != null && this.input.passwordn != null){
        if(this.input.passworda  === this.input.passwordn){

          await this.axios.put('http://localhost:9000/quirec-api/user/reset',{
            email: this.input.email,
            username:"",
            password: this.input.passwordn
          })
              .then(response =>{
                this.$router.push("/login")
              })
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