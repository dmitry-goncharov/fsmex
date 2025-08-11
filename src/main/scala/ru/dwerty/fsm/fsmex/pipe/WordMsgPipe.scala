package ru.dwerty.fsm.fsmex.pipe

import ru.dwerty.fsm.fsmex.words.Words.Statuses.Stop
import ru.dwerty.fsm.fsmex.words.Words.fsm
import ru.dwerty.fsm.fsmex.words.{Word, WordsMapper}

class WordMsgPipe extends Pipe[LetterMsg, WordMsg] with WordsMapper {
  private var m = WordMsg()

  override def pipe(it: Iterator[LetterMsg]): Iterator[WordMsg] = {
    it
      .map(_.data)
      .map(Word)
      .map(w => (w.asEvent, w.data))
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
            m = WordMsg()
            true
          case _ =>
            m = msg
            false
        }
      }
  }
}
