package models

/**
 * @author Marcin Burczak
 * @since 01.03.14
 */
case class Attribute(values: Seq[AttributeValue]) {

  lazy val valuesCount = values.foldLeft(0)(_ + _.count)

  lazy val entropy =
    if (valuesCount == 0) 0.0
    else values.map(at => at.count / valuesCount.toDouble * at.entropy).sum
}
