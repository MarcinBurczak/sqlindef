package models

import com.google.common.math.DoubleMath

/**
 * @author Marcin Burczak
 * @since 01.03.14
 */
case class AttributeValue(value: Int,
                          yesCount: Int = 0,
                          noCount: Int = 0) {

  lazy val count = yesCount + noCount

  lazy val entropy =
    if (yesCount == 0 || yesCount == count) 0
    else {
      val a = yesCount / count.toDouble
      val b = (count - yesCount) / count.toDouble
      -a * DoubleMath.log2(a) -b * DoubleMath.log2(b)
    }

  val decision =
    if (yesCount >= noCount) Attack
    else NoAttack

  val isLeaf = yesCount == 0 || noCount == 0

  val toLeaf = Leaf(value, decision)
}
