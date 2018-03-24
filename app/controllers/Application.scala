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
  val trainCommandsByCount = repo.trainingCommands.groupBy(_.tokensCount)
  val testCommandsByCount = repo.testCommands.groupBy(_.tokensCount)

  def index = Action {
    val commands = repo.testCommands.map(c => (c, treesTester.test(c), signaturesTester.test(c)))
    Ok(views.html.index(commands.take(100)))
  }

  def tree(id: Int) = Action {
    trees.get(id)
      .map(t => Ok(t.toXml))
      .getOrElse(NotFound)
  }

  def treesView = Action {
    val map = (1 to 100).map(i => {
      val testCommands = testCommandsByCount.get(i)
      val tree = trees.get(i)
      val pred = (for {
        commands <- testCommands
        t <- tree
      } yield {
        val goodPredicts = commands.map(c => (c.attack, treesTester.predict(c, t)))
          .count(p => p._1 == p._2).toDouble
        goodPredicts / commands.size
      }).getOrElse(0.0)
      (i, pred)
    })
    Ok(views.html.trees(map, trainCommandsByCount.mapValues(_.size), testCommandsByCount.mapValues(_.size)))
  }

  def summary = Action {
    val treeSummary = treesTester.test(repo.testCommands)
    val sigSummary = signaturesTester.test(repo.testCommands)
    Ok(views.html.summary(treeSummary, sigSummary))
  }
}