package controllers

import play.api._
import play.api.mvc._
import models.Command
import io.Source
import play.api.Play.current

object Application extends Controller {

  def index = Action {
    val lines = Source.fromFile(Play.getFile("conf/ataki_30.txt")).getLines.map(Command(_, true)).toSeq
    Ok(views.html.index(lines))
  }
}