<template>
  <a-button type="primary" @click="showModal">新建</a-button>
  <a-modal
    v-model:visible="visible"
    @ok="handleOk"
    title="创建博客"
    width="100%"
    wrapClassName="full-modal"
    okText="创建"
    cancelText="取消"
  >
    <a-form ref="formRef" :model="formState" :rules="rules">
      <a-form-item label="文章标题" name="title" required>
        <a-input v-model:value="formState.title" />
      </a-form-item>
      <a-form-item label="文章概要" name="contentAbstract" required>
        <a-textarea v-model:value="formState.contentAbstract" :rows="2" />
      </a-form-item>
      <a-row>
        <a-col :span="6">
          <a-form-item
            label="文章分类"
            :wrapper-col="{ span: 16, offset: 0 }"
            name="categoryId"
            required
          >
            <a-select
              v-model:value="formState.categoryId"
              placeholder="请选择文章分类"
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
            label="文章标签"
            :wrapper-col="{ span: 50, offset: 0 }"
            name="tagIds"
            required
          >
            <a-input-group compact>
              <a-select
                style="width: 30%"
                v-model:value="groupId"
                placeholder="请选择标签组"
                @select="listTagOptions"
              >
                <a-select-option
                  v-for="tagGroup in tagGroupOptions"
                  :key="tagGroup.tagId"
                  :value="tagGroup.tagId"
                  >{{ tagGroup.tagName }}</a-select-option
                >
              </a-select>
              <a-select
                v-model:value="formState.tagIds"
                mode="tags"
                style="width: 70%"
                placeholder="请选择文章标签"
                :options="tagOptions"
              >
              </a-select>
            </a-input-group>
          </a-form-item>
        </a-col>
        <a-col :span="6">
          <a-form-item
            label="是否发布"
            :wrapper-col="{ span: 15, offset: 0 }"
            :label-col="{ offset: 2 }"
          >
            <a-switch v-model:checked="formState.isPublished" />
          </a-form-item>
        </a-col>
      </a-row>
      <a-form-item label="内容" name="content" required>
        <v-md-editor v-model="formState.content"></v-md-editor>
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script>
import { defineComponent, ref, reactive, toRaw, computed } from "vue";
import { useStore } from "vuex";
import VMdEditor from "@kangc/v-md-editor";
import "@kangc/v-md-editor/lib/style/base-editor.css";
import githubTheme from "@kangc/v-md-editor/lib/theme/github.js";
import "@kangc/v-md-editor/lib/theme/style/github.css";
import hljs from "highlight.js";
import postRest from "@/api/postRest";

VMdEditor.use(githubTheme, {
  Hljs: hljs,
});

export default defineComponent({
  setup() {
    const visible = ref(false);
    const showModal = () => {
      visible.value = true;
    };
    const handleOk = () => {
      formRef.value
        .validate()
        .then(() => {
          postRest
            .save(toRaw(formState))
            .then(() => {
              visible.value = false;
            })
            .catch((error) => {
              console.debug("error", error);
            });
        })
        .catch((error) => {
          console.debug("error", error);
        });
    };

    const formRef = ref();
    const formState = reactive({
      title: "",
      contentAbstract: undefined,
      categoryId: "",
      tagIds: [],
      isPublished: true,
      content: "",
    });
    const rules = {
      title: [
        {
          required: true,
          message: "请输入文章标题",
          trigger: "blur",
        },
        {
          max: 100,
          message: "最多100个字符",
          trigger: "blur",
        },
      ],
      contentAbstract: [
        {
          required: true,
          message: "请输入文章概要",
          trigger: "blur",
        },
        {
          max: 200,
          message: "最多200个字符",
          trigger: "blur",
        },
      ],
      categoryId: [
        {
          required: true,
          message: "请选择文章类别",
          trigger: "blur",
        },
      ],
      tagIds: [
        {
          required: true,
          message: "请选择文章标签",
          trigger: "blur",
          type: "array",
        },
      ],
      content: [
        {
          required: true,
          message: "请输入内容",
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

    const groupId = ref("");
    const tagOptions = ref([]);
    const listTagOptions = (groupId) => {
      tagOptions.value = tagOptions.value.filter((tagOption) =>
        formState.tagIds.includes(tagOption.value)
      );
      const tags = store.getters["tag/listTagsByGroupId"](groupId);
      if (tags) {
        tags.forEach((tag) => {
          const tagOption = {
            value: tag.tagId,
            label: tag.tagName,
          };
          if (!formState.tagIds.includes(tagOption.value)) {
            tagOptions.value.push(tagOption);
          }
        });
      }
      return tagOptions;
    };
    return {
      visible,
      showModal,
      handleOk,
      formRef,
      formState,
      rules,
      categories,
      tagGroupOptions,
      groupId,
      tagOptions,
      listTagOptions,
    };
  },
  components: { VMdEditor },
});
</script>

<style lang="less">
.v-md-editor {
  text-align: left;
  height: 600px;
}
.full-modal {
  .ant-modal {
    max-width: 100%;
    top: 0;
    padding-bottom: 0;
    margin: 0;
  }
  .ant-modal-content {
    display: flex;
    flex-direction: column;
    height: calc(100vh);
  }
  .ant-modal-body {
    flex: 1;
  }
}
</style>
