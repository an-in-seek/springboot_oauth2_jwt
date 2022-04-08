import AuthService from '../services/auth.service';

const user = JSON.parse(localStorage.getItem('user'));
const initialState = user ? { status: { loggedIn: true }, user } : { status: { loggedIn: false }, user: null };

export const auth = {
  namespaced: true,
  state: initialState,
  actions: {
    logout({ commit }) {
      AuthService.logout();
      commit('logout');
    }
  },
  mutations: {
    logout(state) {
      state.status.loggedIn = false;
      state.user = null;
    }
  }
};
