import request from "../utils/request";

export default {
  saveCategory(category) {
    if (category.categoryId) {
      return request.put("/categories", {
        headers: { "Content-Type": "application/json" },
        data: category,
      });
    } else {
      return request.post("/categories", {
        headers: { "Content-Type": "application/json" },
        data: category,
      });
    }
  },
  findAll() {
    return request.get("/categories");
  },
};
