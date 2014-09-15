
import akka.actor.{Props, Actor, ActorRef, ActorSelection}

object TileActor {
  def props(key: Int, matrix: Matrix, guiActor: ActorRef): Props = Props(classOf[TileActor], key, matrix, guiActor)
}

class TileActor(key: Int, matrix: Matrix, guiActor: ActorRef) extends Actor {
  import context._
  import Messages._

  var status = false
  val neighborStatus = scala.collection.mutable.Map() ++ matrix.neighbors(key).map(n => (n, false)).toMap
  var neighbors = scala.collection.immutable.Map[Int, ActorRef]()


  def receive = {
    case Start(tiles) =>

      neighbors = matrix.neighbors(key).foldLeft(Map[Int,ActorRef]()){ (acc, x) => acc + (x -> tiles(x)) }
            neighbors.foreach(p => println("Mappings " + p))


    case Prime =>
      status = math.random < 0.50
      guiActor ! Change(key, status)
      //neighbors.foreach(n => println(s"xxxxxxxxxxxxxxx ${key} actor ${n} my status ${status}"))
      //println(s"Prime1 ${key} sending status to ${matrix.neighbors(key)}")
      //println(s"Prime2 ${key} sending status to ${neighbors}")

      neighbors.foreach(n => n._2 ! Change(key, status))


    case Change(neighbor, value) =>
      //println(s"Tile ${key} got a change from ${neighbor} $value}")
      neighborStatus(neighbor) = value
      val c = count
      val oldStatus = status
      status = (c == 2 || c == 3)
      if (status != oldStatus) {
        //neighbors.foreach(n => println(s"yyyyyyyyyyyy actor ${n}"))
        neighbors.foreach(n => n._2 ! Change(key, status))
        guiActor ! Change(key, status)
      }

    case _ =>
      println(s"Tile ${key} got unknown")

  }

  def count = neighborStatus.count(_._2 == true)
}
