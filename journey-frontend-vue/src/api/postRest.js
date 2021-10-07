import request from "../utils/request";

export default {
  getById(id) {
    return request.get(`/posts/${id}`);
  },

  page(current, pageSize, query) {
    return request.get("/posts", {
      params: {
        ...query,
        page: current,
        pageSize: pageSize,
      },
    });
  },

  pageByRoute(current, pageSize, { path, params }) {
    return request.get("/posts", {
      params: {
        ...this.buildQuery(path, params),
        page: current,
        pageSize: pageSize,
      },
    });
  },

  buildQuery(path, params) {
    const query = {};
    if (path.includes("categories")) {
      query.categoryId = params.id;
    }
    if (path.includes("tags")) {
      query.tagId = params.id;
    }
    const year = params.year;
    const month = params.month;
    if (year) {
      query.year = year;
      if (month) {
        query.month = month;
      }
    }
    return query;
  },

  count() {
    return request.get("/posts/counts");
  },

  save(post) {
    if (post.postId) {
      return request.put("/posts", {
        headers: { "Content-Type": "application/json" },
        data: post,
      });
    } else {
      return request.post("/posts", {
        headers: { "Content-Type": "application/json" },
        data: post,
      });
    }
  },

  upload(formData) {
    return request.post("/posts/upload", {
      data: formData,
    });
  },
};
