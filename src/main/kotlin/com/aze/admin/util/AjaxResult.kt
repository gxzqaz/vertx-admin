package com.aze.admin.util

import java.io.Serializable

data class AjaxResult constructor(var c: String? = "0", var m: String? = "", var d: Any? = Any()) : Serializable {

  companion object {
    private const val serialVersionUID = 1L

    fun ok(any: Any?): AjaxResult {
      return AjaxResult(c = "0", d = any)
    }

    fun error(m: String?): AjaxResult {
      return AjaxResult(c = "-1", m = m)
    }

    fun error(c: String, m: String?): AjaxResult {
      return AjaxResult(c = c, m = m)
    }

    /**
     * 作为提示用
     */
    fun confirm(m: String?): AjaxResult {
      return AjaxResult(c = "1", m = m)
    }
  }
}
