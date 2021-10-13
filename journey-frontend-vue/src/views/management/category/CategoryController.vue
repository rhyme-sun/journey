<template>
  <div class="category-table-controller">
    <a-button type="primary" @click="showModal">新增</a-button>
    <a-modal
      v-model:visible="visible"
      title="新增分类"
      width="40%"
      @ok="handleOk"
      okText="新增"
      cancelText="取消"
    >
      <a-form ref="formRef" :model="formState" :rules="rules">
        <a-form-item label="分类名称" name="categoryName" required>
          <a-input v-model:value="formState.categoryName" />
        </a-form-item>
        <a-form-item label="分类描述" name="description" required>
          <a-input v-model:value="formState.description" />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>
<script>
import { defineComponent, reactive, ref, toRaw } from "vue";
import categoryRest from "@/api/categoryRest";
export default defineComponent({
  setup() {
    const visible = ref(false);
    const showModal = () => {
      visible.value = true;
    };
    const handleOk = (e) => {
      console.log(e);
      formRef.value.validate().then(() => {
        categoryRest
          .saveCategory(toRaw(formState))
          .then((result) => {
            console.log(result);
            visible.value = false;
          })
          .catch((error) => {
            console.log("error", error);
          });
      });
    };

    const formRef = ref();
    const formState = reactive({
      categoryName: "",
      description: "",
    });
    const rules = {
      categoryName: [
        {
          required: true,
          message: "请输入分类名称",
          trigger: "blur",
        },
        {
          max: 30,
          message: "最多30个字符",
          trigger: "blur",
        },
      ],
      description: [
        {
          required: true,
          message: "请输入分类描述",
          trigger: "blur",
        },
        {
          max: 100,
          message: "最多100个字符",
          trigger: "blur",
        },
      ],
    };

    return {
      visible,
      showModal,
      handleOk,
      formRef,
      formState,
      rules,
    };
  },
});
</script>
<style>
.category-table-controller {
  margin-bottom: 10px;
  text-align: left;
}
</style>
