package models

/**
 * @author Marcin Burczak
 * @since 02.03.14
 */
sealed trait Tree
case class Leaf(value: Int, decision: Decision) extends Tree
case class Node(attribute: Int, value: Int, nodes: Seq[Tree]) extends Tree

sealed trait Decision
case object Attack extends Decision
case object NoAttack extends Decision