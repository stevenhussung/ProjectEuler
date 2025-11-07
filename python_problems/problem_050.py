#Tested
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


def longest_prime_sum_from_start(primes):

    subsequences = [primes[0:i] for i in range(1, len(primes))]
    sums = [sum(s) for s in subsequences]

    prime_sum_subsequences = list(filter(lambda x : sum(x) in primes, subsequences))

    if len(prime_sum_subsequences) > 0:
        longest_length = max([len(s) for s in prime_sum_subsequences])
        longest_prime_sum = list(filter(lambda s : len(s) == longest_length, prime_sum_subsequences))

        
        return longest_prime_sum[0]

    else:
        return []


def longest_prime_sum(primes):

    max_len = 0
    seq_with_max_len = []
    for i in range(len(primes)):
        seq = longest_prime_sum_from_start(primes[i:])
        if len(seq) > max_len:
            max_len = len(seq)
            seq_with_max_len = seq

    return seq_with_max_len


n = 10000

primes = primes_under(n)

s = longest_prime_sum(primes)
print(s)

print(sum(s))
