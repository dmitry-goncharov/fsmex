package ru.dwerty.fsm.fsmex

import com.typesafe.scalalogging.LazyLogging
import ru.dwerty.fsm.fsmex.pipe.MainPipe

import scala.util.Random
import scala.util.chaining.scalaUtilChainingOps

object App extends LazyLogging {
  def main(args: Array[String]): Unit = {
    val mp = new MainPipe()

    try {
      Iterator
        .continually(Random.nextPrintableChar())
        .map(_.toString.toUpperCase)
        .pipe(mp.pipe)
        .foreach(s => logger.info(s))
    } catch {
      case t: Throwable => logger.error("Unexpected error", t)
    }
  }
}
