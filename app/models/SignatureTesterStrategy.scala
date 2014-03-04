package models

/**
 * @author Marcin Burczak
 * @since 04.03.14
 */
case class SignatureTesterStrategy(signatureCommands: Seq[Command]) extends TesterStrategy {
  val whereLexem = 2

  val signaturesTokens =
    for {
      tokens <- signatureCommands.map(_.tokens)
      if (tokens.contains(whereLexem))
    } yield tokens.drop(tokens.indexOf(whereLexem))

  def test(command: Command): Boolean =
    signaturesTokens.exists(command.tokens.containsSlice(_))
}
