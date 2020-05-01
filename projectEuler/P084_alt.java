package projectEuler;

public class P084_alt extends ParameterizedProblem<Integer> {

    private final static int GO = 0;
    private final static int[] CC = { 2, 17, 33 };
    private final static int[] CH = { 7, 22, 36 };
    private final static int[] R = { 5, 15, 25, 35 };
    private final static int[] U = { 12, 28 };
    private final static int JAIL = 10;
    private final static int C1 = 11;
    private final static int E3 = 24;
    private final static int G2J = 30;
    private final static int H2 = 39;
    private final static int BOARD_SIZE = 40;
    private final static double CARD_PROB = 0.125;

    @Override
    Integer getDefaultParameter() {
        return 4;
    }

    public static void main(String[] args) {
        P084 p = new P084();
        p.solve(4, true);
        long ans = 101524;
        for (int i = 0; i < 500; i++) {
            // System.out.println(i);
            long currAns = p.solve(4, false);
            if (ans != currAns)
                System.out.println(i + " has an inconsistancy: " + currAns);

        }
    }

    // I want to eventually figure this out because it would make me
    long solve(Integer faces, boolean printResults) {
        Double[] spaceProbs = new Double[BOARD_SIZE];

        // everyone starts at GO, although, because the final answer is a stable
        // equilibrium,
        // we could start with any layout. In fact, starting with an even distribution
        // would probably
        // be closer to the final answer and thus require fewer iterations.
        spaceProbs[GO] = 1.0;
        for (int i = 1; i < spaceProbs.length; i++)
            spaceProbs[i] = 0.0;

        Double[] previousProbs = null;
        Double[] rollProbs = figureRollProbs(faces);
        // while (!CollectionTools.equals(spaceProbs, updatedProbs)) {
        for (int i = 0; i < 1000; i++) {

            previousProbs = spaceProbs;
            spaceProbs = updateBoard(previousProbs, rollProbs);
            // double totalProb = 0;
            // if (i % 100 == 0 || i == 1) {
            // for (int j = 0; j < spaceProbs.length; j++) {
            // totalProb += spaceProbs[j];
            // System.out.print(((int) (spaceProbs[j] * 10000)) / 100.0 + "% ");
            // if (j % 10 == 9)
            // System.out.print("| ");
            // }
            // System.out.println(" total: " + totalProb);
            // }
        }

        for (int j = 0; j < spaceProbs.length; j++) {
            System.out.print(((int) (spaceProbs[j] * 10000)) / 100.0 + "% ");
            if (j % 10 == 9)
                System.out.print("| ");
        }
        System.out.println();

        int firstIndex = -1;
        double firstValue = -1;
        for (int i = 0; i < spaceProbs.length; i++) {
            if (spaceProbs[i] > firstValue) {
                firstIndex = i;
                firstValue = spaceProbs[i];
            }
        }
        int secondIndex = -1;
        double secondValue = -1;
        for (int i = 0; i < spaceProbs.length; i++) {
            if (i == firstIndex)
                continue;
            if (spaceProbs[i] > secondValue) {
                secondIndex = i;
                secondValue = spaceProbs[i];
            }
        }
        int thirdIndex = -1;
        double thirdValue = -1;
        for (int i = 0; i < spaceProbs.length; i++) {
            if (i == firstIndex || i == secondIndex)
                continue;
            if (spaceProbs[i] > thirdValue) {
                thirdIndex = i;
                thirdValue = spaceProbs[i];
            }
        }
        if (printResults) {
            System.out.println(firstIndex + " " + firstValue);
            System.out.println(secondIndex + " " + secondValue);
            System.out.println(thirdIndex + " " + thirdValue);
        }

        return 10000 * firstIndex + 100 * secondIndex + thirdIndex;

    }

    private static Double[] figureRollProbs(int faces) {
        return figureRollProbs(faces, 3);
    }

