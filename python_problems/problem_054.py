#
# Project Euler: Problem 54 - Poker Hands
# 
# Need to compare two poker hands. Ties broken by successive high card
#
# Each hand reader needs to return the name of the hand
#

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

def rank_score(r):
    if r in map(str, [2, 3, 4, 5, 6, 7, 8, 9, 10]):
        return int(r)
    else:
        return {"J":11, "Q":12, "K":13, "A":14}[r]


#print(hand_score["Full House"])
#print(hand_score["Full House"] > hand_score["Two Pairs"])

def get_hand_ranks(hand):
    return [hand[2*i] for i in range(5)]

def get_scored_hand_ranks(hand):
    ranks = get_hand_ranks(hand)
    scored_ranks = map(rank_score, ranks)
    return sorted(list(scored_ranks), reverse=True)

def get_hand_suits(hand):
    return [hand[2*i + 1] for i in range(5)]

def cardinalities(hand):
    ranks = get_hand_ranks(hand)
    cardinalities = { r : ranks.count(r) for r in ranks}
    return cardinalities

def read_multiple_hand(hand):
    cardinal = cardinalities(hand)
    significant_ranks = list(filter(lambda x: cardinal[x] > 1, cardinal))
    significant_ranks = sorted(significant_ranks, reverse=True, key=lambda r : cardinal[r]*10 + rank_score(r))

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

    return hand_name

def check_straight(hand):
    ranks = sorted(list(map(rank_score, get_hand_ranks(hand))))
    diffs = [ranks[i+1] - ranks[i] for i in range(len(ranks)-1)]
    return list(set(diffs)) == [1]

def check_flush(hand):
    suits = get_hand_suits(hand)
    return len(list(set(suits))) == 1

def read_hand(hand):
    multiples = read_multiple_hand(hand)
    if multiples != "":
        return multiples
    else:
        #Check for straights, flushes, royal flush
        if check_flush(hand) and check_straight(hand):
            return "Royal Flush"
        elif check_straight(hand):
            return "Straight"
        elif check_flush(hand):
            return "Flush"
        return "High Card"


hand_list = ["2H4H6S8CQC", "5H5C6S7SKD", "5H5C6S7S6D", "5H5C5S6S6D", "2H4H7HKH8H", "2H3C4S5S6C"]
for hand in hand_list:
    print(hand)
    print(get_hand_ranks(hand))
    print(get_scored_hand_ranks(hand))
    print(get_hand_suits(hand))
    print(cardinalities(hand))
    print(read_hand(hand))
    print()

