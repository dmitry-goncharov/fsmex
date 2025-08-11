package ru.dwerty.fsm.fsmex.words

import ru.dwerty.fsm.fsmex.core.Fsm.Event
import ru.dwerty.fsm.fsmex.words.Words.Events

trait WordsMapper {
  implicit class WordsWrapper(word: Word) {
    def asEvent: Event = word.data match {
      case "FINITE" => Events.Finite
      case "STATE" => Events.State
      case "MACHINE" => Events.Machine
      case _ => Events.Unknown
    }
  }
}
