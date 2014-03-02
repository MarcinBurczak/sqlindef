package models

import org.specs2.mutable.Specification
import io.Source
import xml.Elem

/**
 * @author Marcin Burczak
 * @since 02.03.14
 */
class ID3Spec extends Specification {

  "ID3" should {

    "group commands by tokens count" in {
      val attacks70 = fromFile("conf/attacks70.txt", true)
      val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
      val commands = attacks70 ++  noAttacks70

      //ID3.groupByTokensCount(commands).size === 141
      ID3.decisionTrees(commands).foreach { p => println(p)}
    }
  }

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(path).getLines.map(Command(_, attack)).toSeq

}
