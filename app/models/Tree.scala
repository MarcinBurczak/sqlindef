package models

import xml.Elem

/**
 * @author Marcin Burczak
 * @since 02.03.14
 */
sealed trait Tree {
  val value: Int
  val toXml: Elem
}
case class Leaf(value: Int, decision: Decision) extends Tree {
  val toXml = <leaf wartosc={value.toString} decision={decision.toString}/>
}
case class Node(attribute: Int, value: Int, nodes: Seq[Tree]) extends Tree{
  val toXml = <node wartosc={value.toString} atrybut={attribute.toString}>{nodes.map(_.toXml)}</node>
}

sealed trait Decision
case object Attack extends Decision
case object NoAttack extends Decision