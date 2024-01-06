@main def problem_37: Unit =
    val is_prime = prime_engine

    val truncateable_primes =
        (1 to 1000000).filter(n => is_truncateable_prime(n, is_prime)).toList
    
    println(truncateable_primes)
    println(truncateable_primes.sum)

def is_truncateable_prime(n : Int, is_prime : Int => Boolean) : Boolean = 
    if n < 10 then 
        false
    else
        get_truncate_list(n) forall (is_prime)

def prime_engine: (Int => Boolean) = 
    def is_prime(n : Int) : Boolean =    
        ( (2 to n - 1) forall (n % _ != 0) )
        && (n > 1)
    
    val cache = collection.mutable.Map.empty[Int, Boolean]

    n => cache.getOrElseUpdate(n, is_prime(n))
    
def get_truncate_list(n : Int) : List[Int] =
    get_right_truncate_list(n)
    ++ get_left_truncate_list(n)

def get_right_truncate_list(n : Int) : List[Int] =
    if n == 0 then
        List()
    else
        get_right_truncate_list(n/10).appended(n)
    
def get_left_truncate_list(n : Int) : List[Int] =
    val L_reversed : List[Int] = get_right_truncate_list(n.toString.reverse.toInt)
    for n <- L_reversed
        yield n.toString.reverse.toInt

