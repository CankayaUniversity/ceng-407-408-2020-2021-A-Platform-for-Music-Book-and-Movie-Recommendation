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
            <v-card class="elevation-12 my-12 py-12 px-10" color="#212121">
              <v-card-text>
                
                <v-text-field
                  v-model="email"
                  color="#c2185b"
                  name="login"
                  label="User Name or E-mail"
                  type="email"
        
                  prepend-icon="person"
                >
                </v-text-field>

                <v-text-field 
                  v-model="password"
                  color="#c2185b"
                  name="password" 
                  label="Password" 
                  type="password"
                  prepend-icon="lock">
                </v-text-field>
                <div class="text-decoration-underline text-center " >
                 <a href="ResetPass">
                 Forgot Password?
                 </a>
                </div>
                <v-card-actions>
                  <v-layout align-center justify-center>
                    <v-btn to="/BrowserPage" large color="#c2185b" rounded>Login</v-btn>
                  </v-layout>
                </v-card-actions>
              </v-card-text>

              <v-divider> </v-divider>

              <v-card-text>
                <div class="text-decoration-none text-center">
                  Do not have an account? Sign up to QUIREC.
                </div>
                <v-card-actions>
                  <v-layout align-center justify-center>
                    <v-btn to="/Signup" large color="#c2185b" rounded>Sign Up</v-btn>
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
  name: "Login",
  
  
};
</script>
