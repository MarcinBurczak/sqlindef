package models

import org.specs2.mutable.Specification
import io.Source

/**
 * @author Marcin Burczak
 * @since 02.03.14
 */
class ID3Spec extends Specification {

  "ID3" should {

    val attacks70 = fromFile("conf/attacks70.txt", true)
    val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
    val commands = attacks70 ++  noAttacks70

    "group commands by tokens count" in {
      ID3.decisionTrees(commands).size === 141
    }
  }

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(path).getLines.map(Command(_, attack)).toSeq

}
