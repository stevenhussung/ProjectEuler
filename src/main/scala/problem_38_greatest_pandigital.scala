@main def main: Unit =
    println((1 to 9999).map(i => greatest_pandigital(i,9)).max)

def greatest_pandigital(a : Int, n : Int) : Option[Int] = 
    (2 to n).map(x => concatenated_products(a,x)).filter(_.length <= 9).map(_.toInt).filter(is_pandigital).reduceOption((a, b) => math.max(a, b))

def concatenated_products(a : Int, n : Int) : String = 
    (1 to n).map(a*_).map(_.toString()).reduce((a, b) => a.concat(b))

def is_pandigital(a : Int) : Boolean =
    a.toString.toSet.size == 9
    &&  !a.toString.toSet.contains('0')