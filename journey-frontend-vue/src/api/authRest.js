import request from "../utils/request";

export default {
  login(username, password) {
    return request.post("/login", {
      params: {
        username: username,
        password: password,
      },
    });
  },
  logout() {
    return request.post("/logout");
  },
};
