package ru.dwerty.fsm.fsmex.pipe

import ru.dwerty.fsm.fsmex.core.Fsm.State
import ru.dwerty.fsm.fsmex.letters.Letters

case class LetterMsg(data: String = "", state: State = Letters.start)
