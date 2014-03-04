package models

/**
 * @author Marcin Burczak
 * @since 04.03.14
 */
trait TesterStrategy {

  def test(commands: Seq[Command]): (Double, Double) = {
    val attackCount = commands.count(_.attack)
    val noAttackCount = commands.size - attackCount

    val suspiciousCommand = commands.filter(test(_))
    val attackSuspiciousCount = suspiciousCommand.count(_.attack)
    val noAttackSuspiciousCount = suspiciousCommand.size - attackSuspiciousCount

    val falseNegative = (attackCount - attackSuspiciousCount) / attackCount.toDouble * 100
    val falsePositive = noAttackSuspiciousCount / noAttackCount.toDouble * 100
    (falseNegative, falsePositive)
  }

  def test(command: Command): Boolean
}
