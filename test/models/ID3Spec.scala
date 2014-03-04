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
    val attacks30 = fromFile("conf/attacks30.txt", true)
    val noAttacks30 = fromFile("conf/noAttacks30.txt", false)
    val signatures = fromFile("conf/signatures.txt", false)
    val commands = attacks70 ++  noAttacks70
    val commands2 = attacks30 ++  noAttacks30

    "group commands by tokens count" in {
      val trees: Map[Int, Tree] = ID3.decisionTrees(commands)

      println(TreeTesterStrategy(trees).test(commands2))
      trees.size === 141
    }

    "signature" in {
      println(SignatureTesterStrategy(signatures).test(commands ++ commands2))

      List(1, 3, 5, 6, 3, 2, 3).containsSlice(List(3, 5, 6, 3)) === true
    }
  }

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(path).getLines.map(Command(_, attack)).toSeq

}
