<template>
  <a-sub-menu class="category">
    <template #icon>
      <FolderOpenOutlined />
    </template>
    <template #title>分类</template>
    <a-menu-item
      v-for="category in categories"
      :key="category.categoryId"
      @click="handleChangeCategory(category.categoryId)"
    >
      {{ category.categoryName }}
    </a-menu-item>
  </a-sub-menu>
</template>
<script>
import { defineComponent, computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import { FolderOpenOutlined } from "@ant-design/icons-vue";

export default defineComponent({
  setup() {
    const store = useStore();
    const categories = computed(() => {
      return store.state.category.categories;
    });

    const router = useRouter();
    const handleChangeCategory = (categoryId) => {
      router.push(`/journey/categories/${categoryId}/posts`);
    };
    return {
      categories,
      handleChangeCategory,
    };
  },
  components: {
    FolderOpenOutlined,
  },
});
</script>
<style></style>
