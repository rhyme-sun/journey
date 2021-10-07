<template>
  <router-view />
</template>
<script>
import { defineComponent } from "vue";
import { useStore } from "vuex";

export default defineComponent({
  setup() {
    const store = useStore();
    store.dispatch("category/findAll");
    store.dispatch("tag/findAll");

    if (sessionStorage.getItem("store.state.auth")) {
      const state = JSON.parse(sessionStorage.getItem("store.state.auth"));
      store.commit("auth/resetAuthority", state.authorities);
    }

    window.addEventListener("beforeunload", () => {
      const auth = store.state.auth;
      sessionStorage.setItem("store.state.auth", JSON.stringify(auth));
    });
  },
});
</script>

<style>
:root {
  --journey-title-font-size: 25px;
}
</style>
