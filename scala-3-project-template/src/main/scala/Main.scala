@main def hello: Unit =
    val engine = prime_engine
    for i <- (-1 to 20) do
        println((i,engine(i)))

def msg = "I was compiled by Scala 3. :-)"

def prime_engine: (Int => Boolean) = 
    def is_prime(p : Int) : Boolean =    
        (2 to p - 1) forall (p % _ != 0)
    
    val cache = collection.mutable.Map.empty[Int, Boolean]


    return is_prime
