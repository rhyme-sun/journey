<template>
  <div style="text-align: left" class="archive">
    <h3>归档时间轴</h3>
    <a-divider style="height: 2px; background-color: #8c8c8c" />
    <a-timeline
      mode="alternate"
      v-for="postCount in postCounts"
      :key="postCount.year"
    >
      <a-timeline-item>
        <template #dot>
          <CalendarTwoTone
            style="font-size: 30px"
            :twoToneColor="getDotColor(postCount.year)"
          />
        </template>
        <ArchiveCard
          :yearCount="postCount.year"
          :monthCounts="postCount.monthCounts"
        >
        </ArchiveCard>
      </a-timeline-item>
    </a-timeline>
  </div>
</template>
<script>
import { defineComponent, ref } from "vue";
import { CalendarTwoTone } from "@ant-design/icons-vue";
import postRest from "@/api/postRest";
import ArchiveCard from "./ArchiveCard.vue";

export default defineComponent({
  setup() {
    const postCounts = ref([]);
    postRest.count().then((response) => {
      const counts = [];
      response.forEach((item) => {
        const postCount = {
          year: item.year,
        };
        const monthCounts = [];
        for (let i = 0; i < item.monthCounts.length; i++) {
          if (item.monthCounts[i] > 0) {
            const month = i < 9 ? "0" + (i + 1) : i + 1 + "";
            monthCounts.push({
              month: month,
              count: item.monthCounts[i],
            });
          }
        }
        const monthCountPairs = [];
        for (let i = 0; i < monthCounts.length; i += 2) {
          const pair = {};
          pair.left = monthCounts[i];
          pair.right = monthCounts[i + 1];
          if (i + 1 < monthCounts.length) {
            pair.right = monthCounts[i + 1];
          }
          monthCountPairs.push(pair);
        }
        postCount.monthCounts = monthCountPairs;
        counts.push(postCount);
      });
      postCounts.value = counts;
    });

    const getDotColor = (year) => {
      const remainder = year % 4;
      if (remainder === 0) {
        return "#389e0d";
      } else if (remainder === 1) {
        return "#fa541c";
      } else if (remainder === 2) {
        return "#08979c";
      } else {
        return "#096dd9";
      }
    };
    return { postCounts, getDotColor };
  },
  components: {
    CalendarTwoTone,
    ArchiveCard,
  },
});
</script>
<style scoped>
.archive > h3 {
  font-size: var(--journey-title-font-size);
}
</style>
