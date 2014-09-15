
import org.scalatest._

class MatrixTest extends FlatSpec with Matchers {
  val matrix = new Matrix(10, 10)

  behavior of "A Matrix"
  it should "know its size" in {
    100 should equal(matrix.size)
  }

  it should "convert index to (x,y)" in {
    (4, 2) should equal(matrix(24))
  }

  it should "convert and wrap index to (x,y)" in {
    (4, 2) should equal(matrix(124))
  }


  it should "convert (x,y) to index" in {
    24 should equal(matrix(4, 2))
  }

  it should "convert and wrap (-x,-y) to index" in {
    86 should equal(matrix(-4, -2))
  }

  it should "convert and horizonal wrap (x,y) to index" in {
    24 should equal(matrix(4, 12))
  }

  it should "convert and vertically wrap (x,y) to index" in {
    24 should equal(matrix(14, 2))
  }

  it should "know neighbors" in {
    List(14,15,16,24,26,34,35,36) should equal(matrix.neighbors(25))
  }

  it should "know neighbors on origin" in {
    List(99,90,91,9,1,19,10,11) should equal(matrix.neighbors(0))
  }

  it should "know neighbors on bottom edge" in {
    List(88,89,80,98,90,8,9,0) should equal(matrix.neighbors(99))
  }

  it should "know neighbors on top edge" in {
    List(94,95,96,4,6,14,15,16) should equal(matrix.neighbors(5))
  }

  it should "know an actor's name" in {
    "tile_13" should equal(matrix.tileName(13))
  }
}
