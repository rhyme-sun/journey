<template>
  <div class="tag-table-controller">
    <a-button type="primary" @click="showModal">新增</a-button>
    <a-modal
      v-model:visible="visible"
      title="新增标签"
      width="40%"
      @ok="handleOk"
      okText="新增"
      cancelText="取消"
    >
      <a-form ref="formRef" :model="formState" :rules="rules">
        <a-row>
          <a-col :span="12">
            <a-form-item
              label="标签分类"
              :wrapper-col="{ span: 16, offset: 0 }"
              name="categoryId"
              required
            >
              <a-select
                v-model:value="formState.categoryId"
                placeholder="请选择标签分类"
              >
                <a-select-option
                  v-for="category in categories"
                  :key="category.categoryId"
                  :value="category.categoryId"
                >
                  {{ category.categoryName }}
                </a-select-option>
              </a-select>
            </a-form-item>
          </a-col>
          <a-col :span="12">
            <a-form-item
              label="是否为组标签"
              :wrapper-col="{ span: 15, offset: 0 }"
              :label-col="{ offset: 2 }"
            >
              <a-switch v-model:checked="formState.isGroup" />
            </a-form-item>
          </a-col>
        </a-row>
        <a-form-item label="标签名称" name="tagName" required>
          <a-input
            v-if="formState.isGroup"
            v-model:value="formState.tagName"
            placeholder="请输入标签名称"
          />
          <a-input-group v-else compact>
            <a-select
              style="width: 25%"
              v-model:value="formState.groupId"
              placeholder="请选择标签分组"
            >
              <a-select-option
                v-for="tagGroup in tagGroupOptions"
                :key="tagGroup.tagId"
                :value="tagGroup.tagId"
                >{{ tagGroup.tagName }}
              </a-select-option>
            </a-select>
            <a-input
              style="width: 75%"
              v-model:value="formState.tagName"
              placeholder="请输入标签名称"
            />
          </a-input-group>
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import { defineComponent, reactive, ref, toRaw, computed } from "vue";
import { useStore } from "vuex";
import tagRest from "@/api/tagRest";

export default defineComponent({
  setup() {
    const visible = ref(false);
    const showModal = () => {
      visible.value = true;
    };
    const handleOk = (e) => {
      console.log(e);
      formRef.value.validate().then(() => {
        console.log("values", formState, toRaw(formState));
        tagRest
          .saveTag(toRaw(formState))
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
      tagName: "",
      isGroup: false,
      groupId: undefined,
      categoryId: "",
    });
    const rules = {
      tagName: [
        {
          required: true,
          message: "请输入标签名称",
          trigger: "blur",
        },
        {
          max: 30,
          message: "最多30个字符",
          trigger: "blur",
        },
      ],
      categoryId: [
        {
          required: true,
          message: "请选择标签类别",
          trigger: "blur",
        },
      ],
    };

    const store = useStore();
    const categories = computed(() => {
      return store.state.category.categories;
    });
    const tagGroupOptions = computed(() => {
      return store.getters["tag/listTagGroups"](formState.categoryId);
    });

    return {
      visible,
      showModal,
      handleOk,
      formRef,
      formState,
      rules,
      categories,
      tagGroupOptions,
    };
  },
});
</script>

<style>
.tag-table-controller {
  text-align: left;
  margin-bottom: 10px;
}
</style>
