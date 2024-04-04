
@main def problem_44 : Unit = 
    println("ON. MY. OWNNNNNNNNN")

    for i <- (1 to 10) do
        println(nth_pentagonal(i))

    // /* -- Test! -- Success
    for i <- (0 to 20) do 
        println(s"$i, ${is_pentagonal(i)}")
    // */

    //Next: while loop incrementing D, 
    //  use inner for loop to search over pentagonal numbers for differences equal to d, 
    //  and test valid sums for pentagonality
    var sum_of_pair : Int = 0
    // while !is_pentagonal(sum_of_pair)

def nth_pentagonal(n : Int) : Int =
    n*(3*n-1)/2

def is_pentagonal(n : Int) : Boolean = 
    var seeker : Int = 1
    while nth_pentagonal(seeker) < n do
        seeker = seeker + 1
    nth_pentagonal(seeker) == n

/* Commenting out for now. This is log(n), but sqrt(n) is still pretty good
var lower = 1
var higher = n
var middle = int_average(lower, higher)

//Bisect
while lower <> higher
    int_average(lower, higher)
    
    //Set for next iteration
    middle = int_average(lower, higher)
    //TODO
*/