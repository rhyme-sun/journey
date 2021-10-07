<template>
  <div class="post-table-controller">
    <PostEdit />
    <PostUploader />
    <a-button type="primary">导出</a-button>
  </div>
</template>

<script>
import { defineComponent, ref } from "vue";
import PostEdit from "./PostEdit.vue";
import PostUploader from "./PostUploader.vue";
export default defineComponent({
  setup() {
    const postEditorVisible = ref(false);
    const createPost = () => {
      postEditorVisible.value = true;
    };

    return {
      postEditorVisible,
      createPost,
    };
  },
  components: { PostEdit, PostUploader },
});
</script>

<style>
.post-table-controller {
  text-align: left;
  margin: 15px 0px;
}
.post-table-controller > button {
  margin-right: 5px;
}
</style>
