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
   
    //Attempt 2 may also be too slow somehow! Definitely thought this would work.
    
    //Attempt 3: Unified function to produce only prime pandigitals. How? Check as you go? Is that better?
    //Attempt 3b: Tail recursion. Is it possible / desireable here?
    
   
    //Next step: make function to generate the pandigital numbers one by one in descending order. Then filter those through primes
    //(Step after that would be to skip the evens maybe? Unsure)
    val digits = "123456789".sorted.reverse
    val pandigital_numbers =  generate_pandigital(digits).map(_.toInt)
    println(pandigital_numbers.filter(is_prime).head)
    
    

//Later I may make this tail callable
def generate_pandigital(s : String): List[String]= 
    if s.length == 0 then
        List("")
    else
        (
            for digit_char <- s
            digit_char_str = digit_char.toString
            
            //TO DO get this call to work. Need to make a map from the returned array to prepend the chosen digit. Will need to flatten as well.
            tail_string_list = generate_pandigital(s.replace(digit_char_str, ""))
            yield
                tail_string_list.map(
                (partial_string) =>
                    digit_char_str + (partial_string)
                )
        ).flatten.toList
        


def is_prime(n : Int) : Boolean =
    n > 1 &&
    (2 to n - 1).filter(n%_ == 0).isEmpty