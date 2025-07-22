<template>
  <div class="profile-view">
    <LoadingWidget v-if="loading" />
    <template v-else>
      <ProfileDetail v-if="profileWithImg && Object.keys(profileWithImg).length" :profile="profileWithImg" />
      <div v-else class="profile-not-found">
        <h2>Profile not found</h2>
        <p>The profile you are looking for does not exist.</p>
      </div>
    </template>
  </div>
</template>

<script>
import ProfileDetail from '../components/ProfileDetail.vue';
import LoadingWidget from '../components/LoadingWidget.vue';
import { useRoute } from 'vue-router';
import { computed, onMounted, ref, watch } from 'vue';
import { useStore } from 'vuex';
import ImageService from '../services/ImageService';

export default {
  components: { ProfileDetail, LoadingWidget },
  setup() {
    const route = useRoute();
    const store = useStore();
    const profileId = computed(() => route.params.profileId);
    const profileWithImg = ref(null);
    const loading = ref(true);

    const setProfileWithImg = (p) => {
      if (p && p.profilePictureId) {
        const cached = store.state.imageCache[p.profilePictureId];
        if (cached) {
          profileWithImg.value = { ...p, imgPath: `data:image/png;base64,${cached}` };
        } else {
          ImageService.getImage(p.profilePictureId).then((response) => {
            const base64 = ImageService.parseImg(response);
            store.commit('CACHE_IMAGE', { id: p.profilePictureId, data: base64 });
            profileWithImg.value = { ...p, imgPath: `data:image/png;base64,${base64}` };
          });
        }
      } else if (p) {
        profileWithImg.value = { ...p };
      } else {
        profileWithImg.value = {};
      }
    };

    const trySetProfile = () => {
      const p = store.state.profileList.find(
        p => String(p.profileId) === String(profileId.value)
      );
      setProfileWithImg(p);
      loading.value = false;
    };

    onMounted(() => {
      if (!store.state.listLoaded) {
        loading.value = true;
        const unwatch = watch(
          () => store.state.listLoaded,
          (loaded) => {
            if (loaded) {
              trySetProfile();
              unwatch();
            }
          }
        );
      } else {
        trySetProfile();
      }
    });

    return { profileWithImg, loading };
  }
};
</script>

<style scoped>
.profile-view {
  max-width: 700px;
  margin: 0 auto;
  padding: 20px;
}
.profile-not-found {
  text-align: center;
  color: #888;
  margin-top: 40px;
}
</style>