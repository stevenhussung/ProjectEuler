#Tested prime generator from problem 50
def primes_under(n):
    """
        Return array of primes (in order) less than n
    """

    #Index is_prime[3] should be true, is_prime[4] should be false
    is_prime = [True for i in range(n)] 

    for i in range(2, len(is_prime)):

        if is_prime[i]:
            j = i*2
            while j < n:
                is_prime[j] = False
                j += i

    #Convert to list
    primes = map(lambda x : x[0], filter(lambda x : x[1], list(enumerate(is_prime))))
    return list(primes)[2:]

if primes_under(100) != [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]:
    print("Error in primes_under")

def get_diagonals(side_length):
    if side_length < 1:
        return []
    diagonals = [1]
    base = 1
    while base < side_length:
        diagonals += [base**2 + (base+1)*side_number for side_number in [1, 2, 3, 4]]
        base += 2
    return diagonals
        
# Must be odd!
max_side_length = 10001
#max_side_length = 9

primes = set(primes_under(max_side_length**2 + 1))

for side_length in range(1, max_side_length + 1, 2):
    diagonals = get_diagonals(side_length)

    prime_diagonals = list(filter(lambda x : x in primes, diagonals))

    if side_length > 1 and len(prime_diagonals)*10 < len(diagonals):
        print("First! This side length is", side_length)
        exit()

