<template>
  <div class="management-category">
    <h3>分类管理</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <CategorController />
    <a-table
      :columns="columns"
      :data-source="dataSource"
      :row-key="(record) => record.categoryId"
      :row-selection="rowSelection"
    >
      <template
        v-for="col in ['categoryName', 'description']"
        #[col]="{ text, record }"
        :key="col"
      >
        <div>
          <a-input
            v-if="editableData[record.categoryId] || !record.categoryId"
            v-model:value="editableData[record.categoryId][col]"
            style="margin: -5px 0"
          />
          <template v-else>
            {{ text }}
          </template>
        </div>
      </template>
      <template #operation="{ record }">
        <div class="editable-row-operations">
          <span v-if="editableData[record.categoryId] || !record.categoryId">
            <a @click="save(record.categoryId)">保存</a>
            <a @click="cancel(record.categoryId)">取消</a>
          </span>
          <span v-else>
            <a @click="edit(record.categoryId)">编辑</a>
          </span>
        </div>
      </template>
    </a-table>
  </div>
</template>
<script>
import { defineComponent, reactive, ref } from "vue";
import { cloneDeep } from "lodash-es";
import CategorController from "./CategoryController.vue";
import categoryRest from "@/api/categoryRest";

const columns = [
  {
    title: "分类名称",
    dataIndex: "categoryName",
    width: "25%",
    slots: {
      customRender: "categoryName",
    },
  },
  {
    title: "分类描述",
    dataIndex: "description",
    width: "30%",
    slots: {
      customRender: "description",
    },
  },
  {
    title: "创建时间",
    dataIndex: "createTime",
    width: "20%",
  },
  {
    title: "操作",
    dataIndex: "operation",
    slots: {
      customRender: "operation",
    },
  },
];

export default defineComponent({
  setup() {
    const dataSource = ref([]);
    categoryRest.findAll().then((response) => {
      dataSource.value = response;
    });

    const rowSelection = {
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(
          `selectedRowKeys: ${selectedRowKeys}`,
          "selectedRows: ",
          selectedRows
        );
      },
    };

    const editableData = reactive({});

    const edit = (key) => {
      editableData[key] = cloneDeep(
        dataSource.value.find((item) => key === item.categoryId)
      );
    };

    const save = (key) => {
      categoryRest.saveCategory(editableData[key]);
      Object.assign(
        dataSource.value.find((item) => key === item.categoryId),
        editableData[key]
      );
      delete editableData[key];
    };

    const cancel = (key) => {
      delete editableData[key];
    };

    const handleAdd = () => {};

    const handleDelete = (key) => {
      console.log(key);
    };

    return {
      columns,
      dataSource,
      rowSelection,
      editingKey: "",
      editableData,
      edit,
      save,
      cancel,
      handleAdd,
      handleDelete,
    };
  },
  components: {
    CategorController,
  },
});
</script>
<style scoped>
.editable-row-operations a {
  margin-right: 8px;
}

.management-category > h3 {
  font-size: var(--journey-title-font-size);
}
</style>
