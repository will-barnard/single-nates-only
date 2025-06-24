<template>
    <div v-if="userLoaded && profile" class="profile-container">
        <h1>My Profile</h1>
        <div class="profile-image-container">
            <img :src="imgPath || '/img/placeholder.jpeg'" class="profile-image" />
        </div>
        <div class="profile-field">
            <strong>First Name:</strong> {{ profile.firstName }}
        </div>
        <div class="profile-field">
            <strong>Last Name:</strong> {{ profile.lastName }}
        </div>
        <div class="profile-field">
            <strong>Location:</strong> {{ profile.location }}
        </div>
        <div class="profile-field">
            <strong>Interested In:</strong> {{ profile.interestedIn }}
        </div>
        <div class="profile-field">
            <strong>Bio:</strong> {{ profile.bio }}
        </div>
        <div class="profile-field">
            <strong>Skills:</strong> {{ profile.skills }}
        </div>
        <div class="profile-field">
            <strong>Criminal Record:</strong> {{ profile.criminalRecord }}
        </div>
        <div class="profile-field">
            <strong>Accolades:</strong> {{ profile.education }}
        </div>
    </div>
    <router-link :to="{ name: 'edit-profile' }">Edit Profile</router-link>
</template>

<script>
import ImageService from '../services/ImageService';

export default {
    data() {
        return {
            profile: null,
            userLoaded: false,
            showImage: false,
            imgPath: null
        };
    },
    created() {
        const checkUserLoaded = () => {
            if (this.$store.state.userLoaded) {
                this.userLoaded = true;
                this.profile = { ...this.$store.state.userProfile };
                if (!this.profile.img && this.profile.profilePictureId) {
                    ImageService.getImage(this.profile.profilePictureId).then(
                        (response) => {
                            const base64 = ImageService.parseImg(response);
                            this.imgPath = `data:image/png;base64,${base64}`;
                            this.showImage = true;
                        }
                    );
                }
            } else {
                setTimeout(checkUserLoaded, 500);
            }
        };
        checkUserLoaded();
    }
};
</script>
<style>
.profile-container {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    box-sizing: border-box;
}

.profile-field {
    margin-bottom: 15px;
    font-size: 14px;
    line-height: 1.5;
}

strong {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

.profile-image-container {
    display: flex;
    justify-content: center;
    margin-bottom: 20px;
}

.profile-image {
    width: 150px;
    height: 150px;
    border-radius: 50%;
    object-fit: cover;
}

a {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 20px;
    font-size: 16px;
    color: #fff;
    background-color: #007bff;
    text-decoration: none;
    border-radius: 4px;
    transition: background-color 0.3s ease;
}

a:hover {
    background-color: #0056b3;
}
</style>