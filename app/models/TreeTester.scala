package models

/**
 * @author Marcin Burczak
 * @since 03.03.14
 */
object TreeTester {

  def test(command: Command, trees: Map[Int, Tree]): Command = {
    if (!trees.isDefinedAt(command.tokensCount)) command
    else {
      val tree = trees(command.tokensCount)
      parse(command, tree)
    }
  }

  def parse(command: Command, tree: Tree): Command = tree match {
    case l: Leaf => command.copy(attack = l.decision == Attack)
    case n: Node => {
      val value = command.tokens(n.attribute)
      val node = n.nodes.find(_.value == value)
      if (node.isEmpty) command
      else parse(command, node.get)
    }
  }
}
