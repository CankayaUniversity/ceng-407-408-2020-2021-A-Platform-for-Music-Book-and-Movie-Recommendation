<template>
  <v-container fill-height>
    <v-flex>
      <v-form>
        <v-card class="my-12 py-15 px-12" rounded color="#212121">
          <v-card-text class="text-center">
            <p class="text-h3 text-center">Change Your Password</p>

            <v-text-field
              color="#c2185b"
              name="password"
              label="Enter your password"
              type="text"
            >
            </v-text-field>

            <v-text-field
              color="#c2185b"
              name="passwordn"
              label="Enter your new password"
              type="text"
            >
            </v-text-field>

            <v-text-field
              color="#c2185b"
              name="passworda"
              label="Enter your new password again"
              type="text"
            >
            </v-text-field>

            <v-card-actions class="justify-center pt-10">
              <v-layout align-center justify-center>
                <v-btn large color="#c2185b" rounded
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
};
</script>