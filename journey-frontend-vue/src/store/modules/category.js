import rest from "@/api/categoryRest";

const state = {
  categories: [],
};

const getters = {
  getCategory: (state) => {
    return (categoryId) => {
      return state.categories.find(
        (category) => category.categoryId === categoryId
      );
    };
  },
};

const actions = {
  findAll({ commit, state }) {
    if (state.categories.length === 0) {
      rest.findAll().then((response) => {
        commit("setCategories", response);
      });
    }
  },
};

const mutations = {
  setCategories(state, categories) {
    state.categories = categories;
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
