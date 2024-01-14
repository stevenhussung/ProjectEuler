@main def problem_39 : Unit = 
    println("Here I go again")
    for i <- (1 to 50) do
        println(s"Is ${i} square? ${is_square(i)}")
    
    val max_perimeter : Int = 10
    var perimeter_to_triangle_count : Map[Int, Int] = (1 to max_perimeter).map((_, 0)).toMap

//Not quite sure how to iterate through these--are we computing c? Or iterating?
    for a <- (1 to max_perimeter - 2)
        b <- (1 to max_perimeter - a - 1)
        c <- (1 to max_perimiter - a - b - 2)
        do
        if is_triangle(a, b, c) then
            
        


def is_square(n : Int) : Boolean =
    val sqrt_n = math.sqrt(n)
    val lower = math.floor(sqrt_n).toInt
    val higher = math.ceil(sqrt_n).toInt
    lower*lower == n || higher*higher == n