package by.overpass.poms1.ui.main.activity

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import by.overpass.poms1.R
import by.overpass.poms1.model.ExpressionEvaluator
import by.overpass.poms1.model.Item
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity(), ExpressionEvaluator.ResultCallback {

    private val RESULT_KEY = "RESULT_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ExpressionEvaluator.setResultCallback(this)
        savedInstanceState?.let {
            tvExpressionText.text = savedInstanceState.getString(RESULT_KEY) ?: ""
        }
    }

    override fun onStop() {
        ExpressionEvaluator.setResultCallback(null)
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(RESULT_KEY, tvExpressionText.text.toString())
        super.onSaveInstanceState(outState)
    }

    fun onClick(view: View) {
        processInput(
                when (view.id) {
                    R.id.btn0 -> Item.ZERO
                    R.id.btn1 -> Item.ONE
                    R.id.btn2 -> Item.TWO
                    R.id.btn3 -> Item.THREE
                    R.id.btn4 -> Item.FOUR
                    R.id.btn5 -> Item.FIVE
                    R.id.btn6 -> Item.SIX
                    R.id.btn7 -> Item.SEVEN
                    R.id.btn8 -> Item.EIGHT
                    R.id.btn9 -> Item.NINE
                    R.id.btnSqrt -> Item.SQRT
                    R.id.btnPower -> Item.POWER
                    R.id.btnFactorial -> Item.FACTORIAL
                    R.id.btnClear -> Item.CLEAR
                    R.id.btnPlus -> Item.PLUS
                    R.id.btnMinus -> Item.MINUS
                    R.id.btnMultiply -> Item.MULTIPLY
                    R.id.btnDivide -> Item.DIVIDE
                    R.id.btnDot -> Item.DOT
                    R.id.btnEquals -> Item.EQUALS
                    R.id.btnLeftBracket -> Item.LEFT_BRACKET
                    R.id.btnRightBracket -> Item.RIGHT_BRACKET
                    R.id.btnDelete -> Item.DELETE
                    R.id.btnLg -> Item.LG
                    R.id.btnSin -> Item.SIN
                    R.id.btnCos -> Item.COS
                    R.id.btnTg -> Item.TG
                    R.id.btnCtg -> Item.CTG
                    else -> Item.DEFAULT
                }
        )
    }

    private fun processInput(item: Item) {
        if (ExpressionEvaluator.process(item)) {
            tvExpressionText.append(item.value)
        }
    }

    override fun onSuccess(bigDecimal: BigDecimal) {
        tvExpressionText.text = bigDecimal.toPlainString()
    }

    override fun onError(message: String) {
        tvExpressionText.apply {
            text = ""
            error = message
            postDelayed({ error = null }, 2500)
        }
    }

    override fun onClear() {
        tvExpressionText.text = ""
    }

    override fun onDelete(item: Item) {
        tvExpressionText.apply {
            if (text.length <= 1) {
                text = ""
            } else {
                val endIndex = text.length - item.value.length
                text = text.substring(0, endIndex)
            }
        }
    }
}
