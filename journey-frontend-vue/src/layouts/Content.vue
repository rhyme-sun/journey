<template>
  <div class="content" :style="contentStyle">
    <router-view></router-view>
  </div>
  <div id="components-back-top-demo-custom">
    <a-back-top>
      <div class="ant-back-top-inner">UP</div>
    </a-back-top>
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
#components-back-top-demo-custom .ant-back-top {
  bottom: 100px;
}
#components-back-top-demo-custom .ant-back-top-inner {
  height: 40px;
  width: 40px;
  line-height: 40px;
  border-radius: 4px;
  background-color: #1088e9;
  color: #fff;
  text-align: center;
  font-size: 20px;
}
</style>
