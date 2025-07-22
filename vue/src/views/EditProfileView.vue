<template>
    <div>
        <div v-if="userLoaded">
            <h2>Edit Profile</h2>
            <form @submit.prevent="updateProfile">
                <div>
                    <label for="profilePicture">Profile Picture:</label>
                    <!-- Display the current profile image or the uploaded preview, fallback to placeholder -->
                    <div class="profile-image-container">
                        <template v-if="profilePicturePreview || imgPath">
                            <img :src="profilePicturePreview || imgPath" 
                                 alt="Profile Picture Preview" 
                                 style="max-width: 100%; max-height: 200px; margin-bottom: 10px;" />
                        </template>
                        <template v-else>
                            <i class="fa fa-user-circle fa-6x" style="color: #ccc; margin-bottom: 10px;"></i>
                        </template>
                    </div>
                    <input type="file" id="profilePicture" @change="handleFileUpload" />
                </div>
                <div>
                    <label for="firstName">First Name:</label>
                    <input type="text" id="firstName" v-model="profile.firstName" required />
                </div>
                <div>
                    <label for="lastName">Last Name:</label>
                    <input type="text" id="lastName" v-model="profile.lastName" required />
                </div>
                <div>
                    <label for="location">Location:</label>
                    <input type="text" id="location" v-model="profile.location" />
                </div>
                <!-- <div>
                    <label for="genderIdentity">Gender Identity:</label>
                    <input type="text" id="genderIdentity" v-model="profile.genderIdentity" />
                </div> -->
                <div>
                    <label for="interestedIn">Interested In:</label>
                    <input type="text" id="interestedIn" v-model="profile.interestedIn" />
                </div>
                <div>
                    <label for="bio">Bio:</label>
                    <textarea id="bio" v-model="profile.bio"></textarea>
                </div>
                <div>
                    <label for="skills">Skills:</label>
                    <input type="text" id="skills" v-model="profile.skills" />
                </div>
                <div>
                    <label for="criminalRecord">Criminal Record:</label>
                    <input type="text" id="criminalRecord" v-model="profile.criminalRecord" />
                </div>
                <div>
                    <label for="education">Accolades:</label>
                    <input type="text" id="education" v-model="profile.education" />
                </div>
                <button type="submit">Save Changes</button>
            </form>
        </div>
    </div>
</template>

<script>
import ImageService from '../services/ImageService';

export default {
    data() {
        return {
            profile: null,
            userLoaded: false,
            profilePicture: null,
            profilePicturePreview: null, // Added to store the preview URL
            imgPath: null // Added to store the loaded image path
        };
    },
    created() {
        const checkUserLoaded = () => {
            if (this.$store.state.userLoaded) {
                this.userLoaded = true;
                this.profile = { ...this.$store.state.userProfile };
                if (!this.profile.img && this.profile.profilePictureId) {
                    const cached = this.$store.state.imageCache[this.profile.profilePictureId];
                    if (cached) {
                        this.imgPath = `data:image/png;base64,${cached}`;
                    } else {
                        ImageService.getImage(this.profile.profilePictureId).then(
                            (response) => {
                                const base64 = ImageService.parseImg(response);
                                this.imgPath = `data:image/png;base64,${base64}`;
                                this.$store.commit('CACHE_IMAGE', { id: this.profile.profilePictureId, data: base64 });
                            }
                        );
                    }
                }
            } else {
                setTimeout(checkUserLoaded, 500);
            }
        };
        checkUserLoaded();
    },
    methods: {
        handleFileUpload(event) {
            const file = event.target.files[0];
            if (file) {
                this.profilePicture = file;
                this.profilePicturePreview = URL.createObjectURL(file); // Generate preview URL
                ImageService.createImage(file).then((response) => {
                    let id = response.data;
                    this.profile.profilePictureId = id;
                    this.profilePicturePreview = null; // Clear preview URL to avoid caching issues
                }).catch((error) => {
                    console.error("Error uploading image:", error);
                });
            }
        },
        updateProfile() {
            this.$store.commit('RESET_PROFILE_LOADED');
            this.$store.commit('UPDATE_PROFILE', this.profile);
            this.profilePicturePreview = null; // Clear preview URL after saving changes
            this.$router.push({ name: 'my-profile' });
        }
    }
};
</script>
<style>
form {
    max-width: 600px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 8px;
    background-color: #f9f9f9;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    box-sizing: border-box; /* Ensure padding and border are included in width */
}

form div {
    margin-bottom: 15px;
}

form label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

form input[type="text"],
form textarea {
    width: 100%;
    padding: 8px; /* Reduced padding */
    border: 1px solid #ccc;
    border-radius: 4px;
    font-size: 13px; /* Reduced font size */
    box-sizing: border-box; /* Prevent overflow due to padding */
}

form textarea {
    resize: vertical;
    min-height: 60px; /* Reduced minimum height */
}

form button {
    display: inline-block;
    padding: 10px 20px;
    font-size: 16px;
    color: #fff;
    background-color: #007bff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

form button:hover {
    background-color: #0056b3;
}

.profile-image-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-bottom: 10px;
}

.fa-user-circle {
    color: #ccc;
}
</style>

<!--     private int profileId;
    private int userId;
    private Integer profilePictureId;
    private String firstName;
    private String lastName;
    private String genderIdentity;
    private String interestedIn;
    private String location;
    private String bio;
    private String skills;
    private String criminalRecord;
    private String education; -->