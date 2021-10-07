import { createStore } from "vuex";
import category from "./modules/category";
import tag from "./modules/tag";
import auth from "./modules/auth";

export default createStore({
  state: {},
  mutations: {},
  actions: {},
  modules: { category, tag, auth },
});
