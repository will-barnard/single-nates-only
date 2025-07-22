<template>
  <div class="home" v-if="listLoaded">
    <div class="home-content">
      <div class="profile-grid">
        <ProfileCard
          v-for="profile in $store.state.profileList"
          :key="profile.profileId"
          :profile="profile"
          @click="$router.push({ name: 'profile-view', params: { profileId: profile.profileId } })"
        />
      </div>
    </div>
  </div>
</template>

<script>
import ProfileCard from '../components/ProfileCard.vue';

export default {
  components: {
    ProfileCard
  },
  data() {
    return {
      listLoaded: false
    };
  },
  created() {
    const checkListLoaded = () => {
      if (this.$store.state.listLoaded) {
        this.listLoaded = true;
      } else {
        setTimeout(checkListLoaded, 500);
      }
    };
    checkListLoaded();
  },
  methods: {}
};
</script>

<style scoped>
.home-content {
  text-align: center;
  margin-top: 20px;
  padding: 10px;
  background-color: var(--form-background-color);
  border: 1px solid var(--form-border-color);
  border-radius: 5px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
.profile-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 16px;
  justify-items: center;
}
@media (max-width: 600px) {
  .profile-grid {
    grid-template-columns: repeat(2, 1fr);
    gap: 10px;
  }
}
</style>