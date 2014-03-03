package models

/**
 * @author Marcin Burczak
 * @since 01.03.14
 */
object Sql {
  val OPERATOR       = "=|!=|>=|<=|>|<|\\(|\\,"
  val NUMBER         = "\\d*"
  val IDENTYFIKATOR1 = "a*"

  val lexems = Map(
    "SELECT" -> 0,
    "FROM" -> 1,
    "WHERE" -> 2,
    "DELETE" -> 3,
    "VALUES" -> 4,
    "AND" -> 5,
    "LIMIT" -> 6,
    "OR" -> 7,
    "UPDATE" -> 8,
    "SET" -> 9,
    "DESC" -> 10,
    "LIKE" -> 11,
    "COUNT" -> 12,
    "AS" -> 13,
    "UPPER" -> 14,
    "IN" -> 15,
    "SUM" -> 16,
    "LEFT" -> 17,
    "ON" -> 18,
    "GROUP" -> 19,
    "BY" -> 20,
    "MAX" -> 21,
    "MIN" -> 22,
    "ORDER" -> 23,
    "ASC" -> 24,
    "INSERT" -> 25,
    "BETWEEN" -> 26,
    "JOIN" -> 27,
    "NATURAL" -> 28,
    "RIGHT" -> 29,
    "UNION" -> 30,
    "HAVING" -> 31,
    "INTO" -> 32,
    IDENTYFIKATOR1 -> 33,
    NUMBER -> 34,
    "=" -> 35,
    "!=" -> 36,
    ">=" -> 37,
    "<=" -> 38,
    ">" -> 39,
    "<" -> 40,
    "(" -> 41,
    ")" -> 42)
  .withDefaultValue(-1)
}
