@main def main : Unit = 
    println("Here I go again")
    for i <- (1 to 50) do
        println(s"Is ${i} square? ${is_square(i)}")

def is_square(n : Int) : Boolean =
    val sqrt_n = math.sqrt(n)
    val lower = math.floor(sqrt_n).toInt
    val higher = math.ceil(sqrt_n).toInt
    lower*lower == n || higher*higher == n