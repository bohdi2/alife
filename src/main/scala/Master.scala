import akka.actor.{ActorRef, Actor, Props}

object Master {
  //case class ConnectArb(arb: ActorRef)
  //case class ConnectArbs(arbs: Set[ActorRef])
  //case class ConnectBroker(brokers: ActorRef)
  //case class ConnectGui(guiActor: ActorRef)

  def props(): Props = Props(new Master())
}

class Master extends Actor {
  import Messages._

  val matrix = new Matrix(10, 10)
  var tiles: Map[Int, ActorRef] = Map()

  override def preStart = {

    def createTile(n: Int, guiActor: ActorRef) =
      context.actorOf(TileActor.props(n, matrix, guiActor), matrix.tileName(n))

    println("Master creates GuiActor")
    val gui = new Gui(self, matrix)
    val guiActor = context.actorOf(GuiActor.props(gui), "GUI")
    guiActor ! Start

    tiles = (0 until matrix.size).map(n => (n, createTile(n, guiActor))).toMap

  }



  def receive = {
    case Start => println("Start")
      tiles.values.foreach(_ ! Start(tiles))

    case Prime => println("Prime")
      tiles.values.foreach(_ ! Prime)

  }


}
