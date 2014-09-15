

class Matrix(width: Int, height: Int) {
  def apply(index: Int) = {
    val n = index % size
    (n % width, n / width)
  }

  def apply(x: Int, y: Int) = mod(x,height) + width * mod(y,width)

  def size = width * height

  def neighbors(n: Int) = {
    val (x, y) = apply(n)

    List(apply(x-1, y-1),
         apply(x, y-1),
         apply(x+1, y-1),
         apply(x-1, y),
         apply(x+1, y),
         apply(x-1, y+1),
         apply(x, y+1),
         apply(x+1, y+1))
  }

  def mod(a: Int, b: Int) = ((a%b) + b) % b

  def tileName(n: Int) = s"tile_${n}"
}