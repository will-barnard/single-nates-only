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
  --primary-color: #5cc0b7;
  --secondary-color: #35495e;
  --tertiary-color: #69a6d4;
  --text-color: #2c3e50;

  --background-color: #c5eeff;
  --form-background-color: #ffffff;
  --form-border-color: #e0e0e0;
  --form-input-color: #333333;
  --form-input-border-color: #cccccc;
  --form-input-focus-color: #42b983;
  --form-button-color: #42b983;

  --link-background-color: #527aff;

  background-color: var(--background-color);
  color: var(--text-color);

  font-family: Arial, sans-serif;


}
.form-container {
  background-color: var(--form-background-color);
  border: 1px solid var(--form-border-color);
  padding: 20px;
  border-radius: 5px;
}
.generic-buttons p {
  margin: 10px 0;
  color: white;
  cursor: pointer;
  background-color: var(--secondary-color);
  padding: 10px;
  border-radius: 5px;;
}
.top-nav {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  width: 100%; /* Ensure it spans the full width */
  box-sizing: border-box; /* Include padding in width calculation */
  z-index: 1000;
  background-color: #263545;
  color: white;
  padding: 10px;
  text-align: center;
  display: flex;
  justify-content: center;
  gap: 20px;
  font-size: 1.2em;
  font-weight: bold;
}
.top-nav p {
  text-decoration: underline;
  margin: 0px;
}
#single-nates {
  padding-top: 60px; /* Adjust padding to account for fixed nav height */
}
.meaning {
    color: white;
    background-color: var(--secondary-color);
    padding: 10px;
    margin-bottom: 10px;
    border-radius: 5px;
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