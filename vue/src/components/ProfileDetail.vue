<template>
  <div class="profile-detail">
    <div class="profile-image-container">
      <template v-if="profile && profile.imgPath">
        <img :src="profile.imgPath" class="profile-image" />
      </template>
      <template v-else>
        <font-awesome-icon icon="user-circle" class="profile-image fa-4x" style="color: #ccc;" />
      </template>
    </div>
    <div v-if="profile">
      <h2>{{ profile.firstName }} {{ profile.lastName }}</h2>
      <p><strong>Location:</strong> {{ profile.location }}</p>
      <p><strong>Interested In:</strong> {{ profile.interestedIn }}</p>
      <p><strong>Bio:</strong> {{ profile.bio }}</p>
      <p><strong>Skills:</strong> {{ profile.skills }}</p>
      <p><strong>Criminal Record:</strong> {{ profile.criminalRecord }}</p>
      <p><strong>Accolades:</strong> {{ profile.education }}</p>
    </div>
    <div v-else>
      <p>Profile not found.</p>
    </div>
  </div>
</template>
<script>
export default {
  props: {
    profile: Object
  }
};
</script>
<style scoped>
.profile-detail {
  background: var(--form-background-color);
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.07);
  padding: 20px;
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 0;
}
.profile-image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}
.profile-image {
  width: 100px;
  height: 100px;
  border-radius: 50%;
  object-fit: cover;
  background: none;
}
</style>