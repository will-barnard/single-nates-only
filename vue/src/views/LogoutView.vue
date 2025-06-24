<template>
  <h1>Logout</h1>
  <div class="generic-buttons">
    <p @click="logout">Click here to log out</p>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      this.$router.push("/login");
    }
  }
};
</script>
