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
                <v-text-field
                  color="#c2185b"
                  name="username"
                  label="Username"
                  type="text"
                >
                </v-text-field>

                <v-text-field
                  color="#c2185b"
                  name="email"
                  label="E-mail"
                  type="text"
                >
                </v-text-field>

                <v-text-field
                  color="#c2185b"
                  name="password"
                  label="Password"
                  type="text"
                >
                </v-text-field>

                <v-text-field
                  color="#c2185b"
                  name="passworda"
                  label="Enter Your Password Again"
                  type="password"
                >
                </v-text-field>

                <v-card-actions>
                  <v-layout align-center justify-center>
                    <v-btn large color="#c2185b" rounded>Sign Up</v-btn>
                  </v-layout>
                </v-card-actions>
              </v-card-text>
              <v-card-text>
                <div class="text-decoration-none text-center">
                  Do you have an account? Login to QUIREC.
                </div>
                <v-card-actions>
                  <v-layout align-center justify-center>
                    <v-btn to="/Login" large color="#c2185b" rounded>Login</v-btn>
                  </v-layout>
                </v-card-actions>
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
};
</script>
