import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';


const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/about",
    name: "about",
    component: () => import('../views/AboutView.vue'),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/profile",
    name: "edit-profile",
    component: () => import('../views/EditProfileView.vue'),
    meta: {
      requiresAuth: true 
    }
  },
  {
    path: "/profile/:profileId",
    name: "profile-view",
    component: () => import('../views/ProfileDetailView.vue'),
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/profiles/my-profile",
    name: "my-profile",
    component: () => import('../views/MyProfileView.vue'),
    meta: {
      requiresAuth: true 
    }
  }

];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
