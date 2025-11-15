def digit_dict(k):
    str_k = str(k)
    return {d : str_k.count(d) for d in str_k}

#Unused function
def get_permuted_multiples(k):
    k_dict = digit_dict(k)

    valid_multiples = []
    for i in range(2, 7):
        if k_dict == digit_dict(i*k):
            valid_multiples.append(i)
    return valid_multiples

if get_permuted_multiples(125874) != [2]:
    print("Error in get_permuted_multiples")

def ensure_all_permuted_multiples(k):
    k_dict = digit_dict(k)

    for i in range(2, 7):
        if k_dict != digit_dict(i*k):
            return False
    return True

for i in range(1000000):
    if ensure_all_permuted_multiples(i):
        print("Answer found!", i)
        for j in range(2, 7):
            print(i*j)

