
import akka.actor.{Props, Actor, ActorRef}

object GuiActor {

  def props(gui: Gui): Props = Props(new GuiActor(gui))
}

class GuiActor(gui: Gui) extends Actor {
  import GuiActor._
  import Messages._

  def receive = {
    case Start =>
      gui.visible = true

    case Change(n, status) =>
      gui.change(n, status)

  }
}