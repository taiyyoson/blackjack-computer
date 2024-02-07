

class testBlackjack {
	public static void main(String[] java) {
		
		int bettingUnit = 0;
		int runningCount = 0;
		int trueCount;
		if (bal < 100) {
			bettingUnit = 1;
		}
		else if (bal >= 100) {
			bettingUnit = 2;
		}
		
		for (int i=0; i<allCards.length;i++) {
			if (allCards[i] < 7) {
				runningCount++;
			}
			else if (allCards[i] > 9) {
				runningCount--;
			}
		}
		
		double deckCount = ((364 - allCards.length) / 52);
		trueCount = (int) Math.round(runningCount / deckCount);
		int numBetAmt = (trueCount - 1) * bettingUnit;
		betAmt = Integer.toString(numBetAmt);
	}
















