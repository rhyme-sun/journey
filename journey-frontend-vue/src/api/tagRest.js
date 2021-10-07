import request from "../utils/request";

export default {
  saveTag(tag) {
    if (tag.tagId) {
      return request.put("/tags", {
        headers: { "Content-Type": "application/json" },
        data: tag,
      });
    } else {
      return request.post("/tags", {
        headers: { "Content-Type": "application/json" },
        data: tag,
      });
    }
  },

  findAll() {
    return request.get("/tags");
  },

  findByParams(params) {
    return request.get("/tags", {
      params: params,
    });
  },
};
