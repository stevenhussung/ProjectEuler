@main def main: Unit =
    println("Checking in")
    println(greatest_pandigital(192, 9))

def greatest_pandigital(a : Int, n : Int) : Int = 
    greatest_panditigal(a, 1, n)

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

def pandigital_str(a : Int, n : Int) : String = 
    (1 to n).map(a*_).map(_.toString()).reduce((a, b) => a.concat(b))