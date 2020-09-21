package zsy.utils

import java.lang.reflect.Array as JArray

object ArrayUtils {


    @JvmStatic
    fun main(args: Array<String>) {
        val arr1 = arrayOf("11", "21")
        val arr2 = arrayOf("21", "22")
        val arr3 = concat(arr1, arr2)
        println(arr3)
    }


    inline fun <reified T> concat(vararg src: Array<T>): Array<T> {
        if (src.size == 1) {
            return src[0]
        }
        var totalLen = 0
        for (e in src) {
            totalLen += e.size
        }
        val des =
            JArray.newInstance(T::class.java, totalLen) as Array<T>//(T[]) new Object[totalLen];
        var currentLen = 0
        for (arr in src) {
            System.arraycopy(arr, 0, des, currentLen, arr.size)
            currentLen += arr.size
        }
        return des
    }

}
