package controllers

import play.api.mvc._
import models.{ID3, CommandRepo}

object Application extends Controller {

  val trees = ID3.decisionTrees(CommandRepo.trainingCommands)

  def index = Action {
    Ok(views.html.index(CommandRepo.testCommands))
  }

  def tree(id: Int) = Action {
    if (trees.isDefinedAt(id)) Ok(trees(id).toXml)
    else Ok("Brak drzewa decyzyjnego o podanej liczbie tokenów")
  }
}