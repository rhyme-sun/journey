import store from "../store/index.js";

export function getCurrentAuthority() {
  let auth = store.state.auth;
  return auth ? auth.authorities : ["guest"];
}

export function check(authority) {
  const current = getCurrentAuthority();
  return current.some((item) => authority.includes(item));
}

export function isLogin() {
  const current = getCurrentAuthority();
  return current && current[0] !== "guest";
}
