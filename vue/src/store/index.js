import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import ProfileService from '../services/ProfileService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      userProfile: {},
      userLoaded: false,
      profileList: [],
      listLoaded: false
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      INITIALIZE_USER(state) {
        ProfileService.checkIfProfileExists(state.user.id).then(
          (response) => {
            if (response.data !== false) {
              ProfileService.getUserProfile().then((response) => {
                state.userProfile = response.data;
                state.userLoaded = true;
              }).catch(() => {
                
              });
            } else {
              state.userProfile = {};
              state.userLoaded = true;
            }
          }
        ).catch(() => {
        });
      },
      LOAD_PROFILES(state) {
        ProfileService.getAllProfiles()
          .then((response) => {
            state.profileList = response.data;
            state.listLoaded = true;
          })
          .catch(() => {
            state.userProfile = {};
          });
      },
      UPDATE_PROFILE(state, profile) {
        ProfileService.checkIfProfileExists(state.user.id).then(
          (response) => {
            if (response.data !== false) {
              ProfileService.updateProfile(profile).then(
                (response) => {
                  state.userProfile = response.data;
                  state.userLoaded = true;
                }).catch(() => {
                  state.userProfile = {};
                }
              );
            } else {
              ProfileService.createProfile(profile).then(
                (response) => {
                  state.userProfile = response.data;
                  state.userLoaded = true;
                }).catch(() => {
                  state.userProfile = {};
                }
              );
            }
          }
        ).catch(() => {
        });
      },
      RESET_PROFILE_LOADED(state) {
        state.userLoaded = false;
      }
    },
  });
  return store;
}
