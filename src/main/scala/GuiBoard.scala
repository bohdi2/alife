
import java.awt.Color
import java.awt.Graphics
import scala.swing._


class GuiBoard(matrix: Matrix, tileSize: Int) extends FlowPanel {

  val board = Array.ofDim[Boolean](matrix.size)

  //change(matrix(5,5), true)
  //change(matrix(17,13), true)

  def change(n: Int, status: Boolean): Unit = {
    if (status != board(n)) {
      board(n) = status
      repaint
    }
  }

  def apply(x: Int, y: Int) = board(matrix(x,y))

  override def paintComponent(g: Graphics2D) {
    super.paintComponent(g)

    val f = paintTile(g) _
    board.zipWithIndex.foreach(t => f(t._1, t._2))


    }


  def paintTile(g: Graphics2D)(on: Boolean, index: Int) {
    //println(s"paitTile ${on} ${index}")
    if (board(index)) g.setColor(Color.GREEN)
    else g.setColor(Color.GRAY)

    val (x, y) = matrix(index)
    g.fillRect(x * tileSize, y * tileSize, tileSize-1, tileSize-1)
  }

}
