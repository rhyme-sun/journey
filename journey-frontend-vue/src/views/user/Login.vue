<template>
  <a-sub-menu class="user" @click="handleClickLogin">
    <template #icon>
      <a-tooltip placement="bottom">
        <template #title>
          <span v-if="login">登出</span>
          <span v-else>登录</span>
        </template>
        <LogoutOutlined v-if="login" :style="{ fontSize: '20px' }" />
        <LoginOutlined v-else :style="{ fontSize: '20px' }" />
      </a-tooltip>
    </template>
    <a-modal v-model:visible="visible" title="登录" :footer="null">
      <a-form
        :model="formState"
        @finish="handleFinish"
        @finishFailed="handleFinishFailed"
      >
        <a-form-item required>
          <a-input v-model:value="formState.username" placeholder="Username">
            <template #prefix
              ><UserOutlined style="color: rgba(0, 0, 0, 0.25)"
            /></template>
          </a-input>
        </a-form-item>
        <a-form-item required>
          <a-input
            v-model:value="formState.password"
            type="password"
            placeholder="Password"
          >
            <template #prefix>
              <LockOutlined style="color: rgba(0, 0, 0, 0.25)" />
            </template>
          </a-input>
        </a-form-item>
        <a-form-item>
          <a-button
            type="primary"
            html-type="submit"
            :disabled="formState.username === '' || formState.password === ''"
          >
            登录
          </a-button>
        </a-form-item>
      </a-form>
    </a-modal>
  </a-sub-menu>
</template>

<script>
import { defineComponent, ref, reactive } from "vue";
import { useStore } from "vuex";
import {
  LoginOutlined,
  LogoutOutlined,
  UserOutlined,
  LockOutlined,
} from "@ant-design/icons-vue";
import { isLogin } from "@/utils/auth.js";

export default defineComponent({
  setup() {
    const visible = ref(false);

    const login = ref(isLogin());

    const store = useStore();
    const handleClickLogin = () => {
      if (login.value) {
        store.dispatch("auth/logout");
      } else {
        visible.value = true;
      }
    };

    const formState = reactive({
      username: "",
      password: "",
    });

    const handleFinish = () => {
      store.dispatch("auth/login", {
        username: formState.username,
        password: formState.password,
      });
      visible.value = false;
    };

    const handleFinishFailed = (errors) => {
      console.log(errors);
    };

    return {
      login,
      visible,
      handleClickLogin,
      formState,
      handleFinish,
      handleFinishFailed,
    };
  },
  components: {
    LoginOutlined,
    LogoutOutlined,
    UserOutlined,
    LockOutlined,
  },
});
</script>

<style></style>
