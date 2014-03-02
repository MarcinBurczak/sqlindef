package models

import org.specs2.mutable.Specification

/**
 * @author Marcin Burczak
 * @since 02.03.14
 */
class AttributeSpec extends Specification {

  "Attribute" should {

    "calculate entropy" in {
      val values = Seq(AttributeValue(10, 1, 0), AttributeValue(11, 1, 1), AttributeValue(14, 1, 2))
      val attribute = Attribute(values)
      attribute.entropy must beCloseTo(.792, .001)
    }

    "calculate entopy with zero yes/no decision" in {
      val values = Seq(AttributeValue(10), AttributeValue(11), AttributeValue(14))
      val attribute = Attribute(values)
      attribute.entropy must beCloseTo(.0, .001)
    }
  }
}
