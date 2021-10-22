<template>
  <div style="text-align: left" class="tag">
    <h3>全部标签</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <template v-for="item in categoryTags" :key="item[0].categoryId">
      <a-divider orientation="right">
        {{ getCategory(item[0].categoryId) }}
      </a-divider>
      <div class="tags-layout">
        <a-tag
          v-for="tag in item"
          :key="tag.tagId"
          :color="getTagColor(tag)"
          @click="handleClickTag(tag.tagId)"
        >
          <a>
            {{ tag.tagName }}
          </a>
        </a-tag>
      </div>
    </template>
  </div>
</template>

<script>
import { defineComponent, ref } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const categoryTags = ref({});
    const store = useStore();
    categoryTags.value = store.getters["tag/listCategoryTags"]();

    const getCategory = (categoryId) => {
      const category = store.getters["category/getCategory"](categoryId);
      return category.categoryName + "：" + category.description;
    };

    const getTagColor = (tag) => {
      if (tag.isGroup) {
        return "#1890ff";
      } else {
        return "#ffa940";
      }
    };

    const router = useRouter();
    const handleClickTag = (tagId) => {
      router.push(`/journey/tags/${tagId}/posts`);
    };
    return {
      categoryTags,
      getCategory,
      getTagColor,
      handleClickTag,
    };
  },
});
</script>

<style scoped>
.tag > h3 {
  font-size: var(--journey-title-font-size);
}
.tags-layout {
  padding: 10px;
}
.tags-layout > span {
  padding: 0.25rem 0.5rem;
  font-size: 15px;
  border-radius: 0.2rem;
}
</style>
