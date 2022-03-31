import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import Vuestagram from './views/Vuestagram.vue';
import Login from './views/Login.vue';
import Register from './views/Register.vue';
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
      path: '/vuestagram',
      component: Vuestagram
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
      path: '/register',
      component: Register
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      // lazy-loaded
      component: () => import('./views/BoardAdmin.vue')
    },
    {
      path: '/admin/board/detail',
      name: 'adminBoardDetailNewRegister',
      // lazy-loaded
      component: () => import('./views/BoardAdminDetail.vue')
    },
    {
      path: '/admin/board/detail/:boardNo',
      name: 'adminBoardDetail',
      // lazy-loaded
      component: () => import('./views/BoardAdminDetail.vue')
    },
    {
      path: '/mod',
      name: 'moderator',
      // lazy-loaded
      component: () => import('./views/BoardModerator.vue')
    },
    {
      path: '/user',
      name: 'user',
      // lazy-loaded
      component: () => import('./views/BoardUser.vue')
    },
    {
      path: '/user/board/detail',
      name: 'userBoardDetailNewRegister',
      // lazy-loaded
      component: () => import('./views/BoardUserDetail.vue')
    },
    {
      path: '/user/board/detail/:boardNo',
      name: 'userBoardDetail',
      // lazy-loaded
      component: () => import('./views/BoardUserDetail.vue')
    },
    {
      path: '/post/detail',
      name: 'postDetailNewRegister',
      // lazy-loaded
      component: () => import('./views/PostDetail.vue')
    }
  ]
});

// router.beforeEach((to, from, next) => {
//   const publicPages = ['/login', '/register', '/home'];
//   const authRequired = !publicPages.includes(to.path);
//   const loggedIn = localStorage.getItem('user');

//   // trying to access a restricted page + not logged in
//   // redirect to login page
//   if (authRequired && !loggedIn) {
//     next('/login');
//   } else {
//     next();
//   }
// });
