package com.example.mygausswithactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val matrix: Array<FloatArray> = arrayOf(floatArrayOf(2f, -1f, 3f, 2f),
                                                floatArrayOf(1f, -2f, 1f, 3f),
                                                floatArrayOf(3f, -1f, 3f, 4f))
        var xx: FloatArray = floatArrayOf(0f, 0f, 0f)

        showMatrix2d(matrix)

        xx = myGauss(matrix)

        showMatrix2d(matrix)

        showMatrix1d(xx)

    }
}

fun showMatrix1d(matrix: FloatArray) {
    var str1: String = ""
    for (i in matrix) {
        str1 = "$str1$i, "
    }
    Log.d("MyLog", str1)

}

fun showMatrix2d(matrix: Array<FloatArray>) {
    var str1: String = ""
    for (line in matrix) {
        for (i in line) {
            str1 = "$str1$i, "
        }
        Log.d("MyLog", str1)
        str1  = ""
    }
}

fun myGauss(matrix: Array<FloatArray>): FloatArray {
    val n: Int = 3
    val xx: FloatArray = FloatArray(n)
    var tmp: Float = 0f

    for (i in 0 until n) {
        tmp = matrix[i][i]
        for (j in n until i step -1) {
            matrix[i][j] /= tmp
            for (j in i+1 until n) {
                tmp = matrix[j][i]
                for (k in n until i step -1) {
                    matrix[j][k] -= tmp * matrix[i][k]
                }
            }
        }
    }
    xx[n-1] = matrix[n-1][n]
    for (i in n-2 until 0 step -1) {
        xx[i] = matrix[i][n]
        for (j in i+1 until n) {
            xx[i] -=matrix[i][j] * xx[j]
        }
    }
    return xx
}