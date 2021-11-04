import rest from "@/api/tagRest";
import { keyBy, isEmpty, groupBy } from "lodash";

const state = {
  tagMap: {},
};

const getters = {
  getTag(state) {
    return (tagId) => {
      return state.tagMap[tagId];
    };
  },
  listTags(state) {
    return (tagIds) => {
      const tags = [];
      if (tagIds) {
        tagIds.forEach((tagId) => {
          const tag = state.tagMap[tagId];
          if (tag) {
            tags.push(tag);
          }
        });
      }
      return tags;
    };
  },
  listTagGroups(state) {
    return (categoryId) => {
      const tagGroups = [];
      Object.keys(state.tagMap).forEach((key) => {
        const tag = state.tagMap[key];
        if (tag.isGroup) {
          if (!categoryId || categoryId === tag.categoryId) {
            tagGroups.push(tag);
          }
        }
      });
      return tagGroups;
    };
  },
  listTagsByGroupId(state) {
    return (groupId) => {
      const tags = [];
      tags.push(state.tagMap[groupId]);
      Object.keys(state.tagMap).forEach((key) => {
        const tag = state.tagMap[key];
        if (tag.groupId === groupId) {
          tags.push(tag);
        }
      });
      return tags;
    };
  },
  listCategoryTags(state) {
    return () => groupBy(state.tagMap, (tag) => tag.categoryId);
  },
};

const actions = {
  findAll({ commit, state }) {
    if (isEmpty(state.tagMap)) {
      rest.findAll().then((response) => {
        commit("setTags", response);
      });
    }
  },
};

const mutations = {
  setTags(state, tags) {
    state.tagMap = keyBy(tags, "tagId");
  },
};

export default {
  namespaced: true,
  state,
  getters,
  actions,
  mutations,
};
