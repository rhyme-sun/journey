<template>
  <div class="post-page">
    <h3>{{ title }}</h3>
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
import { defineComponent, ref, reactive } from "vue";
import { useRouter } from "vue-router";
import PostCard from "@/components/PostCard";
import postRest from "@/api/postRest";

export default defineComponent({
  setup() {
    const title = ref("全部文章");
    const router = useRouter();
    const handleClickTitle = (postId) => {
      router.push(`/journey/posts/${postId}`);
    };

    const pageOptions = reactive({
      current: 1,
      pageSize: 10,
      total: 0,
    });
    const postAbstracts = ref([]);

    postRest
      .page(pageOptions.current, pageOptions.pageSize, {})
      .then((response) => {
        postAbstracts.value = response.data;
        pageOptions.total = response.total;
      });

    const handlePageChange = (page, pageSize) => {
      postRest.page(page, pageSize, {}).then((response) => {
        postAbstracts.value = response.data;
        pageOptions.total = response.total;
      });
    };

    return {
      title,
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
