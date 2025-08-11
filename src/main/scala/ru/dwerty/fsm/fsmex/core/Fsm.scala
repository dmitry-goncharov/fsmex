package ru.dwerty.fsm.fsmex.core

import ru.dwerty.fsm.fsmex.core.Fsm.{Event, FsmException, State, Status}

import scala.collection.mutable

trait Fsm {
  private type Transition = State => State
  private type StateFunction = PartialFunction[Event, Transition]

  private val stateFunctions = mutable.Map[Status, StateFunction]()

  def process(currentState: State, event: Event): State = {
    val stateFunction = stateFunctions(currentState.status)
    val nextState = if (stateFunction.isDefinedAt(event)) {
      stateFunction(event)(currentState)
    } else {
      stay()(currentState).withStopReason(FsmException(s"Unhandled event $event in state $currentState"))
    }
    nextState.stopReason.foreach(throw _)
    applyState(nextState, currentState)
  }

  protected final def when(statuses: Status*)(stateFunction: StateFunction): Unit = {
    statuses.foreach(register(_, stateFunction))
  }

  protected final def start(status: Status): State = State(status)

  protected final def stay(): Transition = currentState => currentState

  protected final def goto(nextStatus: Status): Transition = _ => State(nextStatus)

  protected final def stop(reason: FsmException): Transition = {
    currentState => stay()(currentState).withStopReason(reason)
  }

  private final def register(status: Status, function: StateFunction): Unit = {
    stateFunctions(status) = stateFunctions.getOrElse(status, function)
  }

  private def applyState(nextState: State, currentState: State): State = {
    val state = if (stateFunctions.contains(nextState.status)) {
      nextState
    } else {
      stay()(currentState).withStopReason(FsmException(s"Next state ${nextState.status} does not exist"))
    }
    state.stopReason.foreach(throw _)
    state
  }
}

object Fsm {
  trait Status {
    def is(pf: PartialFunction[Status, Boolean]): Boolean = Status.is(this)(pf)
  }

  object Status {
    def is(status: Status)(pf: PartialFunction[Status, Boolean]): Boolean = pf.lift(status).fold(false)(identity)
  }

  class Event

  case class State(status: Status, stopReason: Option[FsmException] = None) {
    def !(event: Event)(implicit fsm: Fsm): State = fsm.process(this, event)

    private[core] def withStopReason(reason: FsmException): State = copy(stopReason = Some(reason))
  }

  case class FsmException(message: String) extends RuntimeException(message)
}
