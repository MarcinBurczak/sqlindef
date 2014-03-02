package models

/**
 * @author Marcin Burczak
 * @since 01.03.14
 */
object ID3 {

  def decisionTrees(commands: Seq[Command]) =
    commands.groupBy(_.tokensCount)

  def decisionTree(commands: (Int, Seq[Command])) = {

  }

  def commandsWithAttributeValue(commands: Seq[Command], index: Int, value: Int) =
    commands.filter(_.hasTokenWithValue(index, value))

  def attributeValues(commands: Seq[Command], index: Int) =
    commands.groupBy(_.tokens(index))
      .mapValues(c => c.span(_.attack))
      .map(kv => AttributeValue(kv._1, kv._2._1.size, kv._2._2.size))
      .toSeq

  def attributes(commands: Seq[Command], attributesNo: Set[Int]) =
    attributesNo.map(i => (i, Attribute(attributeValues(commands, i)))).toMap

  def attributeNoWithMinEntropy(attributes: Map[Int, Attribute]) =
    attributes.minBy(kv => kv._2.entropy)._1
}
