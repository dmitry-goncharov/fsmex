package ru.dwerty.fsm.fsmex.letters

import ru.dwerty.fsm.fsmex.core.Fsm.Event
import ru.dwerty.fsm.fsmex.letters.Letters.Events

trait LetterMapper {
  implicit class LetterWrapper(letter: Letter) {
    def asEvent: Event = letter.data match {
      case "A" => Events.A
      case "B" => Events.B
      case "C" => Events.C
      case "D" => Events.D
      case "E" => Events.E
      case "F" => Events.F
      case "G" => Events.G
      case "H" => Events.H
      case "I" => Events.I
      case "J" => Events.J
      case "K" => Events.K
      case "L" => Events.L
      case "M" => Events.M
      case "N" => Events.N
      case "O" => Events.O
      case "P" => Events.P
      case "Q" => Events.Q
      case "R" => Events.R
      case "S" => Events.S
      case "T" => Events.T
      case "U" => Events.U
      case "V" => Events.V
      case "W" => Events.W
      case "X" => Events.X
      case "Y" => Events.Y
      case "Z" => Events.Z
      case _ => Events.Unknown
    }
  }
}
