const webpack = require("webpack");

// const isProd = process.env.NODE_ENV === "production";

const assetsCDN = {
  // webpack build externals
  externals: {
    vue: "Vue",
    vuex: "Vuex",
    "vue-router": "vue-router",
    "ant-design-vue": "antd",
  },
  css: [],
  js: [
    "https://cdn.staticfile.org/vue/3.0.0/vue.runtime.global.prod.js",
    "https://cdn.staticfile.org/vuex/4.0.0/vuex.global.prod.min.js",
    "https://cdn.staticfile.org/vue-router/4.0.0/vue-router.global.prod.min.js",
    "https://cdn.staticfile.org/ant-design-vue/2.2.6/antd.min.js",
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
    // externals: isProd ? assetsCDN.externals : {},
    externals: {},
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
