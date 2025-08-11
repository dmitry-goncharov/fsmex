package ru.dwerty.fsm.fsmex.letters

import ru.dwerty.fsm.fsmex.core.Fsm
import ru.dwerty.fsm.fsmex.core.Fsm.{Event, FsmException, State, Status}

object Letters {
  object Statuses {
    case object Start extends Status

    case object Stop extends Status

    case object A extends Status

    case object B extends Status

    case object C extends Status

    case object D extends Status

    case object E extends Status

    case object F extends Status

    case object G extends Status

    case object H extends Status

    case object I extends Status

    case object J extends Status

    case object K extends Status

    case object L extends Status

    case object M extends Status

    case object N extends Status

    case object O extends Status

    case object P extends Status

    case object Q extends Status

    case object R extends Status

    case object S extends Status

    case object T extends Status

    case object U extends Status

    case object V extends Status

    case object W extends Status

    case object X extends Status

    case object Y extends Status

    case object Z extends Status
  }

  object Events {
    case object Unknown extends Event

    case object A extends Event

    case object B extends Event

    case object C extends Event

    case object D extends Event

    case object E extends Event

    case object F extends Event

    case object G extends Event

    case object H extends Event

    case object I extends Event

    case object J extends Event

    case object K extends Event

    case object L extends Event

    case object M extends Event

    case object N extends Event

    case object O extends Event

    case object P extends Event

    case object Q extends Event

    case object R extends Event

    case object S extends Event

    case object T extends Event

    case object U extends Event

    case object V extends Event

    case object W extends Event

    case object X extends Event

    case object Y extends Event

    case object Z extends Event
  }

  object LettersFsm extends Fsm {
    val start: State = start(Statuses.Start)

    when(Statuses.Start) {
      case Events.F => goto(Statuses.F)
      case Events.S => goto(Statuses.S)
      case Events.M => goto(Statuses.M)
      case _ => stay()
    }

    when(Statuses.F) {
      case Events.I => goto(Statuses.I)
      case _ => stay()
    }

    when(Statuses.I) {
      case Events.N => goto(Statuses.N)
      case Events.T => goto(Statuses.T)
      case _ => stay()
    }

    when(Statuses.N) {
      case Events.I => goto(Statuses.I)
      case Events.E => goto(Statuses.E)
      case _ => stay()
    }

    when(Statuses.I) {
      case Events.N => goto(Statuses.N)
      case _ => stay()
    }

    when(Statuses.T) {
      case Events.E => goto(Statuses.E)
      case Events.A => goto(Statuses.A)
      case _ => stay()
    }

    when(Statuses.S) {
      case Events.T => goto(Statuses.T)
      case _ => stay()
    }

    when(Statuses.A) {
      case Events.T => goto(Statuses.T)
      case Events.C => goto(Statuses.C)
      case _ => stay()
    }

    when(Statuses.C) {
      case Events.H => goto(Statuses.H)
      case _ => stay()
    }

    when(Statuses.M) {
      case Events.A => goto(Statuses.A)
      case _ => stay()
    }

    when(Statuses.H) {
      case Events.I => goto(Statuses.I)
      case _ => stay()
    }

    when(Statuses.E) {
      case _ => goto(Statuses.Stop)
    }

    when(Statuses.Stop) {
      case event => (current: State) => stop(FsmException(s"Event: $event received in finite status: ${current.status}"))(current)
    }
  }

  implicit val fsm: LettersFsm.type = LettersFsm
  val start: State = fsm.start
}
