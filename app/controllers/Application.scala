package controllers

import javax.inject._
import play.api.mvc._
import models._
import models.SignatureTesterStrategy
import models.TreeTesterStrategy
import play.api.Environment

@Singleton
class Application @Inject()(cc: ControllerComponents, env: Environment) extends AbstractController(cc) {

  private val repo = new CommandRepo(env)

  val trees = ID3.decisionTrees(repo.trainingCommands)
  val treesTester = TreeTesterStrategy(trees)
  val signaturesTester = SignatureTesterStrategy(repo.signatures)

  def index = Action {
    val commands = repo.testCommands.map(c => (c, treesTester.test(c), signaturesTester.test(c)))
    Ok(views.html.index(commands.take(100)))
  }

  def tree(id: Int) = Action {
    trees.get(id)
      .map(t => Ok(t.toXml))
      .getOrElse(NotFound)
  }
}