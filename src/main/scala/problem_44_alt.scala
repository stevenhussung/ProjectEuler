@main def problem_44_alt : Unit = 
    var pent_set = scala.collection.mutable.Set[Int]()
    var answer = List[BigInt]()
    var i = 1
    var s = 1

    while answer.length == 0 do
        i += 1
        s = ((3*i*i) - i) / 2
        for p_j <- pent_set do
            if pent_set.contains(s - p_j) && pent_set.contains(s - 2*p_j) then
                answer = List(s - 2*p_j)
        pent_set.add(s)

    print(answer)