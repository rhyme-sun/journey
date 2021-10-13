import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import {
  Layout,
  Menu,
  Pagination,
  Card,
  Table,
  Form,
  Input,
  Button,
  Tag,
  Divider,
  Switch,
  Radio,
  Checkbox,
  Select,
  Modal,
  Row,
  Col,
  Upload,
  Popconfirm,
  Dropdown,
  Timeline,
  Result,
  Tooltip,
} from "ant-design-vue";

createApp(App)
  .use(store)
  .use(router)
  .use(Layout)
  .use(Menu)
  .use(Pagination)
  .use(Card)
  .use(Table)
  .use(Form)
  .use(Input)
  .use(Button)
  .use(Tag)
  .use(Divider)
  .use(Switch)
  .use(Radio)
  .use(Checkbox)
  .use(Select)
  .use(Modal)
  .use(Row)
  .use(Col)
  .use(Upload)
  .use(Popconfirm)
  .use(Dropdown)
  .use(Timeline)
  .use(Result)
  .use(Tooltip)
  .mount("#app");

// if (process.env.NODE_ENV !== "prod") {
//   if ("__VUE_DEVTOOLS_GLOBAL_HOOK__" in window) {
//     window.__VUE_DEVTOOLS_GLOBAL_HOOK__.Vue = app;
//   }
//   app.config.devtools = true;
// }
