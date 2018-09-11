package by.overpass.poms1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn0 -> processInput("0")
            R.id.btn1 -> processInput("1")
            R.id.btn2 -> processInput("2")
            R.id.btn3 -> processInput("3")
            R.id.btn4 -> processInput("4")
            R.id.btn5 -> processInput("5")
            R.id.btn6 -> processInput("6")
            R.id.btn7 -> processInput("7")
            R.id.btn8 -> processInput("8")
            R.id.btn9 -> processInput("9")
            R.id.btnSqrt -> processInput("")
            R.id.btnPower -> processInput("0")
            R.id.btnFactorial -> processInput("0")
            R.id.btnClear -> processInput("0")
            R.id.btnPlus -> processInput("+")
            R.id.btnMinus -> processInput("-")
            R.id.btnMultiply -> processInput("0")
            R.id.btnDivide -> processInput("0")
            R.id.btnDot -> processInput("0")
            R.id.btnEquals -> processInput("0")
            R.id.btnLeftBracket -> processInput("0")
            R.id.btnRightBracket -> processInput("0")
            R.id.btnDelete -> processInput("0")
            R.id.btnLn -> processInput("0")
            R.id.btnSin -> processInput("0")
            R.id.btnCos -> processInput("0")
            R.id.btnTg -> processInput("0")
            R.id.btnCtg -> processInput("0")
        }
    }

    private fun processInput(input: String) {
        tvExpressionText.append(input)
    }
}
