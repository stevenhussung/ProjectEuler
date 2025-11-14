#Solved! Answer in 7 seconds: 997651

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

def digitwise_mult(a, b):
    total = 0
    power = 0
    while a > 0:
        total += (10**power) * (a%10) * (b%10)
        a //= 10
        b //= 10
        power += 1
    return total

if digitwise_mult(34, 10) != 30 or \
    digitwise_mult(1234, 10) != 30:
    print("Error in digitwise mult")

def count_prime_replacements_with_mask(p, prime_set, mask):
    d = max(set(str(digitwise_mult(p, mask))))
    base_p = p - digitwise_mult(p, mask)
    family = [base_p + i*mask for i in range(10)]
    family = list(filter(lambda q : len(str(q)) == len(str(p)), family))
    prime_family = list(filter(lambda q : q in prime_set, family))

    if len(prime_family) > 0:
        return (min(prime_family), len(prime_family))
    else:
        return
if count_prime_replacements_with_mask(13, primes_under(100), 10) != (13, 6):
    print("Error in count_prime_replacements_with_mask")

def generate_masks(p):
    digits_present = list(map(int, set(str(p))))
    masks = []

    for d in digits_present:
        this_mask = 0
        power = 0
        temp = p
        while temp > 0:
            this_mask += (1 if temp%10 == d else 0)* (10**power)
            power += 1
            temp //= 10

        masks.append(this_mask)

    return masks

if sorted(generate_masks(1234)) != [1, 10, 100, 1000] or \
        sorted(generate_masks(2332)) != [110, 1001]:
    print("Error in generate_masks")


def count_prime_replacements(p, prime_set):
    counts = []
    for mask in generate_masks(p):
        counts.append(count_prime_replacements_with_mask(p, prime_set, mask))
    counts = sorted(counts, key = lambda x : x[1])
    return counts[-1]

if count_prime_replacements(56003, primes_under(100000)) != (56003, 7):
    print("Error in count_prime_replacements")


primes = primes_under(1000000)
prime_set = set(primes)
highest_count = 0
for p in primes:
    (p_smallest_member, p_count) = count_prime_replacements(p, prime_set)
    if p_count > highest_count:
        print("New best!")
        print((p_smallest_member, p_count))
        highest_count = p_count

