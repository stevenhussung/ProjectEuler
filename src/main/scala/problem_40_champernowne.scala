@main def problem_40 : Unit = 
    println("HERE I GOOOOOOOoooooo")

    println(identify_series(12))

def identify_series(n : Int) : Int =
    //This is very wrong: Do not pay attention to this.
    //There are 9 single digits, 
    //90 double digits (90*2 characters)
    //900 triple digits (900*3 characters)
    //9000 quadruple digits (9000*4 characters)
    //How do you determine which series a number is in? Not the way we currently are doing it!
    /*
        Map (n)
        0 - 9 -> 1
        10 - 89 -> 2
        90 - 899 -> 3
        900 - 8999 -> 4
    */
    if n < 10 then
        1
    else
        number_of_digits(n/9) + 1
    
def number_of_digits(n : Int) : Int =
    n.toString.length

// def identify_ordinal_in_series(n : Int, series : Int) : Int =
    