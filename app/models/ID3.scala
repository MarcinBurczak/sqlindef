package models

/**
 * @author Marcin Burczak
 * @since 01.03.14
 */
object ID3 {

  def decisionTrees(commands: Seq[Command]) =
    groupByTokensCount(commands).map(p => (p._1, decisionTreeRoot(p)))

  def groupByTokensCount(commands: Seq[Command]) =
    commands.groupBy(_.tokensCount)

  def decisionTreeRoot(commands: (Int, Seq[Command])) =
    decisionTree(commands._2, attributesSet(commands._1), -1)

  def attributesSet(index: Int) =
    (0 until index).toSet

  def decisionTree(commands: Seq[Command], attributesSet: Set[Int], value: Integer): Tree = {
    val att = attributes(commands, attributesSet)
    val minEntropyAtt = attributeNoWithMinEntropy(att)
    if (minEntropyAtt.isEmpty) Leaf(value, Attack)
    else {
      val index = minEntropyAtt.get
      val attribute = att(index)
      decisionTreeForAttributeWithMinEntropy(index, attribute, value, attributesSet, commands)
    }
  }

  def decisionTreeForAttributeWithMinEntropy(index: Int, attribute: Attribute, value: Integer, attributesNo: Set[Int], commands: Seq[Command]): Tree = {
    val decisions = attribute.values.groupBy(_.decision)
    if (decisions.size == 1) Leaf(value, decisions.head._1)
    else {
      val nodes = attribute.values.map(toNodes(_, attributesNo, index, commands))
      Node(index, value, nodes)
    }
  }

  def toNodes(at: AttributeValue, attributesSet: Set[Int], index: Int, commands: Seq[Command]): Tree = {
    if (at.isLeaf) at.toLeaf
    else toNodes2(attributesSet, index, at, commands)
  }

  def toNodes2(attributesSet: Set[Int], index: Int, at: AttributeValue, commands: Seq[Command]): Tree = {
    val newAttributesSet = attributesSet - index
    if (newAttributesSet.isEmpty) at.toLeaf
    else {
      val newCommands = commandsWithAttributeValue(commands, index, at.value)
      decisionTree(newCommands, newAttributesSet, at.value)
    }
  }

  def commandsWithAttributeValue(commands: Seq[Command], index: Int, value: Int) =
    commands.filter(_.hasTokenWithValue(index, value))

  def attributes(commands: Seq[Command], attributesSet: Set[Int]) =
    attributesSet.map(i => (i, Attribute(attributeValues(commands, i)))).toMap

  def attributeValues(commands: Seq[Command], index: Int) =
    commands.groupBy(_.tokens(index))
      .mapValues(_.span(_.attack))
      .map(kv => AttributeValue(kv._1, kv._2._1.size, kv._2._2.size))
      .toSeq

  def attributeNoWithMinEntropy(attributes: Map[Int, Attribute]) = {
    val attNo = attributes.minBy(kv => kv._2.entropy)._1
    if (attributes(attNo).entropy >= 1) None
    else Some(attNo)
  }

}
