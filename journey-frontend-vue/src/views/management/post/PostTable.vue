<template>
  <div class="management-post">
    <h3>博客管理</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <PostTableController />
    <PostTableSearch />
    <a-table
      :columns="columns"
      :data-source="data"
      :pagination="pagination"
      :row-key="(record) => record.postId"
      :row-selection="rowSelection"
      @change="handleTableChange"
    >
      <template #category="{ text: category }">
        <span>
          {{ category?.categoryName }}
        </span>
      </template>
      <template #tags="{ text: tags }">
        <span>
          <a-tag
            v-for="tag in tags"
            :key="tag.tagId"
            :color="tag.tagName.length > 5 ? 'green' : 'geekblue'"
          >
            {{ tag.tagName }}
          </a-tag>
        </span>
      </template>
      <template #yes-or-no="{ text }">
        <span v-if="text">是</span>
        <span v-else>否</span>
      </template>
      <template #action="{ record }">
        <span>
          <a @click="editPost(record)">编辑</a>
          <a-divider type="vertical" />
          <a @click="deletePost(record.key)">删除</a>
        </span>
      </template>
    </a-table>
  </div>
</template>
<script>
import { defineComponent, ref, computed } from "vue";
import { useStore } from "vuex";
import PostTableSearch from "./PostTableSearch.vue";
import PostTableController from "./PostTableController.vue";

import postRest from "@/api/postRest.js";

const columns = [
  {
    dataIndex: "title",
    key: "title",
    title: "文章标题",
  },
  {
    title: "文章分类",
    dataIndex: "category",
    key: "category",
    slots: {
      customRender: "category",
    },
  },
  {
    title: "文章标签",
    key: "tags",
    dataIndex: "tags",
    slots: {
      customRender: "tags",
    },
  },
  {
    title: "发布状态",
    dataIndex: "isPublished",
    key: "isPublished",
    slots: {
      customRender: "yes-or-no",
    },
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    key: "createTime",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
    key: "updateTime",
  },
  {
    title: "操作",
    key: "action",
    slots: {
      customRender: "action",
    },
  },
];

export default defineComponent({
  setup() {
    const data = ref([]);
    const total = ref(0);

    const store = useStore();
    postRest.page(1, 10).then((response) => {
      data.value = response.data;
      data.value.forEach((post) => {
        post.tags = store.getters["tag/listTags"](post.tagIds);
        post.category = store.getters["category/getCategory"](post.categoryId);
      });
      total.value = response.total;
    });

    const pagination = computed(() => ({
      total: total.value,
    }));

    const handleTableChange = (pag) => {
      postRest.page(pag.current, pag.pageSize).then((response) => {
        data.value = response.data;
        data.value.forEach((post) => {
          post.tags = store.getters["tag/listTags"](post.tagIds);
          post.category = store.getters["category/getCategory"](
            post.categoryId
          );
        });
      });
    };

    const rowSelection = {
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(
          `selectedRowKeys: ${selectedRowKeys}`,
          "selectedRows: ",
          selectedRows
        );
      },
    };

    const editPost = (record) => {
      console.log(record);
    };
    const deletePost = (postId) => {
      console.log(postId);
    };

    return {
      data,
      columns,
      pagination,
      handleTableChange,
      rowSelection,
      editPost,
      deletePost,
    };
  },

  components: {
    PostTableSearch,
    PostTableController,
  },
});
</script>
<style scoped>
.management-post > h3 {
  font-size: var(--journey-title-font-size);
}
</style>
