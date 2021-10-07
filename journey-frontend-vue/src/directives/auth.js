import { check } from "../utils/auth";

function install(app, options = {}) {
  app.directive(options.name || "auth", {
    mounted(el, binding) {
      if (!check(binding.value)) {
        el.parentNode && el.parentNode.removeChild(el);
      }
    },
  });
}

export default { install };
