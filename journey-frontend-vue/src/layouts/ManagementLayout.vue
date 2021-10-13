<template>
  <a-sub-menu class="management">
    <template #icon>
      <SettingOutlined />
    </template>
    <template #title>博客管理</template>
    <a-menu-item
      v-for="item in sideMenuData"
      :key="item.name"
      @click="handleChangeManagement(item.path)"
    >
      {{ item.meta.title }}
    </a-menu-item>
  </a-sub-menu>
</template>
<script>
import { defineComponent, computed } from "vue";
import { useRouter } from "vue-router";
import { SettingOutlined } from "@ant-design/icons-vue";
import { createManagementSideMenuData } from "@/utils/menu";

export default defineComponent({
  components: {
    SettingOutlined,
  },

  setup() {
    const router = useRouter();
    const sideMenuData = computed(() =>
      createManagementSideMenuData(router.options.routes)
    );
    const handleChangeManagement = (path) => {
      router.push(path);
    };

    return {
      sideMenuData,
      handleChangeManagement,
    };
  },
});
</script>
<style>
.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
  cursor: pointer;
}
.management-content {
  min-height: 910px;
  padding: 30px 40px;
  background: #fff;
}
</style>
