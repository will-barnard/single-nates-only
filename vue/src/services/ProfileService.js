import axios from 'axios';

export default {

    getProfileByUserId(userId) {
        return axios.get('/profiles/' + userId);
    },
    checkIfProfileExists(userId) {
        return axios.get('/profiles/checkIfProfile');
    },
    getUserProfile() {
        return axios.get('/profiles/user');
    },
    getAllProfiles() {
        return axios.get('/profiles');
    },
    createProfile(profile) {
        return axios.post('/profiles', profile);
    },
    updateProfile(profile) {
        return axios.put('/profiles', profile);
    },
    deleteProfile(userId) {
        return axios.delete('/profiles/' + userId);
    }

}