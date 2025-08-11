package ru.dwerty.fsm.fsmex.pipe

import ru.dwerty.fsm.fsmex.core.Fsm.State
import ru.dwerty.fsm.fsmex.words.Words

case class WordMsg(data: String = "", state: State = Words.start)
