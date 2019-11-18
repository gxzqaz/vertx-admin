package com.aze.admin.util

/**
 * 让get请求里面的空字符串为null
 */

fun emptyStrToNull(str: String?): String? = if (str == null || str.isEmpty()) null else str


fun isEmpty(list: List<Any>?): Boolean {
  return list == null || list.isEmpty();
}

fun isEmpty(str: String?): Boolean {
  return str == null || str.isEmpty();
}

fun isNotEmpty(str: String?): Boolean {
  return !isEmpty(str);
}

fun isNotEmpty(list: List<Any>?): Boolean {
  return !isEmpty(list)
}
