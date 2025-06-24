import { createApp } from 'vue'
import SingleNates from './App.vue'
import { createStore } from './store'
import router from './router'
import axios from 'axios'
import { library } from '@fortawesome/fontawesome-svg-core'
import { faCoffee } from '@fortawesome/free-solid-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

axios.defaults.baseURL = import.meta.env.VITE_REMOTE_API;

let currentToken = localStorage.getItem('token');
let currentUser = JSON.parse(localStorage.getItem('user'));

if (currentToken) {
  // Set token axios requests
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

// Create the Vuex store passing in the stored credentials
const store = createStore(currentToken, currentUser);

// Add icons to the library
library.add(faCoffee);

const app = createApp(SingleNates);
// Register Font Awesome globally
app.component('font-awesome-icon', FontAwesomeIcon);
app.use(store);
app.use(router);
app.mount('#app');