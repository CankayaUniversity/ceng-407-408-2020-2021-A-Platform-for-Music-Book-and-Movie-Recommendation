<template>
  <v-container fill-height>
    <v-flex>
      <v-form>
        <v-card class="my-12 py-15 px-12" rounded color="#212121">
          <v-card-text class="text-center">
            <p class="text-h3 text-center">DELETE ACCOUNT</p>
            <p class="text-h6 text-center">You are about to delete your
              QuiRec account. Are you sure?</p>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn large color="#c2185b" v-on:click="DelAcc()" rounded>Delete My Account
                </v-btn
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
  name: "deleteaccount",
  data() {
    return {
      input: {
        username: "",
        email: "",
        password: "",
      }


    }

  },
  methods: {
    async DelAcc(){
      const response = await this.axios.delete('http://localhost:9000/quirec-api/user/' + this.$store.getters.getUserId)
          .then(response => {
            this.$router.push("/login")
          })
    }
  }
};
</script>