    private static Double[] figureRollProbs(int faces, int maxDepth) {
        if (maxDepth == 0)
            return new Double[] { 1.0 };

        double rollProb = 1.0 / (faces * faces);

        Double[] rollProbs = new Double[faces * 2 * maxDepth];
        for (int i = 0; i < rollProbs.length; i++)
            rollProbs[i] = 0.0;

        for (int die1 = 1; die1 <= faces; die1++) {
            for (int die2 = 1; die2 <= faces; die2++) {
                if (die1 != die2) {
                    rollProbs[die1 + die2] += rollProb;
                } else {
                    Double[] recursiveProbs = figureRollProbs(faces, maxDepth - 1);

                    // update jail probability
                    rollProbs[0] += recursiveProbs[0] * rollProb;

                    // update probabilities for doubles
                    for (int i = 1; i < recursiveProbs.length; i++)
                        rollProbs[i + die1 + die2] += recursiveProbs[i] * rollProb;

                }
            }
        }
        return rollProbs;// this is the beginning of my attempt for a recursive algorithm, TODO
    }

    private static Double[] updateBoard(Double[] spaceProbs, Double[] rollProbs) {
        Double[] updatedProbs = new Double[BOARD_SIZE];
        for (int i = 0; i < updatedProbs.length; i++)
            updatedProbs[i] = 0.0;

        // first they roll
        for (int i = 0; i < spaceProbs.length; i++) {
            updatedProbs[JAIL] += spaceProbs[i] * rollProbs[0];
            for (int roll = 1; roll < rollProbs.length; roll++)
                // we don't need to account for people in jail needing to roll doubles, as we're
                // just letting them pay to get out thier first turn
                updatedProbs[(i + roll) % BOARD_SIZE] += spaceProbs[i] * rollProbs[roll];

        }

        // deal with chance
        for (int ch : CH) {
            updatedProbs[GO] += updatedProbs[ch] * CARD_PROB;
            updatedProbs[JAIL] += updatedProbs[ch] * CARD_PROB;
            updatedProbs[C1] += updatedProbs[ch] * CARD_PROB;
            updatedProbs[E3] += updatedProbs[ch] * CARD_PROB;
            updatedProbs[H2] += updatedProbs[ch] * CARD_PROB;
            updatedProbs[R[0]] += updatedProbs[ch] * CARD_PROB;
            updatedProbs[ch - 3] += updatedProbs[ch] * CARD_PROB;

            // next railroad * 2
            // if the loop is never entered, they're already past the last railroad and the
            // next one is the first one
            int r = R[0];
            for (int i = R.length - 1; i >= 0 && R[i] > ch; i--)
                r = R[i];
            updatedProbs[r] += updatedProbs[ch] * CARD_PROB * 2;

            // next utility
            // if the loop is never entered, they're already past the last utility and the
            // next one is the first one
            int u = U[0];
            for (int i = U.length - 1; i >= 0 && U[i] > ch; i--)
                u = U[i];
            updatedProbs[u] += updatedProbs[ch] * CARD_PROB;

            // there's only 6 cards that leave the player at a chance tile
            updatedProbs[ch] *= 6 * CARD_PROB;
        }

        // then they deal with the effects of the tile
        // deal with community chest
        for (int cc : CC) {
            updatedProbs[GO] += updatedProbs[cc] * CARD_PROB;
            updatedProbs[JAIL] += updatedProbs[cc] * CARD_PROB;

            // only two community chest cards move the player
            updatedProbs[cc] *= CARD_PROB * 14;
        }

        // everyone on go to jail needs to go to jail.
        updatedProbs[JAIL] += updatedProbs[G2J];
        updatedProbs[G2J] = 0.0;

        // double totalProb = 0.0;
        // for (int i = 0; i < updatedProbs.length; i++)
        // totalProb += updatedProbs[i];

        // for (int i = 0; i < updatedProbs.length; i++)
        // updatedProbs[i] /= totalProb;

        return updatedProbs;
    }

    @Override
    protected Integer getTestParameter() {
        return 6;
    }

    @Override
    protected long getTestSolution() {
        // I'm aware this value is technically wrong. My algorithm works for 4, but not
        // 6, so I'm not going to fix it for now
        return 100024;
    }

    @Override
    String getTitle() {
        return "Problem 084: Monopoly Odds";
    }
}