package models

import play.api.Environment
import scala.io.Source

/**
 * @author Marcin Burczak
 * @since 03.03.14
 */
class CommandRepo(env: Environment) {

  private val data = "data/"
  val attacks70 = fromFile("attacks70.txt", attack = true)
  val noAttacks70 = fromFile("noAttacks70.txt", attack = false)
  val attacks30 = fromFile("attacks30.txt", attack = true)
  val noAttacks30 = fromFile("noAttacks30.txt", attack = false)
  val signatures = fromFile("signatures.txt", attack = false)

  val trainingCommands = attacks70 ++  noAttacks70
  val testCommands = attacks30 ++  noAttacks30

  def fromFile(path: String, attack: Boolean) =
    Source.fromInputStream(env.classLoader.getResourceAsStream(data + path))
      .getLines.map(Command(_, attack)).toSeq

}
