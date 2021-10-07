<template>
  <a-button type="primary" @click="showModal">上传</a-button>
  <a-modal
    v-model:visible="visible"
    title="上传文章"
    @ok="handleOk"
    okText="上传"
    cancelText="取消"
    width="55%"
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
    </a-form>

    <div class="clearfix">
      <a-upload
        :file-list="imageList"
        list-type="picture"
        :remove="handleImageRemove"
        :before-upload="beforeImageUpload"
      >
        <a-button>
          <upload-outlined></upload-outlined>
          上传图片
        </a-button>
      </a-upload>
      <br />
      <a-upload
        :file-list="fileList"
        :remove="handleRemove"
        :before-upload="beforeUpload"
      >
        <a-button :disabled="fileList.length === 1">
          <upload-outlined></upload-outlined>
          上传博客
        </a-button>
      </a-upload>
    </div>
  </a-modal>
</template>
<script>
import { defineComponent, reactive, ref, computed } from "vue";
import { useStore } from "vuex";
import { UploadOutlined } from "@ant-design/icons-vue";
import { message } from "ant-design-vue";
import postRest from "@/api/postRest";

export default defineComponent({
  setup() {
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
          message: "请输入博客标题",
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
          message: "请输入博客概要",
          trigger: "blur",
        },
        {
          max: 100,
          message: "最多100个字符",
          trigger: "blur",
        },
      ],
      categoryId: [
        {
          required: true,
          message: "请选择博客类别",
          trigger: "blur",
        },
      ],
      tagIds: [
        {
          required: true,
          message: "请选择博客标签",
          trigger: "blur",
          type: "array",
        },
      ],
    };

    const store = useStore();

    const categories = computed(() => {
      return store.state.category.categories;
    });

    const tagGroupOptions = computed(() => {
      return store.getters["tag/listTagGroups"]();
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

    const imageList = ref([]);
    const imageUploading = ref(false);
    const handleImageRemove = (image) => {
      const index = imageList.value.indexOf(image);
      const newImageList = imageList.value.slice();
      newImageList.splice(index, 1);
      imageList.value = newImageList;
    };
    const beforeImageUpload = (image) => {
      imageList.value = [...imageList.value, image];
      return false;
    };

    const fileList = ref([]);
    const uploading = ref(false);
    const handleRemove = (file) => {
      const index = fileList.value.indexOf(file);
      const newFileList = fileList.value.slice();
      newFileList.splice(index, 1);
      fileList.value = newFileList;
    };
    const beforeUpload = (file) => {
      fileList.value = [...fileList.value, file];
      return false;
    };

    const visible = ref(false);
    const showModal = () => {
      visible.value = true;
    };
    const handleOk = () => {
      formRef.value.validate().then(() => {
        const formData = new FormData();
        imageList.value.forEach((image) => {
          formData.append("files[]", image);
        });
        fileList.value.forEach((file) => {
          formData.append("file", file);
        });
        imageUploading.value = true;
        uploading.value = true;

        Object.keys(formState).forEach((key) => {
          formData.append(key, formState[key]);
        });

        postRest
          .upload(formData)
          .then(() => {
            imageList.value = [];
            fileList.value = [];
            imageUploading.value = false;
            uploading.value = false;
            message.success("上传成功。");
            visible.value = false;
          })
          .catch(() => {
            imageUploading.value = false;
            uploading.value = false;
            message.error("上传失败。");
          });
      });
    };

    return {
      formRef,
      formState,
      rules,
      categories,
      groupId,
      tagGroupOptions,
      tagOptions,
      listTagOptions,
      imageList,
      imageUploading,
      handleImageRemove,
      beforeImageUpload,
      fileList,
      uploading,
      handleRemove,
      beforeUpload,
      visible,
      showModal,
      handleOk,
    };
  },
  components: {
    UploadOutlined,
  },
});
</script>
