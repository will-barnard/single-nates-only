<template>
  <div id="single-nates">
    <div id="nav" class="top-nav">
      <p @click="$router.push({name: 'home'})">Home</p>
      <p v-show="$store.state.token === ''" @click="$router.push({name: 'login'})">Login</p>
      <p v-show="$store.state.token === ''" @click="$router.push({name: 'register'})">Register</p>
      <p v-show="$store.state.token !== ''" @click="$router.push({name: 'my-profile'})">My Profile</p>
      <p v-show="$store.state.token !== ''" @click="$router.push({name: 'logout'})">Logout</p>

    </div>
    <TopBanner />

    <router-view />
  </div>
</template>

<script>
import TopBanner from "./components/TopBanner.vue";

export default {
  components: {
    TopBanner
  },
  created() {
    if (this.$store.state.token) {
      this.$store.commit('INITIALIZE_USER');
    }
    this.$store.commit('LOAD_PROFILES');
  }

};
</script>

<style>
:root {
  --primary-color: #ff6f61; /* Warm coral red for primary */
  --secondary-color: #ffe5e0; /* Light coral for backgrounds */
  --tertiary-color: #ffb3ab; /* Soft coral accent */
  --text-color: #2c3e50;

  --background-color: #ffe5e0;
  --form-background-color: #ffffff;
  --form-border-color: #ffb3ab;
  --form-input-color: #333333;
  --form-input-border-color: #ffb3ab;
  --form-input-focus-color: #ff6f61;
  --form-button-color: #ff6f61;

  --link-background-color: #ff6f61;

  background-color: var(--background-color);
  color: var(--text-color);

  font-family: 'Quicksand', 'Nunito', 'Montserrat', 'Segoe UI', 'Arial', sans-serif;
}
.form-container {
  background-color: var(--form-background-color);
  border: 1px solid var(--form-border-color);
  padding: 20px;
  border-radius: 8px;
}
.generic-buttons p {
  margin: 10px 0;
  color: white;
  cursor: pointer;
  background-color: var(--primary-color);
  padding: 10px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(126,200,227,0.08);
}
.top-nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  width: 100%;
  box-sizing: border-box;
  z-index: 1000;
  background-color: var(--primary-color);
  color: white;
  padding: 12px;
  text-align: center;
  display: flex;
  justify-content: center;
  gap: 24px;
  font-size: 1.2em;
  font-weight: bold;
  box-shadow: 0 2px 8px rgba(126,200,227,0.10);
}
.top-nav p {
  text-decoration: underline;
  margin: 0px;
}
#single-nates {
  padding-top: 70px;
  background: linear-gradient(135deg, #ffe5e0 0%, #ffb3ab 100%);
}
.meaning {
    color: white;
    background-color: var(--primary-color);
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 8px;
}
.spacer {
  flex-grow: 1;
}
.single-row {
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: space-between;
}
</style>