<template>
  <div class="management-tag">
    <h3>标签管理</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <TagController />
    <a-table
      :columns="columns"
      :data-source="dataSource"
      :row-selection="rowSelection"
      :row-key="(record) => record.tagId"
      @expand="listChildTags"
    >
      <template #category="{ text: category }">
        <span>
          {{ category?.categoryName }}
        </span>
      </template>
    </a-table>
  </div>
</template>
<script>
import { defineComponent, ref } from "vue";
import { useStore } from "vuex";
import TagController from "./TagController.vue";
import tagRest from "@/api/tagRest";

const columns = [
  {
    title: "标签名称",
    dataIndex: "tagName",
    key: "name",
    width: "25%",
  },
  {
    title: "所属分类",
    dataIndex: "category",
    key: "age",
    slots: {
      customRender: "category",
    },
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    key: "createTime",
    width: "20%",
  },
  {
    title: "更新时间",
    dataIndex: "updateTime",
    key: "updateTime",
    width: "20%",
  },
];

export default defineComponent({
  setup() {
    const rowSelection = {
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(
          `selectedRowKeys: ${selectedRowKeys}`,
          "selectedRows: ",
          selectedRows
        );
      },
      onSelect: (record, selected, selectedRows) => {
        console.log(record, selected, selectedRows);
      },
      onSelectAll: (selected, selectedRows, changeRows) => {
        console.log(selected, selectedRows, changeRows);
      },
    };

    const store = useStore();
    store.dispatch("tag/findAll");
    store.dispatch("category/findAll");

    const dataSource = ref([]);
    tagRest.findByParams({ onlyGroup: true }).then((response) => {
      response.forEach((tag) => {
        tag.category = store.getters["category/getCategory"](tag.categoryId);
        tag.children = [];
      });
      dataSource.value = response;
    });

    const listChildTags = (expanded, gourpTag) => {
      if (expanded) {
        if (gourpTag.children.length === 0) {
          tagRest.findByParams({ groupId: gourpTag.tagId }).then((response) => {
            response.forEach((tag) => {
              tag.category = store.getters["category/getCategory"](
                tag.categoryId
              );
            });
            gourpTag.children = response;
          });
        }
      }
    };

    return {
      dataSource,
      columns,
      rowSelection,
      listChildTags,
    };
  },
  components: {
    TagController,
  },
});
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}
.management-tag > h3 {
  font-size: var(--journey-title-font-size);
}
</style>
