<script>
import { check } from "../utils/auth";
export default {
  props: {
    authority: {
      type: Array,
      required: true,
    },
  },

  render(context) {
    const { $props, $slots } = context;

    return check($props.authority) ? $slots.default() : null;
  },
};
</script>
