@main def main: Unit =
    println("Checking in")
    for i <- (1 to 100000) 
    do
        println(greatest_pandigital(i, 9))

def greatest_pandigital(a : Int, n : Int) : Int = 
    (1 to n).map(x => pandigital_str(a,x)).filter(_.length <= 9).map(_.toInt).max

/*
def greatest_pandigital(a : Int, n : Int, count_down : Int) : Int = 
    if countdown == 0 then
        0
    else
        math.max(
            pandigital(a, n),
            greatest_pandigital(a, n-1)
        )
        
def pandigital(a : Int, n : Int) : Long = 
    pandigital_str(a, n).tolong
*/
def pandigital_str(a : Int, n : Int) : String = 
    (1 to n).map(a*_).map(_.toString()).reduce((a, b) => a.concat(b))