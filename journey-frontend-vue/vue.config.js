const webpack = require("webpack");

const isProd = process.env.NODE_ENV === "production";

const assetsCDN = {
  // webpack build externals
  externals: {
    vue: "Vue",
    vuex: "Vuex",
    "vue-router": "VueRouter",
    "ant-design-vue": "antd",
    "highlight.js": "hljs",
  },
  css: [],
  js: [
    "https://unpkg.com/vue@3",
    "https://unpkg.com/vue-router@4",
    "https://unpkg.com/vuex@4",
    "https://cdn.jsdelivr.net/npm/ant-design-vue@2.2.6/dist/antd.js",
    "https://cdn.jsdelivr.net/gh/highlightjs/cdn-release@11.3.1/build/highlight.min.js",
  ],
};

module.exports = {
  css: {
    loaderOptions: {
      less: {
        modifyVars: {
          "layout-header-background": "transparent",
          "font-size-base": "15px",
        },
        javascriptEnabled: true,
      },
    },
  },
  configureWebpack: {
    plugins: [
      // Ignore all locale files of moment.js
      new webpack.IgnorePlugin(/^\.\/locale$/, /moment$/),
    ],
    externals: isProd ? assetsCDN.externals : {},
    // externals: assetsCDN.externals,
  },
  chainWebpack: (config) => {
    if (process.env.NODE_ENV === "production") {
      config.plugin("html").tap((args) => {
        args[0].cdn = assetsCDN;
        return args;
      });
    }
  },
};
