<template>
  <div class="archive-card">
    <a-card>
      <template v-slot:title>
        <span class="archive-card-year" @click="handleCilckYear(yearCount)">
          {{ yearCount }}
        </span>
      </template>
      <a-row v-for="item in monthCounts" :key="item.left.month">
        <a-col :span="10">
          <div
            class="archive-card-month"
            @click="handleClickMonth(yearCount, item.left.month)"
          >
            <span :style="archiveCardMonthLeftStyle"
              >{{ item.left.month }} 月</span
            >
            <span :style="archiveCardMonthRightStyle">{{
              item.left.count
            }}</span>
          </div>
        </a-col>
        <a-col :span="4"> </a-col>
        <a-col :span="10">
          <div
            class="archive-card-month"
            v-if="item.right"
            @click="handleClickMonth(yearCount, item.right.month)"
          >
            <span :style="archiveCardMonthLeftStyle"
              >{{ item.right.month }} 月</span
            >
            <span :style="archiveCardMonthRightStyle">{{
              item.right?.count
            }}</span>
          </div>
        </a-col>
      </a-row>
    </a-card>
  </div>
</template>

<script>
import { defineComponent, computed } from "vue";
import { useRouter } from "vue-router";

export default defineComponent({
  props: {
    yearCount: {
      type: Number,
    },
    monthCounts: {
      type: Array,
    },
  },
  setup(props) {
    const router = useRouter();
    const handleCilckYear = (year) => {
      router.push(`/journey/archives/${year}`);
    };

    const handleClickMonth = (year, month) => {
      router.push(`/journey/archives/${year}/${month}`);
    };

    const archiveCardMonthLeftStyle = computed(() => {
      return {
        float: "left",
      };
    });

    const archiveCardMonthRightStyle = computed(() => {
      return {
        float: "right",
        background: getCountBackgroundColor(),
      };
    });

    const getCountBackgroundColor = () => {
      const remainder = props.yearCount % 4;
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

    return {
      handleCilckYear,
      handleClickMonth,
      archiveCardMonthLeftStyle,
      archiveCardMonthRightStyle,
    };
  },
});
</script>

<style>
.archive-card {
  width: 400px;
  border-radius: 4px;
  box-shadow: 0 1px 6px #0000002d;
  margin: 0px 20px;
}
.archive-card-year {
  font-size: 20px;
  color: #10239e;
  cursor: pointer;
}
.archive-card-month {
  font-size: 15px;
  color: #10239e;
  cursor: pointer;
  margin-bottom: 27px;
}
.archive-card-month > span:last-child {
  width: 24px;
  height: 22px;
  color: #fff;
  /* background: #8c8c8c; */
  text-align: center;
  border-radius: 6px;
}
</style>
