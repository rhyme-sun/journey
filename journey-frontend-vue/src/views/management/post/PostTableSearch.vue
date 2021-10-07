<template>
  <div class="post-search">
    <a-form
      layout="inline"
      :model="formState"
      :label-col="labelCol"
      :wrapper-col="wrapperCol"
    >
      <a-form-item label="文章标题">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item label="文章分类">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item label="文章标签">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item label="是否发布">
        <a-input v-model:value="formState.name" />
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
        <a-button type="primary" @click="onSubmit">查询</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script>
import { defineComponent, reactive, toRaw } from "vue";
export default defineComponent({
  setup() {
    const formState = reactive({
      name: "",
      region: undefined,
      date1: undefined,
      delivery: false,
      type: [],
      resource: "",
      desc: "",
    });

    const onSubmit = () => {
      console.log("submit!", toRaw(formState));
    };

    return {
      labelCol: {
        span: 20,
      },
      wrapperCol: {
        span: 16,
      },
      formState,
      onSubmit,
    };
  },
});
</script>
<style>
.post-search {
  margin: 15px 0px;
}
</style>
