<template>
  <div class="post-card-container">
    <div class="post-create-time-box">
      <div class="post-create-year-month">
        {{ createYear }}-{{ createMonth }}
      </div>
      <div class="post-create-day">
        {{ createDay }}
      </div>
      <div class="post-create-time">{{ createHour }}:{{ createMinute }}</div>
    </div>
    <div class="post-content-abstract">
      <h3 @click="handleClickTitle(postId)">
        <a>
          {{ title }}
        </a>
      </h3>
      <p>{{ contentAbstract }}</p>
      <a-tag
        class="post-tag"
        v-for="tag in tags"
        :key="tag.tagId"
        :color="getTagColor(tag)"
        @click="handleClickTag(tag.tagId)"
      >
        <a> {{ tag.tagName }} </a>
      </a-tag>
    </div>
  </div>
  <a-divider style="height: 2px; background-color: #f0f0f0" />
</template>
<script>
import { defineComponent, computed } from "vue";
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import moment from "moment";

export default defineComponent({
  props: {
    postId: {
      type: String,
    },
    title: {
      type: String,
    },
    contentAbstract: {
      type: String,
    },
    createTime: {
      type: String,
    },
    tagIds: {
      type: Array,
    },
  },
  setup(props) {
    const router = useRouter();
    const handleClickTitle = (postId) => {
      router.push(`/journey/posts/${postId}`);
    };

    const createTime = moment(props.createTime);
    const createYear = computed(() => {
      return createTime.year();
    });
    const createMonth = computed(() => {
      return createTime.month() + 1;
    });
    const createDay = computed(() => {
      return createTime.date();
    });
    const createHour = computed(() => {
      return createTime.hour();
    });
    const createMinute = computed(() => {
      return createTime.minute();
    });

    const store = useStore();
    const tags = [];
    props.tagIds.forEach((tagId) => {
      const tag = store.getters["tag/getTag"](tagId);
      if (tag && tag.tagName) {
        tags.push(tag);
      }
    });
    const handleClickTag = (tagId) => {
      router.push(`/journey/tags/${tagId}/posts`);
    };
    const getTagColor = (tag) => {
      if (tag.isGroup) {
        return "#1890ff";
      } else {
        return "#ffa940";
      }
    };

    return {
      handleClickTitle,
      createYear,
      createMonth,
      createDay,
      createHour,
      createMinute,
      tags,
      handleClickTag,
      getTagColor,
    };
  },
});
</script>

<style>
.post-card-container {
  display: flex;
  width: 100%;
  min-height: 15vh;
  --primary-card-bg-color: #434343;
}
.post-create-time-box {
  width: 80px;
  margin-right: 30px;
  margin-top: 6px;
  flex: 1.5;
}
.post-create-year-month {
  background-color: var(--primary-card-bg-color);
  padding: 1px 5px;
  text-align: center;
  color: white;
  border-radius: 3px 3px 0px 0px;
}
.post-create-day {
  height: 63px;
  border: solid 1px var(--primary-card-bg-color);
  color: var(--primary-card-bg-color);
  text-align: center;
  padding: 5px;
  font-size: 30px;
  font-weight: 700;
}
.post-create-time {
  border: solid 1px var(--primary-card-bg-color);
  text-align: center;
  padding: 1px 5px;
  color: white;
  background: var(--primary-card-bg-color);
  border-radius: 0px 0px 3px 3px;
}
.post-content-abstract {
  flex: 13;
}
.post-tag {
  cursor: pointer;
}
.post-tag > a {
  font-size: 15px;
}
</style>
