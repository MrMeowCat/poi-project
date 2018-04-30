package org.mrmeowcat.poibackend.util

import org.apache.commons.lang3.RandomStringUtils

/**
 * Utility methods for strings.
 */
object StringUtils {

    fun randomAlphanumeric(length: Int) : String {
        return RandomStringUtils.randomAlphanumeric(length)
    }

    fun isBlank(s: String?) : Boolean {
        s ?: return true
        return s.trim().isEmpty()
    }
}