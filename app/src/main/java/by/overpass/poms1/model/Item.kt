package by.overpass.poms1.model

data class Item(val value: String, val type: Type) {

    companion object {
        // Operators
        val PLUS = Item("+", Type.OPERATOR)
        val MINUS = Item("-", Type.OPERATOR)
        val MULTIPLY = Item("*", Type.OPERATOR)
        val DIVIDE = Item("/", Type.OPERATOR)
        val SQRT = Item("SQRT(", Type.OPERATOR)
        val POWER = Item("^(", Type.OPERATOR)
        val FACTORIAL = Item("FACT(", Type.OPERATOR)
        val LG = Item("log10(", Type.OPERATOR)
        val SIN = Item("SIN(", Type.OPERATOR)
        val COS = Item("COS(", Type.OPERATOR)
        val TG = Item("TAN(", Type.OPERATOR)
        val CTG = Item("COT(", Type.OPERATOR)

        // Actions
        val EQUALS = Item("", Type.ACTION_EQUALS)
        val DELETE = Item("", Type.ACTION_DELETE)
        val CLEAR = Item("", Type.ACTION_CLEAR)
        val DEFAULT = Item("", Type.ACTION_DEFAULT)

        // Helpers
        val DOT = Item(".", Type.HELPER)
        val LEFT_BRACKET = Item("(", Type.HELPER)
        val RIGHT_BRACKET = Item(")", Type.HELPER)

        // Numbers
        val ZERO = Item("0", Type.NUMBER)
        val ONE = Item("1", Type.NUMBER)
        val TWO = Item("2", Type.NUMBER)
        val THREE = Item("3", Type.NUMBER)
        val FOUR = Item("4", Type.NUMBER)
        val FIVE = Item("5", Type.NUMBER)
        val SIX = Item("6", Type.NUMBER)
        val SEVEN = Item("7", Type.NUMBER)
        val EIGHT = Item("8", Type.NUMBER)
        val NINE = Item("9", Type.NUMBER)
    }

}