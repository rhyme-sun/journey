<template>
  <div class="logo" @click="handleClickLogo">
    <div class="simon-portgas" @click="handleClickLogo" />
  </div>
  <a-menu theme="dark" mode="horizontal">
    <CategoryLayout />
    <a-sub-menu
      v-for="item in getMenuData"
      :key="item.path"
      @click="handleChangeMenu(item.path)"
    >
      <template #icon>
        <component :is="item.meta.icon"></component>
      </template>
      <template #title> {{ item.meta.title }}</template>
    </a-sub-menu>
    <ManagementLayout v-if="isAdmin" />
    <Login />
  </a-menu>
</template>

<script>
import { defineComponent, computed } from "vue";
import { useRouter } from "vue-router";
import {
  TagsOutlined,
  CalendarOutlined,
  RobotOutlined,
  DownOutlined,
} from "@ant-design/icons-vue";
import CategoryLayout from "./CategoryLayout.vue";
import ManagementLayout from "./ManagementLayout.vue";
import Login from "@/views/user/Login.vue";
import Authorized from "@/components/Authorized.vue";
import { check } from "@/utils/auth.js";

import { createHomeMenuData } from "../utils/menu";

export default defineComponent({
  setup() {
    const router = useRouter();
    const getMenuData = computed(() => {
      return createHomeMenuData(router.options.routes);
    });

    const handleChangeMenu = (path) => {
      router.push(path);
    };

    const handleClickLogo = () => {
      router.push("/journey");
    };

    const isAdmin = computed(() => {
      return check(["ROLE_admin"]);
    });

    return {
      getMenuData,
      handleChangeMenu,
      handleClickLogo,
      isAdmin,
    };
  },
  components: {
    CategoryLayout,
    ManagementLayout,
    Authorized,
    Login,
    TagsOutlined,
    CalendarOutlined,
    RobotOutlined,
    DownOutlined,
  },
});
</script>

<style>
.ant-menu-overflow > .user {
  margin-left: auto;
}

.logo {
  width: 30px;
  height: 30px;
  margin-right: 160px;
  background: transparent;
  background-image: url(../assets/logo.png);
  background-size: 100% 100%;
  cursor: pointer;
}
.simon-portgas {
  width: 150px;
  height: 16px;
  margin-left: 35px;
  margin-top: 11px;
  cursor: pointer;
  background-image: url(../assets/simon-portgas.png);
  background-size: 100% 100%;
}

.user {
  cursor: pointer;
  margin-right: -22px;
}
</style>
