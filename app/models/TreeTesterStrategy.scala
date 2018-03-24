package models

/**
 * @author Marcin Burczak
 * @since 03.03.14
 */
case class TreeTesterStrategy(trees: Map[Int, Tree]) extends TesterStrategy {

  def test(command: Command): Boolean = {
    if (!trees.isDefinedAt(command.tokensCount)) false
    else {
      val tree = trees(command.tokensCount)
      predict(command, tree)
    }
  }

  def predict(command: Command, tree: Tree): Boolean = tree match {
    case l: Leaf => l.decision == Attack
    case n: Node => {
      val value = command.tokens(n.attribute)
      val node = n.nodes.find(_.value == value)
      if (node.isEmpty) false
      else predict(command, node.get)
    }
  }
}
