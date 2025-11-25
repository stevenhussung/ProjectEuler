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
    if r in map(str, [2, 3, 4, 5, 6, 7, 8, 9]):
        return int(r)
    else:
        return {"T":10, "J":11, "Q":12, "K":13, "A":14}[r]

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
    return { r : ranks.count(r) for r in ranks}

def get_significant_ranks(hand):
    cardinal = cardinalities(hand)
    significant_ranks = list(filter(lambda x: cardinal[x] > 1, cardinal))
    significant_ranks = sorted(significant_ranks, reverse=True, key=lambda r : cardinal[r]*100 + rank_score(r))
    return significant_ranks


def read_multiple_hand(hand):
    cardinal = cardinalities(hand)
    significant_ranks = get_significant_ranks(hand)

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
            #Check for royal
            high_card = get_scored_hand_ranks(hand)[0]
            if high_card == rank_score("A"):
                return "Royal Flush"
            else:
                return "Straight Flush"
        elif check_straight(hand):
            return "Straight"
        elif check_flush(hand):
            return "Flush"
        return "High Card"

def successive_high_card(hand_1, hand_2):
    ranks_1 = get_scored_hand_ranks(hand_1)
    ranks_2 = get_scored_hand_ranks(hand_2)

    for r_1, r_2 in zip(ranks_1, ranks_2):
        if r_1 > r_2:
            return 1
        if r_1 < r_2:
            return 2
    
    #All ties
    return 0

def tiebreaker(hand_1, hand_2):
    #Hand types are equal
    hand_type = read_hand(hand_1)
    sig_ranks_1 = get_significant_ranks(hand_1)
    sig_ranks_2 = get_significant_ranks(hand_2)

    if sig_ranks_1 != []:
        if rank_score(sig_ranks_1[0]) > rank_score(sig_ranks_2[0]):
            return (1, 'Hand Rank')
        elif rank_score(sig_ranks_1[0]) < rank_score(sig_ranks_2[0]):
            return (2, 'Hand Rank')
        else:
            return (successive_high_card(hand_1, hand_2), "High Card")
    else:
        return (successive_high_card(hand_1, hand_2), "High Card")

def compare_hands(hand_1, hand_2):
    #Compare hand types
    hand_1_type_score = hand_score[read_hand(hand_1)]
    hand_2_type_score = hand_score[read_hand(hand_2)]

    if hand_1_type_score > hand_2_type_score:
        return (1, "Hand Type")
    elif hand_1_type_score < hand_2_type_score:
        return (2, "Hand Type")
    else:
        return tiebreaker(hand_1, hand_2)


# Test cases
if False:
    #hand_list = ["2H4H6S8CQC", "5H5C6S7SKD", "5H4C6S7S6D", "5H5C5S6S6D", "2H4H7HKH8H", "2H3C4S5S6C"]
    hand_list = ["TH8H5CQSTC", "9H4DJCKSJS"]
    for hand in hand_list:
        print(hand)
        print(get_hand_ranks(hand))
        print(get_scored_hand_ranks(hand))
        print(get_hand_suits(hand))
        print(cardinalities(hand))
        print(get_significant_ranks(hand))
        print(read_hand(hand))
        print()

    for hand_1 in hand_list:
        for hand_2 in hand_list:
            print(hand_1, read_hand(hand_1))
            print(hand_2, read_hand(hand_2))
            print(compare_hands(hand_1, hand_2))
            print()

print(" - - - Reading file - - - ")

with open("data_054.txt", "r") as datafile:
    poker_result = []
    hand_type_result = []
    for line in datafile:
        hand_1 = "".join(line.split(" ")[:5]).strip()
        hand_2 = "".join(line.split(" ")[5:]).strip()
        poker_result.append(compare_hands(hand_1, hand_2))
        hand_type_result.append(read_hand(hand_1))
        hand_type_result.append(read_hand(hand_2))
        if False:
            if compare_hands(hand_1, hand_2)[1] in ["Hand Rank"]:
                print(hand_1, read_hand(hand_1))
                print(hand_2, read_hand(hand_2))
                print(compare_hands(hand_1, hand_2))
                print()

    wins_1 = len(list(filter(lambda result : result[0] == 1, poker_result)))
    wins_2 = len(list(filter(lambda result : result[0] == 2, poker_result)))
    ties =   len(list(filter(lambda result : result[0] == 0, poker_result)))
    print("Number of wins for hand 1:", wins_1)
    print("Number of wins for hand 2:", wins_2)
    print("Number of ties:", ties)

    #Hand type analysis
    for hand_type in hand_score:
        print(hand_type, " appeared ", hand_type_result.count(hand_type), "times, which is", f"{hand_type_result.count(hand_type) / 2000 * 100:.2f}", "%")
