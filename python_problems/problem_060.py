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

def concatenate(a, b):
    return int(str(a) + str(b))

#Main
n = 100
primes = primes_under(n)
print(primes, "primes under", n)

prime_families = []

for p in primes:
    for i, F in enumerate(prime_families):
        if all([concatenate(p, q) in primes and concatenate(q, p) in primes for q in F]):
            F.append(p)

    prime_families.append([p])
    print(prime_families)

print(filter(lambda S : len(S) > 1, prime_families)

