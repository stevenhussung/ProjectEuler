
@main def problem_44 : Unit = 
    println("ON. MY. OWNNNNNNNNN")
    
    for i <- (1 to 10) do
        println(nth_pentagonal(i))
    
    // /* -- Test! -- Success
    for i <- (0 to 20) do 
        println(s"$i, ${is_pentagonal(i)}")
    // */
    
    var sum_of_pair : Int = 0
    var difference_of_pair_index = 1
    var difference_of_pair = nth_pentagonal(difference_of_pair_index)
    
    while
        println(s"Testing n = ${difference_of_pair_index}, D = ${difference_of_pair}")
        difference_of_pair = nth_pentagonal(difference_of_pair_index)
        !pentagonal_pair_exists(difference_of_pair)
    do
        difference_of_pair_index += 1

    println(s"index of the difference: ${difference_of_pair_index}")
    println(s"D = ${difference_of_pair}")
    
    var solution_pair = find_pentagonal_pairs(difference_of_pair)
    println(s"Solution pair: ${solution_pair}")
    // println(s"Sum of solution pair: ${solution_pair.toList.sum}")

def nth_pentagonal(n : Int) : Int =
    n*(3*n-1)/2

def is_pentagonal(n : Int) : Boolean = 
    var seeker : Int = 1
    while nth_pentagonal(seeker) < n do
        seeker = seeker + 1
    nth_pentagonal(seeker) == n

/* If we bisect, we would achieve log(n), but sqrt(n) is still pretty good */

def pentagonal_pair_exists(d : Int) : Boolean = 
    !find_pentagonal_pairs(d).isEmpty

def find_pentagonal_pairs(d : Int) =
    /*
    The difference between P(n+1) and P(n) (multiplied by 2) is
    (n+1)(3(n+1) - 1) - n*(3n - 1)
    = (n+1)(3n + 2) - 3n^2 + n
    = 3n^2 + 5n + 2 - 3n^2 + n
    = 6n + 2
    So P(n+1) - P(n) = 3n + 1
    
    We have to search upward until 3n + 1 > d, or while n <= d/3 - 1. (We use d/3 to avoid thoughts of rounding)
    After this n, all pentagonal numbers are too far apart for this pentagonal difference.
    */
    for a <- (1 to d/3)
        b <- (a to d/3 + 1)
        p_a = nth_pentagonal(a)
        p_b = nth_pentagonal(b)
        pentagonal_sum = p_a + p_b
        if p_b - p_a == d && is_pentagonal(pentagonal_sum)
        yield (p_a, p_b)