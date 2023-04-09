/**

 A Counter class to count the points in a Cribbage game.
 */
public class Counter {
    private PowerSet<Card> cardps; // A PowerSet to hold all subsets of the hand
    private Card starter; // The starter card for the round.

    /**

     Constructs a new Counter object with a given hand and starter card.
     @param hand an array of cards representing a whole hand.
     @param starter the starter card for the round.
     */
    public Counter(Card[] hand, Card starter) {
        this.starter = starter;
        cardps = new PowerSet<>(hand); // Initialize the PowerSet with the given hand.
    }

    /**

     Counts the total points in the hand.
     @return the total points.
     */
    public int countPoints() {
        int points = 0;
        points = points + countPairs();
        points += countRuns();
        points += flushCount();
        points += nobsCount();
        points += countSumFifteen();
        return points;
    }

    /**

     Counts the total points for pairs in the hand.
     @return the total points for pairs.
     */
    private int countPairs() {
        int points5 = 0;
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> set = cardps.getSet(i);
            if (set.getLength() == 2 && set.getElement(0).getLabel().equals(set.getElement(1).getLabel())) {
                points5 += 2; // Add 2 points for each pair.
            }
        }
        return points5;
    }
    /**

     Counts the number of sets that add up to 15 points and returns the number of points earned.
     @return the number of points earned from sets that add up to 15
     */
    private int countSumFifteen() {
        int points4 = 0;
        int sum = 0;

        // Iterate through all the sets in the power set of cards.
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> set = cardps.getSet(i);
            sum = 0;
            Card card;

        // Iterate through all the cards in the set and sum up their ranks.
            for (int j = 0; j < set.getLength(); j++) {
                card = set.getElement(j);
                sum += card.getFifteenRank();
            }

        // Check if the sum of the ranks is 15 and increment the points accordingly.
            if (sum == 15) {
                points4 += 2;
            }
        }
        // Return the total number of points earned from sets that add up to 15.
        return points4;
    }

    // This method counts the number of points for having a flush in the hand.
    private int flushCount() {
        int points3 = 0;
        Card card2;
        Card card4;
        Card card5;
        Card card6;
        int count5 = 0;
        int count4 = 0;

        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> set2 = cardps.getSet(i);
            if (set2.getLength() == 5) {
                card2 = set2.getElement(0);
                String storeSuite = card2.getSuit();
                // Check if all cards in the set have the same suit.
                for (int j = 1; j < set2.getLength(); j++) {
                    card4 = set2.getElement(j);
                    if (storeSuite.equals(card4.getSuit())) {
                        count5++;
                    }
                }
                if (count5 == 4) {
                    points3 += 5; // If all cards have the same suit, add 5 points.
                    break;
                } else {
                    count5 = 0; // Reset the counter if the set doesn't contain a flush.
                }
            }
        }

        // Check for 4-card flush.
        if (points3 != 5) {
            for (int i = 0; i < cardps.getLength(); i++) {
                Set<Card> set2 = cardps.getSet(i);
                if (set2.getLength() == 4) {
                    card5 = set2.getElement(0);
                    String storeSuite = card5.getSuit();
                    for (int k = 1; k < set2.getLength(); k++) {
                        card6 = set2.getElement(k);
                        if (storeSuite.equals(card6.getSuit())) {
                            count4++;
                        }
                    }
                    if (count4 == 3) {
                        points3 += 4; // If all 4 cards have the same suit, add 4 points.
                        break;
                    } else {
                        count4 = 0; // Reset the counter if the set doesn't contain a 4-card flush.
                    }
                }
            }
        }

        return points3;

    }

    // This method counts the number of points for having a jack of the same suit as the starter card.
    private int nobsCount() {
        int points2 = 0;
        Card cardNob;
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> set2 = cardps.getSet(i);
            if (set2.getLength() == 5) {
        // Check if any card in the set is a jack with the same suit as the starter.
                for(int j = 0; j < set2.getLength(); j++){
                    cardNob = set2.getElement(j);
                    if (cardNob.getRunRank() == 11) {
                        if (cardNob.getSuit().equals(starter.getSuit())) {
                            points2 += 1; // If there is a jack with the same suit as the starter, add 1 point.
                        }
                    }
                }
            }
        }
        return points2;
    }

    /**

     Counts the total points for runs in the hand.
     @return the total points for runs.
     */
    private int countRuns(){
        int points1 = 0;
        int maxStreak2 = 0;
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> set2 = cardps.getSet(i);
            if (isRun(set2)) {
                if (set2.getLength() > maxStreak2) {
                    maxStreak2 = set2.getLength(); // Keep track of the length of the longest run.
                }
            }
        }
        for (int i = 0; i < cardps.getLength(); i++) {
            Set<Card> set3 = cardps.getSet(i);
            if (isRun(set3) && set3.getLength() == maxStreak2) { // Only check the runs of the "longest run" length.
                points1 += set3.getLength(); // The points are equal to the number of cards in the run.
            }
        }
        return points1;
    }
    /**

     Determines if a given set of cards is a run.
     @param set the set of cards to check.
     @return true if the set is a run, false otherwise.
     */
    private boolean isRun (Set<Card> set) {

        int n = set.getLength();

        if (n <= 2) return false; // Run must be at least 3 in length.

        // Create an array to store the frequency of each rank in the set.
        // The index of the array represents the rank of the card minus one (to fit the range 0-12).
        int[] rankArr = new int[13];
        for (int i = 0; i < 13; i++) rankArr[i] = 0; // Ensure the default values are all 0.

        // Increment the count of the card rank in the array for each card in the set.
        for (int i = 0; i < n; i++) {
            rankArr[set.getElement(i).getRunRank()-1] += 1;
        }

        // Now search in the array for a sequence of n consecutive 1's.
        int streak = 0;
        int maxStreak = 0;
        for (int i = 0; i < 13; i++) {
            if (rankArr[i] == 1) {
                streak++;
                if (streak > maxStreak) maxStreak = streak;
            } else {
                streak = 0;
            }
        }
        // Check if this is the maximum streak.
        return maxStreak == n;

    }
    }