<template>
  <a-result
    status="403"
    title="403"
    sub-title="Sorry, you are not authorized to access this page."
  >
    <template #extra>
      <a-button type="primary" @click="handleCilckBackHome">返回首页</a-button>
    </template>
  </a-result>
</template>

<script>
import { defineComponent } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    const router = useRouter();
    const handleCilckBackHome = () => {
      router.push("/journey");
    };

    return {
      handleCilckBackHome,
    };
  },
});
</script>
