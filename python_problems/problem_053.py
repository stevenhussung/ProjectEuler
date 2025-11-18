from functools import cache, reduce

@cache
def product(a, b):
    if a < b:
        return reduce(lambda x, y : x*y, list(range(a, b+1)))
    else:
        return 1

if product(4, 7) != 7*6*5*4 or \
    product(6, 10) != 10*9*8*7*6:
    print("Error in product")
    print(product(4,7))
    print(product(6,10))

@cache
def factorial(n):
    if n in [0, 1]:
        return 1
    else:
        return n*factorial(n-1)

if factorial(3) != 6 or \
    factorial(0) != 1 or \
    factorial(1) != 1:
    print("Error in factorial")
    

def n_choose_k(n, k):
    """
    Compute and return
    n! / [k! * (n-k)!]
    """
    return product(n-k+1, n) // factorial(k)

if n_choose_k(5, 3) != 10 \
        or n_choose_k(23, 10) != 1144066 \
        or n_choose_k(12, 0) != 1 \
        or n_choose_k(10, 2) != 10*9/2:
    print("Error in n_choose_k")
    print(n_choose_k(23, 10))
    print(n_choose_k(12, 0))
    print(n_choose_k(10, 2))

count = 0
for n in range(101):
    for k in range(n+1):
        print(n, k, n_choose_k(n, k))
        if n_choose_k(n,k) > 1000000:
            count += 1
print(count)
