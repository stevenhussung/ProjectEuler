#Powerful digit sum
#Sums of digits of numbers a^b, where a, b < 100

def digit_sum(n):
    total = 0
    while n > 0:
        total += n%10
        n //= 10
    return total

n = 20
digit_sums = []
for a in range(100):
    for b in range(100):
        digit_sums.append(digit_sum(a**b))

print(max(digit_sums))
