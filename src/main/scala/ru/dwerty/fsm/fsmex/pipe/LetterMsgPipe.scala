package ru.dwerty.fsm.fsmex.pipe

import ru.dwerty.fsm.fsmex.letters.Letters.Statuses.Stop
import ru.dwerty.fsm.fsmex.letters.Letters.fsm
import ru.dwerty.fsm.fsmex.letters.{Letter, LetterMapper}

class LetterMsgPipe extends Pipe[String, LetterMsg] with LetterMapper {
  private var m = LetterMsg()

  override def pipe(it: Iterator[String]): Iterator[LetterMsg] = {
    it
      .map(Letter)
      .map(l => (l.asEvent, l.data))
      .map { case (evt, data) =>
        val oldState = m.state
        val newState = oldState ! evt
        if (oldState == newState) m
        else if (newState.status == Stop) m.copy(m.data, newState)
        else m.copy(m.data + data, newState)
      }
      .filter { msg =>
        msg.state.status match {
          case Stop =>
            m = LetterMsg()
            true
          case _ =>
            m = msg
            false
        }
      }
  }
}
