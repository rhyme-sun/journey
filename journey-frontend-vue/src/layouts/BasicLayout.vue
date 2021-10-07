<template>
  <a-layout style="background: transparent">
    <div class="layout-top">
      <a-layout-header>
        <Header />
      </a-layout-header>
    </div>
    <a-layout-content style="padding: 0 50px">
      <Content />
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      <Footer />
    </a-layout-footer>
  </a-layout>
</template>

<script>
import Header from "./Header.vue";
import Content from "./Content.vue";
import Footer from "./Footer.vue";

export default {
  components: {
    Header,
    Content,
    Footer,
  },
};
</script>

<style>
body {
  background: #f0f2f5;
  /* background-image: url(../assets/layout-content-bg.jpg); */
}
.layout-top {
  padding-bottom: 150px;
  background-image: url(../assets/layout-top-bg.png);
}
.ant-layout-header {
  margin: 0% 12%;
  padding: 0px 0px;
  border-bottom: 1px #f0f2f5 solid;
}
.ant-layout-content {
  margin-top: -115px;
  /* background-image: url(../assets/layout-content-bg.jpg); */
}
.ant-menu-dark.ant-menu-submenu-popup {
  background: #fff;
}
.ant-menu-item.ant-menu-item-only-child > .ant-menu-title-content {
  color: #000;
}
</style>
