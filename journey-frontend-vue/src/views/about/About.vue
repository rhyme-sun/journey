<template>
  <div class="about">
    <h3>关于</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <a-typography class="about-typography">
      <a-typography-paragraph>
        <a-typography-title :level="4" type="secondary"
          >关于本站</a-typography-title
        >
      </a-typography-paragraph>

      <a-typography-paragraph>
        Journey
        是一个前后端分离的简单的博客系统，主要为了记录一些学习笔记，方便在线查看，下面简要列举一些使用到的技术。
      </a-typography-paragraph>
      <a-typography-paragraph>
        <a-typography-text> 前端使用到的一些技术： </a-typography-text>
      </a-typography-paragraph>
      <a-typography-paragraph>
        <ul>
          <li>
            <a-typography-link href="https://cn.vuejs.org/index.html"
              >Vue</a-typography-link
            >
          </li>
          <li>
            <a-typography-link
              href="https://2x.antdv.com/docs/vue/introduce-cn/"
              >Ant Design Vue</a-typography-link
            >
          </li>
          <li>
            <a-typography-link href="https://github.com/umijs/umi-request"
              >umi-request</a-typography-link
            >
          </li>
          <li>
            <a-typography-link
              href="https://ckang1229.gitee.io/vue-markdown-editor/zh/"
              >v-md-editor</a-typography-link
            >
          </li>
        </ul>
      </a-typography-paragraph>
      <a-typography-paragraph
        ><a-typography-text> 后端使用到的一些技术：</a-typography-text>
      </a-typography-paragraph>
      <a-typography-paragraph>
        <a-typography-text> </a-typography-text>
        <ul>
          <li>
            <a-typography-link href="https://spring.io/projects/spring-boot"
              >Spring Boot</a-typography-link
            >
          </li>
          <li>
            <a-typography-link href="https://spring.io/projects/spring-security"
              >Spring Security</a-typography-link
            >
          </li>
          <li>
            <a-typography-link href="https://baomidou.com/"
              >MyBatis-Plus</a-typography-link
            >
          </li>
          <li>
            <a-typography-link href="https://www.mysql.com/cn/"
              >MySQL</a-typography-link
            >
          </li>
          <li>
            <a-typography-link href="https://www.docker.com/"
              >Docker</a-typography-link
            >
          </li>
        </ul>
      </a-typography-paragraph>
      <a-typography-paragraph
        ><a-typography-text> 网站的构建参考了以下博客：</a-typography-text>
      </a-typography-paragraph>
      <a-typography-paragraph>
        <ul>
          <li>
            <a-typography-link href="https://edi.wang/">
              Edi Wang
            </a-typography-link>
          </li>
          <li>
            <a-typography-link href="http://mogu-go.com/#/">
              蘑菇博客
            </a-typography-link>
          </li>
        </ul>
      </a-typography-paragraph>
      <a-typography-paragraph>
        博客网站源代码:
        <a-typography-link href="https://github.com/rhyme-sun/journey">
          rhyme-sun/journey (github.com)
        </a-typography-link>
      </a-typography-paragraph>
      <a-typography-paragraph>
        <a-typography-title :level="4" type="secondary">
          关于本人
        </a-typography-title>
      </a-typography-paragraph>
      <a-typography-paragraph> 做好眼前的事情。 </a-typography-paragraph>
    </a-typography>
  </div>
</template>

<style scoped>
.about > h3 {
  font-size: var(--journey-title-font-size);
}
.about-typography > h3 {
  font-size: var(--journey-title-font-size);
  font: 1.5em sans-serif;
}
</style>
