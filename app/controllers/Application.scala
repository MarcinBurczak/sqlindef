package controllers

import play.api._
import play.api.mvc._
import models.{Node, Leaf, Tree, Command}
import io.Source
import play.api.Play.current
import xml.Elem

object Application extends Controller {

  val attacks70 = fromFile("conf/attacks70.txt", true)
  val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
  val commands = attacks70 ++  noAttacks70
  val trees = models.ID3.decisionTrees(commands)

  def toXml(tree: Tree): Elem = tree match {
    case Leaf(v, d) => <leaf wartosc={v.toString} decision={d.toString}/>
    case Node(a, v, at) => <node wartosc={v.toString} atrybut={a.toString}>{at.map(toXml(_))}</node>
  }

  def index = Action {
    val attacks70 = fromFile("conf/attacks70.txt", true)
    val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
    val commands = attacks70 ++  noAttacks70

    Ok(views.html.index(attacks70))
  }

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(Play.getFile(path)).getLines.map(Command(_, attack)).toSeq

  def tree(id: Int) = Action {
    Ok(toXml(trees(id)))
  }
}