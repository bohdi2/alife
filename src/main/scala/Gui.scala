
import akka.actor.ActorRef
import java.awt.Color
import java.awt.Dimension
import scala.swing._
import scala.swing.event.ButtonClicked



class Gui(master: ActorRef, matrix: Matrix) extends MainFrame {

  val WIDTH = 600
  val HEIGHT = 400
  val board = new GuiBoard(matrix, 10)

  minimumSize = new Dimension(WIDTH, HEIGHT)

  title = "Akka Life"
  val stopButton = new Button("Stop")

  contents = new BoxPanel(Orientation.Vertical) {
    contents += board
    contents += stopButton
  }

  listenTo(stopButton)

  reactions += {
    case ButtonClicked(`stopButton`) =>
      println("stop")
    case bc: ButtonClicked => println("Button: " + bc)
  }

  def change(n: Int, status: Boolean) {board.change(n, status)}

}

