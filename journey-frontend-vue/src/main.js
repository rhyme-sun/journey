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
  Typography,
  BackTop,
} from "ant-design-vue";

const app = createApp(App);
app
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
  .use(Typography)
  .use(BackTop);

if (process.env.NODE_ENV === "production") {
  app.config.devtools = false;
}

app.mount("#app");
