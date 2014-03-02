package models

case class Command(sql: String, attack: Boolean) {

  lazy val tokens = sql.split(' ')
    .map(_.trim)
    .filterNot(_.isEmpty)
    .map(Sql.lexems)
    .toList

  lazy val tokensCount = tokens.size

  def hasTokenWithValue(index: Int, value: Int) =
    tokens.isDefinedAt(index) && tokens(index) == value
}