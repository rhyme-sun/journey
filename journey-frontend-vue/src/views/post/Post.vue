<template>
  <v-md-preview :text="post.content"></v-md-preview>
</template>

<script>
import { defineComponent, ref } from "vue";
import { useRoute } from "vue-router";
import VMdPreview from "@kangc/v-md-editor/lib/preview";
import "@kangc/v-md-editor/lib/style/preview.css";

import githubTheme from "@kangc/v-md-editor/lib/theme/github";
import "@kangc/v-md-editor/lib/theme/style/github.css";

import hljs from "highlight.js";

import postRest from "@/api/postRest";

VMdPreview.use(githubTheme, {
  Hljs: hljs,
});

export default defineComponent({
  components: { VMdPreview },
  setup() {
    const route = useRoute();
    const postId = route.params.id;

    const post = ref({});
    postRest.getById(postId).then((data) => {
      post.value = data;
    });
    return {
      post,
    };
  },
});
</script>

<style>
.v-md-editor-preview {
  text-align: left;
}
</style>
