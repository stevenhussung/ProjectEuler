
val non_prime_flag = "-"

def problem_41 : Unit = 
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
    
    //Attempt 2 is actually ok! The thing is that there are NO 9-digit pandigital primes! Why not? Gotta be something.
    //Lmao. The triangular number for 9 is 9*10/2 = 45, which is divisible by 3. So they're always divisible by 3.
    // Leaving this here, in memoriam
    // val digits = "123456789".sorted.reverse
       
    // How about 8? Nope! Digit sum is just 9 less than 45, which is still divisible by 3.
    // val digits = "12345678".sorted.reverse
    
   
    //Next step: make function to generate the pandigital numbers one by one in descending order. Then filter those through primes
    //(Step after that would be to skip the evens maybe? Unsure)
    val digits = "1234567".sorted.reverse
    val pandigital_numbers =  generate_pandigital(digits)
        .filter(!_.contains("-"))
        .map(_.toInt)
    
    println("Numbers generated! Now need the prime")
    println(pandigital_numbers.dropWhile(!is_prime(_)).head)
    
    

//Later I may make this tail callable
def generate_pandigital(s : String): List[String]= 
    /*
    if s.length == 0 then
        List("")
        */
    if s.length == 1 then
        s match
            case "2" | "4" | "5" | "6" | "8" => List(non_prime_flag)
            case _ => List(s)
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
    val result = 
    n > 1 &&
    (2 to Math.sqrt(n).toInt + 1).filter(n%_ == 0).isEmpty
    result