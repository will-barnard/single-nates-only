<template>
  <div class="profile-card-tile">
    <div class="profile-image-container">
      <template v-if="imgPath">
        <img :src="imgPath" class="profile-image" />
      </template>
      <template v-else>
        <font-awesome-icon icon="user-circle" class="profile-image fa-4x" style="color: #ccc;" />
      </template>
    </div>
    <div class="profile-details">
      <div class="profile-name">{{ profile.firstName }} {{ profile.lastName }}</div>
      <div class="profile-info"><span>📍</span>{{ profile.location }}</div>
      <div class="profile-info"><span>💡</span>{{ profile.interestedIn }}</div>
      <div class="profile-info"><span>📝</span>{{ profile.bio }}</div>
      <div class="profile-info"><span>🔧</span>{{ profile.skills }}</div>
      <div class="profile-info"><span>⚖️</span>{{ profile.criminalRecord }}</div>
      <div class="profile-info"><span>🎓</span>{{ profile.education }}</div>
    </div>
  </div>
</template>

<script>
import ImageService from '../services/ImageService';
// Import FontAwesome icon
import { faUserCircle } from '@fortawesome/free-solid-svg-icons';
import { library } from '@fortawesome/fontawesome-svg-core';
library.add(faUserCircle);

export default {
    props: ['profile'],
    data() {
        return {
            imgPath: null,
            showImage: false
        };
    },
    created() {
        if (this.profile.profilePictureId && !this.profile.img) {
            const cached = this.$store.state.imageCache[this.profile.profilePictureId];
            if (cached) {
                this.imgPath = `data:image/png;base64,${cached}`;
                this.showImage = true;
            } else {
                ImageService.getImage(this.profile.profilePictureId).then((response) => {
                    const base64 = ImageService.parseImg(response);
                    this.imgPath = `data:image/png;base64,${base64}`;
                    this.showImage = true;
                    this.$store.commit('CACHE_IMAGE', { id: this.profile.profilePictureId, data: base64 });
                });
            }
        }
    }
};
</script>

<style>
.profile-card-tile {
  background: #f7f8fa;
  border-radius: 10px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.07);
  padding: 12px 10px;
  margin: 0;
  display: flex;
  flex-direction: column;
  align-items: center;
  min-width: 0;
  border: 1px solid #e0e3e8;
}
.profile-image-container {
  display: flex;
  justify-content: center;
  margin-bottom: 10px;
}
.profile-image {
  width: 70px;
  height: 70px;
  border-radius: 50%;
  object-fit: cover;
  background: none;
}
.fa-user-circle.profile-image {
  color: #ccc;
  width: 70px;
  height: 70px;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
}
.profile-details {
  width: 100%;
  padding: 0;
}
.profile-name {
  font-size: 1.1em;
  font-weight: 600;
  margin-bottom: 4px;
  text-align: center;
}
.profile-info {
  font-size: 0.95em;
  margin-bottom: 2px;
  line-height: 1.3;
  display: flex;
  align-items: center;
  gap: 4px;
}
</style>