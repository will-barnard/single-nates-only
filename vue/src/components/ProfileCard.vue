<template>
    <div>
        <div class="profile-image-container">
            <img :src="imgPath || '/img/placeholder.jpeg'" class="profile-image" />
        </div>
        <h2>{{ profile.firstName }} {{ profile.lastName }}</h2>
        <p><strong>Location:</strong> {{ profile.location }}</p>
        <p><strong>Interested In:</strong> {{ profile.interestedIn }}</p>
        <p><strong>Bio:</strong> {{ profile.bio }}</p>
        <p><strong>Skills:</strong> {{ profile.skills }}</p>
        <p><strong>Criminal Record:</strong> {{ profile.criminalRecord }}</p>
        <p><strong>Accolades:</strong> {{ profile.education }}</p>
    </div>
</template>
<script>
import ImageService from '../services/ImageService';

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
            ImageService.getImage(this.profile.profilePictureId).then((response) => {
                const base64 = ImageService.parseImg(response);
                this.imgPath = `data:image/png;base64,${base64}`;
                this.showImage = true;
            });
        }
    }
};
</script>
<style>
.profile-image-container {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.profile-image {
    width: 100px;
    height: 100px;
    border-radius: 50%;
    object-fit: cover;
}
</style>