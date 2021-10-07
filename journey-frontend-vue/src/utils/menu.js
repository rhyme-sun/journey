import { check } from "../utils/auth";
import { last } from "lodash";

export function createHomeMenuData(routes = []) {
  return createMenuData(routes, 0);
}

export function createManagementSideMenuData(routes = []) {
  const menuData = [];
  if (routes.length === 0) {
    return menuData;
  }
  const management = last(routes[0].children);
  for (let item of management.children) {
    if (item.meta && !item.meta.hideInMenu) {
      const newItem = { ...item };
      delete newItem.children;
      delete newItem.component;
      menuData.push(newItem);
    }
  }
  return menuData;
}

function createMenuData(routes = [], index) {
  const menuData = [];
  let route = routes[index];
  for (let item of route.children) {
    if (item.meta && item.meta.authority && !check(item.meta.authority)) {
      continue;
    }
    if (item.meta && !item.meta.hideInMenu) {
      const newItem = { ...item };
      delete newItem.children;
      delete newItem.component;
      menuData.push(newItem);
    }
  }
  return menuData;
}
