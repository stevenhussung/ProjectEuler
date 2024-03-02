def problem_39 : Unit = 
    println("Here I go again")

    val max_perimeter : Int = 1000

    val triangle_number_counts = 
    for a <- (1 to max_perimeter - 2)
        b <- (a to max_perimeter - a - 1)
        c <- int_sqrt(a*a + b*b)
        if a + b + c <= max_perimeter
        yield
        // println(s"a = ${a}, b = ${b}, a*a + b*b = ${a*a + b*b}, so c = ${c}, and a + b + c = ${a + b + c}")
        a + b + c

    val triangle_sets_per_perimeter =
    for n <- (1 to max_perimeter)
        yield
        (n, triangle_number_counts.count(_ == n))
    
    println(triangle_sets_per_perimeter.maxBy(_(1)))
            
        
def int_sqrt(n : Int) : List[Int] =
    val sqrt_n = math.sqrt(n)
    val lower = math.floor(sqrt_n).toInt
    val higher = math.ceil(sqrt_n).toInt
        
    if lower*lower == n then
        List(lower)
    else if higher*higher == n then
        List(higher)
    else 
        List()