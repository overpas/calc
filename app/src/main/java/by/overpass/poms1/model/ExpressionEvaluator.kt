package by.overpass.poms1.model

import by.overpass.poms1.util.runInBackground
import by.overpass.poms1.util.runOnUI
import com.udojava.evalex.Expression
import java.math.BigDecimal

object ExpressionEvaluator {

    private var resultCallback: ResultCallback? = null
    private val expressionItems: MutableList<Item> = mutableListOf()

    fun setResultCallback(resultCallback: ResultCallback?) {
        this.resultCallback = resultCallback
    }

    /**
     * @return true if the item value has to be appended to the displaying view
     */
    fun process(item: Item): Boolean {
        if (expressionItems.isNotEmpty() && expressionItems.last() == Item.DOT && item == Item.DOT) {
            return false
        }
        if (!item.type.isAction()) {
            expressionItems.add(item)
        } else {
            processAction(item)
        }
        return true
    }

    private fun processAction(item: Item) {
        when (item) {
            Item.EQUALS -> {
                calculate()
            }
            Item.CLEAR -> {
                clearExpressionItems()
                resultCallback?.onClear()
            }
            Item.DELETE -> {
                var removedItem: Item? = null
                if (expressionItems.isNotEmpty()) {
                    removedItem = expressionItems.removeAt(expressionItems.size - 1)
                }
                if (removedItem != null) {
                    resultCallback?.onDelete(removedItem)
                }
            }
            else -> {
            }
        }
    }

    private fun calculate() {
        runInBackground {
            val expressionString = StringBuilder()
            expressionItems.forEach {
                expressionString.append(it.value)
            }
            try {
                val result = Expression(expressionString.toString()).eval()
                clearExpressionItems()
                expressionItems.add(Item(result.toPlainString(), Type.NUMBER))
                runOnUI { resultCallback?.onSuccess(result) }
            } catch (exception: Expression.ExpressionException) {
                clearExpressionItems()
                runOnUI { resultCallback?.onError(exception.localizedMessage) }
            } catch (exception: ArithmeticException) {
                clearExpressionItems()
                runOnUI { resultCallback?.onError(exception.localizedMessage) }
            } catch (exception: NumberFormatException) {
                clearExpressionItems()
                runOnUI { resultCallback?.onError("Invalid expression!") }
            }
        }
    }

    private fun clearExpressionItems() {
        expressionItems.clear()
    }

    interface ResultCallback {
        fun onSuccess(bigDecimal: BigDecimal)
        fun onError(message: String)
        fun onClear()
        fun onDelete(item: Item)
    }

}
