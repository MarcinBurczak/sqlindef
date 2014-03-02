package models

import org.specs2.mutable.Specification

/**
 * @author Marcin Burczak
 * @since 02.03.14
 */
class CommandSpec extends Specification {

  "Command" should {

    "split sql to tokens" in {
      val command = Command("SELECT a FROM aaa", false)
      command.tokens === List(0, -1, 1, -1)
    }

    "split sql to tokens and trim" in {
      val command: Command = Command("SELECT    a FROM  aaa", false)
      command.tokens === List(0, -1, 1, -1)
    }

    "has token with value" in {
      val command: Command = Command("SELECT a FROM aaa", false)
      command.hasTokenWithValue(1, -1) === true
    }

    "hasn't token when index is out off bound" in {
      val command: Command = Command("SELECT a FROM aaa", false)
      command.hasTokenWithValue(6, 1) === false
    }

    "hasn't token with value" in {
      val command: Command = Command("SELECT a FROM aaa", false)
      command.hasTokenWithValue(1, 1) === false
    }
  }
}
