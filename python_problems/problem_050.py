# 
# TODO: Next todo item. If you already have a culative prime list of length k, 
# then you can stop looking for subsequences once you reach n/k. This is because
# to get a longer list, you would have to find k numbers larger than n/k, which 
# will sum to over n.
#
# This means you can adaptively adjust the number at which you stop looking for
# more sequences. (We still need to do this!)


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

if primes_under(100) != [2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]:
    print("Error in primes_under")


def longest_prime_sum_from_start(primes, max_len=1):

    sum_start = primes[0]
    largest_prime = primes[-1]
    print("Largest prime:", largest_prime)
    primes_shortened = primes[:largest_prime//sum_start + 1]
    prime_set = set(primes)

    prime_sum_subsequences = [primes_shortened[0:i] for i in range(max_len, len(primes_shortened)) if sum(primes[0:i]) <= largest_prime and sum(primes[0:i]) in prime_set]

    if len(prime_sum_subsequences) > 0:
        longest_length = max([len(s) for s in prime_sum_subsequences])
        longest_prime_sum = list(filter(lambda s : len(s) == longest_length, prime_sum_subsequences))
        return longest_prime_sum[0]
    else:
        return []

if longest_prime_sum_from_start([2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97]) != [2, 3, 5, 7, 11, 13]:
    print("Error in longest_prime_sum_from_start")
    print("Longest_prime_sum_from_start(primes(100)):", longest_prime_sum_from_start(primes_under(100)))


def longest_prime_sum(primes):

    n = len(primes)
    largest_prime = primes[-1]
    max_len = 0
    seq_with_max_len = []
    for i in range(n):
        print("Searching for longest prime sum beginning with", primes[i], "and searching through", len(primes) - i, "primes")
        seq = longest_prime_sum_from_start(primes[i:])
        if len(seq) > max_len:
            max_len = len(seq)
            seq_with_max_len = seq

        if i*max_len > largest_prime:
            break

    return seq_with_max_len


n = 200000
print("Generate primes")
primes = primes_under(n)

print("Finding longest prime sum")
s = longest_prime_sum(primes)
print(s)

print(sum(s))
