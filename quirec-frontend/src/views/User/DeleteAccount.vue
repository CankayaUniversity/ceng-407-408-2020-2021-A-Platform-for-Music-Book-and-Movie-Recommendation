<template>
  <v-container fill-height>
    <v-flex>
      <v-form>
        <v-card className="my-12 py-15 px-12 secondary" rounded>
          <v-card-text className="text-center">
            <p className="text-h3 text-center">DELETE ACCOUNT</p>
            <p className="text-h6 text-center">You are about to delete your
              QuiRec account. Are you sure?</p>

            <v-card-actions className="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn className="primary" large v-on:click="deleteAccount()" rounded>Delete My Account</v-btn>
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
  },
  methods: {
    async deleteAccount() {
      await this.axios.delete('http://localhost:9000/quirec-api/user/' + this.$store.getters.getUserId)
          .then(response => {
            this.$router.push("/login")
          })
    }
  }
};
</script>