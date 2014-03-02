package controllers

import play.api._
import play.api.mvc._
import models.Command
import io.Source
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    val attacks70 = fromFile("conf/attacks70.txt", true)
    val noAttacks70 = fromFile("conf/noAttacks70.txt", false)
    val commands = attacks70 ++  noAttacks70

    Ok(views.html.index(attacks70))
  }

  def fromFile(path: String, attack: Boolean) =
    Source.fromFile(Play.getFile(path)).getLines.map(Command(_, attack)).toSeq

}