package models

import io.Source
import play.api.Play
import play.api.Play.current
/**
 * @author Marcin Burczak
 * @since 03.03.14
 */
object CommandRepo {

  val attacks70 = fromFile("conf/attacks70.txt", true)
  val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
  val attacks30 = fromFile("conf/attacks30.txt", false)
  val noAttacks30 = fromFile("conf/noAttacks30.txt", false)

  val trainingCommands = attacks70 ++  noAttacks70
  val testCommands = attacks30 ++  noAttacks30

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(Play.getFile(path)).getLines.map(Command(_, attack)).toSeq

}
