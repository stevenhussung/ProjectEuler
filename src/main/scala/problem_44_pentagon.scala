
@main def problem_44 : Unit = 
    println("ON. MY. OWNNNNNNNNN")
    
    for i <- (1 to 10) do
        println(nth_pentagonal(i))
        
    var checker = Pentagon_knower()
    
    // /* -- Test! -- Success
    for i <- (1 to 20) do 
        println(s"$i, ${checker.is_pentagonal(i)}")
    // */
    
    var sum_of_pair : Int = 0
    var i = 1
    var difference_of_pair = nth_pentagonal(i)
    
    while
      difference_of_pair = nth_pentagonal(i)
      if i % 100 == 0 then
        println(s"Testing n = ${i}, D = ${difference_of_pair}")
      !pentagonal_pair_exists(i, difference_of_pair, checker) 
      // && 
      // i < 100
    do i += 1

    println(s"index of the difference: ${i}")
    println(s"D = ${difference_of_pair}")
    
    var solution_pair = find_pentagonal_pairs(i, difference_of_pair, checker)
    println(s"Solution pair: ${solution_pair}")
    // println(s"Sum of solution pair: ${solution_pair.toList.sum}")

def nth_pentagonal(n : BigInt) : BigInt =
    n*(3*n-1)/2

class Pentagon_knower():
  var max_known_index = BigInt(1)
  var max_known_pentagonal = BigInt(1)
  private var known_pentagonal_nums = scala.collection.mutable.Set[BigInt](1)
  
  def is_pentagonal(n : BigInt) = 
    while n > max_known_pentagonal 
      do this.learn_next()
    known_pentagonal_nums.contains(n)

  private def learn_next() = 
    max_known_index += 1
    max_known_pentagonal = nth_pentagonal(max_known_index)
    known_pentagonal_nums.add(max_known_pentagonal)


def pentagonal_pair_exists(i : Int, d : BigInt, checker : Pentagon_knower) : Boolean = 
    !find_pentagonal_pairs(i, d, checker).isEmpty

def find_pentagonal_pairs(i : Int, d_in : BigInt, checker : Pentagon_knower) =
    /*
    The difference between P(n+1) and P(n) (multiplied by 2) is
    (n+1)(3(n+1) - 1) - n*(3n - 1)
    = (n+1)(3n + 2) - 3n^2 + n
    = 3n^2 + 5n + 2 - 3n^2 + n
    = 6n + 2
    So P(n+1) - P(n) = 3n + 1
    
    We have to search upward until 3n + 1 > d, or while n <= d/3 - 1/3. (We use d/3 + 1 to avoid thoughts of rounding)
    After this n, all pentagonal numbers are too far apart for this pentagonal difference.
    
    p_a > d/3 - 1/3 
    p_a > d/3 - 1
    */
    val d = d_in.toInt
    var b : BigInt = 0
    var p_b : BigInt = 0
    (
    for a <- (math.floor(math.sqrt(i)).toInt to d/3 + 1) 
        p_a = nth_pentagonal(a) 
        p_b = p_a + d

        if checker.is_pentagonal(p_b)
          && checker.is_pentagonal(p_a + p_b)
        yield (p_a, p_b)
    )