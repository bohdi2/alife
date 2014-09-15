/**
 * Created by chris on 8/15/14.
 */

import akka.actor.ActorRef

object Messages {

  case class Start(tiles: Map[Int, ActorRef])

  case object Prime
  //case class Off(n: Int)
  //case class On(n: Int)
  case class Change(n: Int, value: Boolean)

}
