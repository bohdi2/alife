
import akka.actor._

object  Main extends App {

  val actorSystem = ActorSystem("EBS")

  val master = actorSystem.actorOf(Master.props())

  println("Main asking master to start")
  master ! Messages.Start

  Thread.sleep(2000)

  println("Main asking master to prime")

  master ! Messages.Prime

  Thread.sleep(5000)
  println("Done")

}
