
@main def problem_43 : Unit = 
    println("HERE I GO AGAIN")

    val digits = "0123456789"
    val all_pandigitals = generate_pandigital(digits).filter(x => x(0) != '0')
    
    //Note to self! Remove anything with a leading 0. (Not a pandigital number!)
    
    val divisible_pandigitals = all_pandigitals
        .filter( x => is_div(x.slice(1, 4).toInt, 2) )
        .filter( x => is_div(x.slice(2, 5).toInt, 3) )
        .filter( x => is_div(x.slice(3, 6).toInt, 5) )
        .filter( x => is_div(x.slice(4, 7).toInt, 7) )
        .filter( x => is_div(x.slice(5, 8).toInt, 11) )
        .filter( x => is_div(x.slice(6, 9).toInt, 13) )
        .filter( x => is_div(x.slice(7, 10).toInt, 17) )
        .map(x => scala.math.BigInt(x))
    
    println(divisible_pandigitals)
    println(divisible_pandigitals.sum)
    
    
def is_div(number : Integer, divisor : Integer) : Boolean = 
    number % divisor == 0