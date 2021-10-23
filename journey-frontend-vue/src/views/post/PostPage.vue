<template>
  <div class="post-page">
    <h3>{{ getTitle }}</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <template v-for="item in postAbstracts" :key="item.postId">
      <PostCard
        :postId="item.postId"
        :title="item.title"
        :contentAbstract="item.contentAbstract"
        :createTime="item.createTime"
        :tagIds="item.tagIds"
      >
      </PostCard>
    </template>

    <a-pagination
      v-model:current="pageOptions.current"
      :pageSize="pageOptions.pageSize"
      :total="pageOptions.total"
      @change="handlePageChange"
    >
    </a-pagination>
  </div>
</template>
<script>
import { defineComponent, ref, reactive, computed } from "vue";
import { useRouter, useRoute, onBeforeRouteUpdate } from "vue-router";
import { useStore } from "vuex";
import PostCard from "@/components/PostCard";

import postRest from "@/api/postRest";

export default defineComponent({
  setup() {
    const title = ref("");
    const router = useRouter();
    const handleClickTitle = (postId) => {
      router.push(`/journey/posts/${postId}`);
    };

    const store = useStore();
    const route = useRoute();
    const getTitle = computed(() => {
      if (route.path.includes("categories")) {
        return store.getters["category/getCategory"](route.params.id)
          ?.categoryName;
      }
      if (route.path.includes("tags")) {
        return store.getters["tag/getTag"](route.params.id)?.tagName;
      }
      const year = route.params.year;
      const month = route.params.month;
      if (year) {
        if (month) {
          return year + "-" + month;
        } else {
          return year;
        }
      }
      return "";
    });

    // const route = useRoute();
    const pageOptions = reactive({
      current: 1,
      pageSize: 10,
      total: 0,
    });
    const postAbstracts = ref([]);
    postRest
      .pageByRoute(pageOptions.current, pageOptions.pageSize, route)
      .then((response) => {
        postAbstracts.value = response.data;
        pageOptions.total = response.total;
      });
    const handlePageChange = (page, pageSize) => {
      postRest.page(page, pageSize, route).then((response) => {
        postAbstracts.value = response.data;
        pageOptions.total = response.total;
      });
    };
    onBeforeRouteUpdate((to) => {
      postRest
        .pageByRoute(pageOptions.current, pageOptions.pageSize, to)
        .then((response) => {
          postAbstracts.value = response.data;
          pageOptions.total = response.total;
        });
    });

    return {
      title,
      getTitle,
      handleClickTitle,
      pageOptions,
      handlePageChange,
      postAbstracts,
    };
  },
  components: {
    PostCard,
  },
});
</script>

<style scoped>
.post-page {
  text-align: left;
}
.post-page > h3 {
  font-size: var(--journey-title-font-size);
}
.ant-card-head-title {
  cursor: pointer;
}
</style>
