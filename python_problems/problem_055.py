def mirror(n: int):
    m = 0
    while n > 0:
        m *= 10
        m += n%10
        n //= 10
    return m

if mirror(1234) != 4321 or \
    mirror(3131) != 1313 or \
    mirror(10) != 1:
        print("Error in mirror")

def is_palindrome(n):
    return n == mirror(n)
if is_palindrome(1234) != False or \
    is_palindrome(1221) != True:
    print("Error in is_palindrome")

def is_likely_lychrel(n):
    #print(f"Testing {n}")
    for i in range(50):
        n += mirror(n)
        #print(n)
        if is_palindrome(n):
            return False
    return True

for i in range(10000):
    if is_likely_lychrel(i):
        print(i, "is likely a Lychrel number")
