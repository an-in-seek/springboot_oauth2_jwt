import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Login from './views/Login.vue';
import OAuth2RedirectHandler from './views/OAuth2RedirectHandler.vue';

Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/oauth2/redirect',
      component: OAuth2RedirectHandler
    },
    {
      path: '/profile',
      name: 'get user profile info',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/boards',
      name: 'get board list',
      // lazy-loaded
      component: () => import('./views/BoardList.vue')
    },
    {
      path: '/board',
      name: 'get board info',
      // lazy-loaded
      component: () => import('./views/Board.vue')
    },
    {
      path: '/board/:id',
      name: 'get board info by id',
      // lazy-loaded
      component: () => import('./views/Board.vue')
    }
  ]
});