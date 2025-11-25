def digit_count(n):
    count = 0
    while n > 0:
        n //= 10
        count += 1

    return max(1, count)


def next_fraction(a, b):
    """
    Returns the next fraction in the continuous fraction sequence 
    to approximate sqrt(2)
    """
    return (b, 2*b + a)


#Generate fractions
a = 1
b = 2
count = 0
for i in range(999):
    if digit_count(a + b) > digit_count(b):
        count += 1

    (a, b) = next_fraction(a, b)
print(count)
