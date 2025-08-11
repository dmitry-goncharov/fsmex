package ru.dwerty.fsm.fsmex.pipe

import scala.util.chaining.scalaUtilChainingOps

class MainPipe extends Pipe[String, String] {
  private val lmp = new LetterMsgPipe()
  private val wmp = new WordMsgPipe()

  override def pipe(it: Iterator[String]): Iterator[String] = {
    it
      .pipe(lmp.pipe)
      .pipe(wmp.pipe)
      .map(_.data)
  }
}
