@main def problem_40 : Unit = 
    println("HERE I GOOOOOOOoooooo")

    val n = 12
    println(d(n))
    
    val indexes = for i <- (0 to 6) yield math.pow(10, i).toInt
    val constant_values = indexes.map(d)
    val product = constant_values.reduce((x, y) => x*y)
    
    println(indexes)
    println(constant_values)
    println(product)

    
def d(n : Int) : Int =
    var (number_of_digits, number_index_within_series, digit_index_within_number) = parse_constant(n)
    var series_start : Int = math.pow(10, number_of_digits-1).toInt
    var number_to_find : Int = series_start + number_index_within_series
    var digit_to_find : Int = get_bth_digit_from_a(number_to_find, digit_index_within_number)

    /* //For debugging
    println(s"So d-${n} is within the ${number_of_digits} sequence, which starts with ${series_start}")
    println(s"We see that d-${n} is a part of ${number_to_find}, specifically the ${digit_index_within_number} digit.")
    println(s"Which is ${digit_to_find}.")
    */
    
    digit_to_find
    
def get_bth_digit_from_a(a : Int, b : Int) : Int = 
    a.toString.toList(b).toInt - '0'.toInt

def parse_constant(n_prep : Int) : (Int, Int, Int) =
    //There are 9 single digits, 
    //90 double digits (90*2 characters)
    //900 triple digits (900*3 characters)
    //9000 quadruple digits (9000*4 characters)
    //How do you determine which series a number is in? Not the way we currently are doing it!

    var n = n_prep
    /*
    if n <= 9*math.pow(10,0)*1 then
        1
    else
        n -= 9*math.pow(10,0)*1
        if n < 90*2 then
            2
        else
            n -= 9*math.pow(10,1)*2
            if n < 9*math.pow(10,2)*3
                3
    */ //Maybe with enough practice I won't need to write it out, but who cares? 
    //You are able to do it the long way, which is the short way--all things considered.
    var series : Int = 1
    var next_series_boundary : Int = 0
    while
        next_series_boundary = number_of_characters_between_boundaries(series)
        n > next_series_boundary
    do
        n -= next_series_boundary
        series += 1
    (series, (n-1)/series, (n-1)%series)
                
def number_of_digits(n : Int) : Int =
    n.toString.length

def number_of_characters_between_boundaries(series : Int) : Int =
    9*math.pow(10,series-1).toInt*series
