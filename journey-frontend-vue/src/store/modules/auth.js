import rest from "@/api/authRest";

const state = {
  authorities: ["guest"],
};

const getters = {};

const actions = {
  login({ commit }, { username, password }) {
    rest.login(username, password).then((response) => {
      commit("setAuthority", response);
    });
  },

  logout({ commit }) {
    rest.logout().then((response) => {
      commit("clearAuthority", response);
    });
  },
};

const mutations = {
  resetAuthority(state, authorities) {
    state.authorities = authorities;
  },
  setAuthority(state, response) {
    response.authorities.forEach((element) =>
      state.authorities.unshift(element.authority)
    );
  },
  clearAuthority(state, response) {
    response.authorities.forEach((element) => {
      const index = state.authorities.indexOf(element.authority);
      if (index >= 0) {
        state.authorities.splice(index, 1);
      }
    });
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
