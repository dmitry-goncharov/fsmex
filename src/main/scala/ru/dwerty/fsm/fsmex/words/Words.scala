package ru.dwerty.fsm.fsmex.words

import ru.dwerty.fsm.fsmex.core.Fsm
import ru.dwerty.fsm.fsmex.core.Fsm.{Event, FsmException, State, Status}

object Words {
  object Statuses {
    case object Start extends Status

    case object Stop extends Status

    case object Finite extends Status

    case object State extends Status

    case object Machine extends Status
  }

  object Events {
    case object Unknown extends Event

    case object Finite extends Event

    case object State extends Event

    case object Machine extends Event
  }

  object WordsFsm extends Fsm {
    val start: State = start(Statuses.Start)

    when(Statuses.Start) {
      case Events.Finite => goto(Statuses.Finite)
      case _ => stay()
    }

    when(Statuses.Finite) {
      case Events.State => goto(Statuses.State)
      case _ => stay()
    }

    when(Statuses.State) {
      case Events.Machine => goto(Statuses.Machine)
      case _ => stay()
    }

    when(Statuses.Machine) {
      case _ => goto(Statuses.Stop)
    }

    when(Statuses.Stop) {
      case event => (current: State) => stop(FsmException(s"Event: $event received in finite status: ${current.status}"))(current)
    }
  }

  implicit val fsm: WordsFsm.type = WordsFsm
  val start: State = fsm.start
}
