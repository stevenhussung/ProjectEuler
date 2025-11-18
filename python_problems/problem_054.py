
hand_score = \
    {"High Card" : 1,
     "One Pair" : 2,
     "Two Pairs" : 3,
     "Three of a Kind" : 4,
     "Straight" : 5,
     "Flush" : 6,
     "Full House" : 7,
     "Four of a Kind" : 8,
     "Straight Flush" : 9,
     "Royal Flush" : 10}


#print(hand_score["Full House"])
#print(hand_score["Full House"] > hand_score["Two Pairs"])

def get_hand_ranks(hand):
    return [hand[2*i] for i in range(5)]

def cardinalities(hand):
    ranks = get_hand_ranks(hand)
    cardinalities = { r : ranks.count(r) for r in ranks}
    return cardinalities

def read_multiple_hands(hand):
    cardinal = cardinalities(hand)
    significant_ranks = list(filter(lambda x: cardinal[x] > 1, cardinal))
    rank_multiplicities = sorted(map(lambda r : cardinal[r], significant_ranks), key = lambda x : -x)

    if rank_multiplicities == [3, 2]:
        hand_name = "Full House"
    elif rank_multiplicities == [4]:
        hand_name = "Four of a Kind"
    elif rank_multiplicities == [3]:
        hand_name = "Three of a Kind"
    elif rank_multiplicities == [2, 2]:
        hand_name = "Two Pairs"
    elif rank_multiplicities == [2]:
        hand_name = "One Pair"
    else:
        hand_name = ""

    return hand_name #This is only temporary--you also need the cards that make up the hand for breaking ties

hand = "5H5C6S7SKD"
print(hand)
print(get_hand_ranks(hand))
print(cardinalities(hand))
print(read_multiple_hands(hand))

hand = "5H5C6S7S6D"
print(hand)
print(get_hand_ranks(hand))
print(cardinalities(hand))
print(read_multiple_hands(hand))

hand = "5H5C6S6S6D"
print(hand)
print(get_hand_ranks(hand))
print(cardinalities(hand))
print(read_multiple_hands(hand))

