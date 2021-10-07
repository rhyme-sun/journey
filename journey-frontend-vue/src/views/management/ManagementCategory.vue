<template>
  <CategoryTable />
</template>

<script>
import { defineComponent } from "vue";

import CategoryTable from "./category/CategoryTable.vue";

export default defineComponent({
  setup() {},
  components: {
    CategoryTable,
  },
});
</script>
