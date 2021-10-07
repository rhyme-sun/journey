import { createRouter, createWebHistory } from "vue-router";
import { notification } from "ant-design-vue";
import NotFound from "../views/404";
import Forbidden from "../views/403";
import { findLast } from "lodash";
import { check, isLogin } from "../utils/auth";
import NProgress from "nprogress";
import "nprogress/nprogress.css";

const routes = [
  {
    path: "/journey",
    name: "Journey",
    component: () =>
      import(/* webpackChunkName: "layout" */ "../layouts/BasicLayout.vue"),
    meta: { authority: ["guest", "ROLE_admin"] },
    children: [
      {
        path: "/",
        redirect: "/journey",
        meta: { hideInMenu: true },
      },
      {
        path: "/journey",
        component: () =>
          import(/* webpackChunkName: "abstract" */ "../views/Home.vue"),
        meta: { hideInMenu: true },
      },
      {
        path: "/journey/posts/:id",
        component: () =>
          import(/* webpackChunkName: "post" */ "../views/post/Post.vue"),
        meta: { hideInMenu: true },
      },
      {
        path: "/journey/categories",
        name: "Category",
        meta: { icon: "folder-open-outlined", title: "分类", hideInMenu: true },
      },
      {
        path: "/journey/categories/:id/posts",
        name: "CategoryPost",
        component: () =>
          import(
            /* webpackChunkName: "category" */ "../views/post/PostPage.vue"
          ),
        meta: { hideInMenu: true },
      },
      {
        path: "/journey/tags",
        name: "Tag",
        component: () =>
          import(/* webpackChunkName: "tag" */ "../views/tag/Tag.vue"),
        meta: { icon: "tags-outlined", title: "标签", hideInMenu: false },
      },
      {
        path: "/journey/tags/:id/posts",
        name: "TagPost",
        component: () =>
          import(/* webpackChunkName: "tag" */ "../views/post/PostPage.vue"),
        meta: { hideInMenu: true },
      },
      {
        path: "/journey/archives",
        name: "Archive",
        component: () =>
          import(
            /* webpackChunkName: "archive" */ "../views/archive/Archive.vue"
          ),
        meta: { icon: "calendar-outlined", title: "归档", hideInMenu: false },
      },
      {
        path: "/journey/archives/:year",
        name: "ArchiveYearPost",
        component: () =>
          import(
            /* webpackChunkName: "archive" */ "../views/post/PostPage.vue"
          ),
        meta: { hideInMenu: true },
      },
      {
        path: "/journey/archives/:year/:month",
        name: "ArchiveMonthPost",
        component: () =>
          import(
            /* webpackChunkName: "archive" */ "../views/post/PostPage.vue"
          ),
        meta: { hideInMenu: true },
      },
      {
        path: "/journey/about",
        name: "About",
        component: () =>
          import(/* webpackChunkName: "about" */ "../views/about/About.vue"),
        meta: { icon: "robot-outlined", title: "关于", hideInMenu: false },
      },
      // {
      //   path: "/journey/go-to-management",
      //   redirect: "/journey/management",
      //   meta: {
      //     icon: "setting-outlined",
      //     title: "博客管理",
      //     hideInMenu: false,
      //     authority: ["ROLE_admin"],
      //   },
      // },
      {
        path: "/journey/management",
        name: "Management",
        meta: {
          icon: "setting-outlined",
          title: "博客管理",
          hideInMenu: true,
          authority: ["ROLE_admin"],
        },
        component: () =>
          import(
            /* webpackChunkName: "management" */ "../components/RenderRouterView.vue"
          ),
        children: [
          {
            path: "/journey/management",
            redirect: "/journey/management/dashboard",
            meta: { hideInMenu: true },
          },
          {
            path: "/journey/management/dashboard",
            name: "ManagementDashboard",
            component: () =>
              import(
                /* webpackChunkName: "management-dashboard" */ "../views/management/ManagementDashboard.vue"
              ),
            meta: { icon: "1", title: "仪表盘", hideInMenu: false },
          },
          {
            path: "/journey/management/post",
            name: "ManagementPost",
            component: () =>
              import(
                /* webpackChunkName: "management-post" */ "../views/management/ManagementPost.vue"
              ),
            meta: { icon: "1", title: "博客管理", hideInMenu: false },
          },
          {
            path: "/journey/management/categories",
            name: "ManagementCategory",
            component: () =>
              import(
                /* webpackChunkName: "management-category" */ "../views/management/ManagementCategory.vue"
              ),
            meta: { icon: "1", title: "分类管理", hideInMenu: false },
          },
          {
            path: "/journey/management/tags",
            name: "ManagementTag",
            component: () =>
              import(
                /* webpackChunkName: "management-tag" */ "../views/management/ManagementTag.vue"
              ),
            meta: { icon: "1", title: "标签管理", hideInMenu: false },
          },
          {
            path: "/journey/management/images",
            name: "ManagementImage",
            component: () =>
              import(
                /* webpackChunkName: "management-tag" */ "../views/management/ManagementImage.vue"
              ),
            meta: { icon: "1", title: "图片管理", hideInMenu: false },
          },
        ],
      },
    ],
  },
  {
    path: "/journey/user",
    component: () =>
      import(/* webpackChunkName: "layout" */ "../layouts/UserLayout.vue"),
    children: [
      {
        path: "/journey/user",
        redirect: "/journey/user/login",
      },
      {
        path: "/journey/user/login",
        name: "Login ",
        component: () =>
          import(/* webpackChunkName: "user" */ "../views/user/Login.vue"),
      },
      {
        path: "/journey/user/register",
        name: "Register ",
        component: () =>
          import(/* webpackChunkName: "user" */ "../views/user/Register.vue"),
      },
    ],
  },
  {
    path: "/403",
    name: "403",
    hideInMenu: true,
    component: Forbidden,
  },
  {
    path: "/:pathMatch(.*)*",
    name: "404",
    hideInMenu: true,
    component: NotFound,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

router.beforeEach((to, from, next) => {
  if (to.path !== from.path) {
    NProgress.start();
  }
  const record = findLast(to.matched, (record) => record.meta.authority);
  if (record && !check(record.meta.authority)) {
    if (!isLogin() && to.path !== "/journey/user/login") {
      next({
        path: "/journey",
      });
    } else if (to.path !== "/journey/user/login") {
      notification.open({
        message: "403",
        description: "您没有权限访问，请联系管理员。",
      });
      next({
        path: "/403",
      });
    }
    NProgress.done();
  }
  next();
});

router.afterEach(() => {
  NProgress.done();
});

export default router;
