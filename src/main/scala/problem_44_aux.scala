//This is a notes file for old code from problem 44

/*  
def is_pentagonal(n : BigInt) : Boolean = 
  if n > 0 then 
    var upper_bound = nth_pentagonal(n)/n
    if upper_bound > n then
      is_pentagonal_bisect(n, 0, upper_bound)
    else
      is_pentagonal_bisect(n, 0, nth_pentagonal(n))
  else false


def is_pentagonal_bisect(n : BigInt, low : BigInt, high : BigInt) : Boolean =
  if (high - low) <= 1 then
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
*/