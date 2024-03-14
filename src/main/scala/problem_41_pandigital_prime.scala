@main def problem_41 : Unit = 
    println("guitar!")
    
    //Attempt 1: Naive - too slow!
    /*
    val greatest_pandigital_prime = 
        Range(987654321, 123456789, -2).view.
        filter(is_pandigital).
        filter(is_prime).head

    println(greatest_pandigital_prime)
    */
   
    //Next step: make function to generate the pandigital numbers one by one in descending order. Then filter those through primes
    //(Step after that would be to skip the evens maybe? Unsure)
    println(generate_pandigital("123"))

//Later I may make this tail callable
def generate_pandigital(s : String): List[String]= 
    if s.length == 0 then
        List("")
    else
        (
            for digit_char <- s
            digit_char_str = digit_char.toString
            yield
            
            //TO DO get this call to work. Need to make a map from the returned array to prepend the chosen digit. Will need to flatten as wel.
            generate_pandigital(s.replace(digit_char_str, "")).map(
                (partial_string) =>
                    digit_char_str + (partial_string)
            ).toList()
        )
        


def is_prime(n : Int) : Boolean =
    n > 1 &&
    (2 to n - 1).filter(n%_ == 0).isEmpty
    
/* //already defined!
def is_pandigital(a : Int) : Boolean =
    var digit_set = a.toString.toSet
    digit_set.size == 9
    &&  !digit_set.contains('0')
*/