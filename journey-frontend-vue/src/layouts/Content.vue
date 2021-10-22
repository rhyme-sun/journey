<template>
  <div class="content" :style="contentStyle">
    <router-view></router-view>
  </div>
</template>

<script>
import { defineComponent, computed } from "vue";
import { useRoute } from "vue-router";

export default defineComponent({
  setup() {
    const route = useRoute();
    const contentStyle = computed(() => {
      if (route.path.includes("management")) {
        return {
          "min-height": "1200px",
          padding: "30px 60px",
          margin: "20px 10%",
        };
      } else {
        return {
          "min-height": "1200px",
          padding: "30px 60px",
          margin: "20px 21.5%",
        };
      }
    });
    return {
      contentStyle,
    };
  },
});
</script>

<style>
.content {
  background: #fff;
}
</style>
