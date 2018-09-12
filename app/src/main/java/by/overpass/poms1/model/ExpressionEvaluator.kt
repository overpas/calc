package by.overpass.poms1.model

import by.overpass.poms1.util.runInBackground
import by.overpass.poms1.util.runOnUI
import com.udojava.evalex.Expression
import java.math.BigDecimal

object ExpressionEvaluator {

    private val expressionItems: MutableList<Item> = mutableListOf()

    fun process(item: Item, callback: ResultCallback): Boolean {
        if (expressionItems.isNotEmpty() && expressionItems.last() == Item.DOT && item == Item.DOT) {
            return false
        }
        if (!item.type.isAction()) {
            expressionItems.add(item)
        } else {
            processAction(item, callback)
        }
        return true
    }

    private fun processAction(item: Item, callback: ResultCallback) {
        when (item) {
            Item.EQUALS -> {
                calculate(callback)
            }
            Item.CLEAR -> {
                clearExpressionItems()
                callback.onClear()
            }
            Item.DELETE -> {
                var removedItem: Item? = null
                if (expressionItems.isNotEmpty()) {
                    removedItem = expressionItems.removeAt(expressionItems.size - 1)
                }
                if (removedItem != null) {
                    callback.onDelete(removedItem)
                }
            }
            else -> {
            }
        }
    }

    private fun calculate(callback: ResultCallback) {
        runInBackground {
            val expressionString = StringBuilder()
            expressionItems.forEach {
                expressionString.append(it.value)
            }
            try {
                val result = Expression(expressionString.toString()).eval()
                clearExpressionItems()
                expressionItems.add(Item(result.toPlainString(), Type.NUMBER))
                runOnUI { callback.onSuccess(result) }
            } catch (exception: Expression.ExpressionException) {
                clearExpressionItems()
                runOnUI { callback.onError(exception.localizedMessage) }
            } catch (exception: ArithmeticException) {
                clearExpressionItems()
                runOnUI { callback.onError(exception.localizedMessage) }
            } catch (exception: NumberFormatException) {
                clearExpressionItems()
                runOnUI { callback.onError("Invalid expression!") }
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
