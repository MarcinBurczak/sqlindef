package controllers

import play.api._
import play.api.mvc._
import models.Command
import io.Source
import play.api.Play.current

object Application extends Controller {

  val attacks70 = fromFile("conf/attacks70.txt", true)
  val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
  val attacks30 = fromFile("conf/attacks30.txt", false)
  val noAttacks30 = fromFile("conf/noAttacks30.txt", false)

  val trainingCommands = attacks70 ++  noAttacks70
  val testCommands = attacks30 ++  noAttacks30

  val trees = models.ID3.decisionTrees(trainingCommands)

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(Play.getFile(path)).getLines.map(Command(_, attack)).toSeq

  def index = Action {
    Ok(views.html.index(testCommands))
  }

  def tree(id: Int) = Action {
    if (trees.isDefinedAt(id)) Ok(trees(id).toXml)
    else Ok("Brak drzewa decyzyjnego o podanej liczbie tokenów")
  }
}