package by.overpass.poms1.model

enum class Type(val code: Int) {

    ACTION_EQUALS(1),
    ACTION_DELETE(2),
    ACTION_CLEAR(3),
    ACTION_DEFAULT(4),
    OPERATOR(5),
    HELPER(6),
    NUMBER(7);

    fun isAction() = this.code <= 4

}