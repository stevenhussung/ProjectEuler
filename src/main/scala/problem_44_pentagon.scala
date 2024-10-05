
@main def problem_44 : Unit = 
    println("ON. MY. OWNNNNNNNNN")
    
    for i <- (1 to 10) do
        println(nth_pentagonal(i))
    
    // /* -- Test! -- Success
    for i <- (1 to 20) do 
        println(s"$i, ${is_pentagonal(i)}")
    // */
    
    var sum_of_pair : Int = 0
    var i = 1
    var difference_of_pair = nth_pentagonal(i)
    
    while
      difference_of_pair = nth_pentagonal(i)
      println(s"Testing n = ${i}, D = ${difference_of_pair}")
      !pentagonal_pair_exists(difference_of_pair) 
      // && 
      // i < 100
    do i += 1

    println(s"index of the difference: ${i}")
    println(s"D = ${difference_of_pair}")
    
    var solution_pair = find_pentagonal_pairs(difference_of_pair)
    println(s"Solution pair: ${solution_pair}")
    // println(s"Sum of solution pair: ${solution_pair.toList.sum}")

def nth_pentagonal(n : BigInt) : BigInt =
    n*(3*n-1)/2

def is_pentagonal(n : BigInt) : Boolean = 
  if n > 0 then 
    var upper_bound = nth_pentagonal(n)/n
    if upper_bound > n then
      is_pentagonal_bisect(n, 0, upper_bound)
    else
      is_pentagonal_bisect(n, 0, nth_pentagonal(n))
  else false


def is_pentagonal_bisect(n : BigInt, low : BigInt, high : BigInt) : Boolean =
  if (low - high).abs <= 1 then
    false
  else
    var mid = (low + high) / 2
    var mid_pent = nth_pentagonal(mid)
    if mid_pent == n then
      true
    else if mid_pent > n then
      is_pentagonal_bisect(n, low, mid)
    else
      is_pentagonal_bisect(n, mid, high)


def pentagonal_pair_exists(d : BigInt) : Boolean = 
    !find_pentagonal_pairs(d).isEmpty

def find_pentagonal_pairs(d_in : BigInt) =
    /*
    The difference between P(n+1) and P(n) (multiplied by 2) is
    (n+1)(3(n+1) - 1) - n*(3n - 1)
    = (n+1)(3n + 2) - 3n^2 + n
    = 3n^2 + 5n + 2 - 3n^2 + n
    = 6n + 2
    So P(n+1) - P(n) = 3n + 1
    
    We have to search upward until 3n + 1 > d, or while n <= d/3 - 1/3. (We use d/3 + 1 to avoid thoughts of rounding)
    After this n, all pentagonal numbers are too far apart for this pentagonal difference.
    */
    val d = d_in.toInt
    var b : BigInt = 0
    var p_b : BigInt = 0
    (
    for a <- (1 to d/3 + 1) 
        p_a = nth_pentagonal(a) 
        p_b = p_a + d

        if is_pentagonal(p_b)
        yield (p_a, p_b)
    )
    .filter{x => is_pentagonal(x(0) + x(1))}
