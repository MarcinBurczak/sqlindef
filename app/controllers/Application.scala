package controllers

import play.api.mvc._
import models._
import models.SignatureTesterStrategy
import models.TreeTesterStrategy

object Application extends Controller {

  val trees = ID3.decisionTrees(CommandRepo.trainingCommands)
  val treesTester = TreeTesterStrategy(trees)
  val signaturesTester = SignatureTesterStrategy(CommandRepo.signatures)

  def index = Action {
    val commands = CommandRepo.testCommands.map(c => (c, treesTester.test(c), signaturesTester.test(c)))
    Ok(views.html.index(commands.take(100)))
  }

  def tree(id: Int) = Action {
    trees.get(id)
      .map(t => Ok(t.toXml))
      .getOrElse(NotFound)
  }
}