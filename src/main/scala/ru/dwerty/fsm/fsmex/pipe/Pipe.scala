package ru.dwerty.fsm.fsmex.pipe

trait Pipe[In, Out] {
  def pipe(it: Iterator[In]): Iterator[Out]
}
