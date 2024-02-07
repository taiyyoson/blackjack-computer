import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

//class Card takes cards and analyzes, returns num value
class Card {
	private int cardNumValue;

	//constructor
	Card (String setCardValue) {
		//split 1 string into char array
		char[] cardArray = setCardValue.toCharArray();
		//get card value
		if (cardArray[1] == '0' || Character.toLowerCase(cardArray[0]) == 'j' || Character.toLowerCase(cardArray[0]) == 'q' || Character.toLowerCase(cardArray[0]) == 'k') {
			cardNumValue = 10;
		}
		else if (Character.toLowerCase(cardArray[0]) == 'a') {
			cardNumValue = 11;
		}
		else {
			String valuetoString = "";
			valuetoString += cardArray[0];
			cardNumValue = Integer.parseInt(valuetoString);
		}

	}
	//getter method, returns number value
	public int value() {
		return cardNumValue;
	}

}

class CardCounting {
	String betAmt;
	CardCounting (int[] allCards, int bal) {
		int bettingUnit = 0;
		int runningCount = 0;
		int trueCount = 0;
		int numBetAmt = 0;
		if (bal <= 375) {
			bettingUnit = 1;
		}
		else if (bal > 375) {
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
		trueCount = (int) (runningCount / deckCount);
		if (trueCount > 1) {
			numBetAmt = (trueCount - 1) * bettingUnit;
		}
		else {
			numBetAmt = bettingUnit;
		}
		
		betAmt = Integer.toString(numBetAmt);
		
	}
	
	public String getBetAmt() {
		return betAmt;
	}
}


class BasicStrategy {
	public String playCmd;
	BasicStrategy (int dealerUpCard, int[] myHand) {
		int cardTotal = 0;
		for (int i=0;i<myHand.length;i++) {
			cardTotal += myHand[i];
		}
		
		//BLACKJACK!!
		if (cardTotal == 21) {
			playCmd = "stand";
		}
		
		//split chart strategies
		else if (myHand[0] == myHand[1]) {
			switch(dealerUpCard) {
				case 2:
					if ( myHand[0] == 4 || myHand[0] == 5) {
						playCmd = "hit";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "split";
					}
					break;
				case 3:
					if ( myHand[0] == 4 || myHand[0] == 5) {
						playCmd = "hit";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "split";
					}
					break;
				case 4:
					if ( myHand[0] == 4 || myHand[0] == 5) {
						playCmd = "hit";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "split";
					}
					break;
				case 5:
					if (myHand[0] == 5) {
						playCmd = "hit";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "split";
					}
					break;
				case 6:
					if (myHand[0] == 5) {
						playCmd = "hit";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "split";
					}
					break;
				case 7:
					if ( myHand[0] == 4 || myHand[0] == 5 || myHand[0] == 6) {
						playCmd = "hit";
					}
					else if (myHand[0] == 10 || myHand[0] == 9) {
						playCmd = "stand";
					}
					else {
						playCmd = "split";
					}
					break;
				case 8:
					if (myHand[0] == 8 || myHand[0] == 9 || myHand[0] == 11) {
						playCmd = "split";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 9:
					if (myHand[0] == 8 || myHand[0] == 9 || myHand[0] == 11) {
						playCmd = "split";
					}
					else if (myHand[0] == 10) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 10:
					if (myHand[0] == 8 || myHand[0] == 11) {
						playCmd = "split";
					}
					else if (myHand[0] == 10 || myHand[0] == 9) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 11:
					if (myHand[0] == 8 || myHand[0] == 11) {
						playCmd = "split";
					}
					else if (myHand[0] == 10 || myHand[0] == 9) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
					
			}
		}
		
		//fix soft hand if total exceeds 21, turn to stiff hand
		if (cardTotal > 21) {
			cardTotal -= 10;
			for (int i=0; i<myHand.length;i++) {
				if (myHand[i] == 11) {
					myHand[i] = 1;
					break;
				}
			}
		}
		
		//soft total chart strategies
		else if (myHand[0] == 11 || myHand[1] == 11) {
			
			switch(dealerUpCard) {
				case 2:
					if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else if (cardTotal == 18) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 3:
					if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else if (cardTotal == 18 || cardTotal == 17) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 4:
					if (cardTotal == 13 || cardTotal == 14) {
						playCmd = "hit";
					}
					else if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "double";
					}
					break;
				case 5:
					if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "double";
					}
					break;
				case 6:
					if (cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "double";
					}
					break;
				case 7:
					if (cardTotal == 18 || cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 8:
					if (cardTotal == 18 || cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 9:
					if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 10:
					if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 11:
					if (cardTotal == 19 || cardTotal == 20) {
						playCmd = "stand";
					}
					else {
						playCmd = "hit";
					}
					break;
			}
		}
		
		
		else if (cardTotal >= 18) {
			playCmd = "stand";
		}
		//hard total chart strategies
		else {
			switch(dealerUpCard) {
				case 2:
					if (cardTotal == 8 || cardTotal == 9 || cardTotal == 12) {
						playCmd = "hit";
					}
					else if (cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "stand";
					}
					break;
				case 3:
					if (cardTotal == 8 || cardTotal == 12) {
						playCmd = "hit";
					}
					else if (cardTotal == 9 || cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "stand";
					}
					break;
				case 4:
					if (cardTotal == 8) {
						playCmd = "hit";
					}
					else if (cardTotal == 9 || cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "stand";
					}
					break;
				case 5:
					if (cardTotal == 8) {
						playCmd = "hit";
					}
					else if (cardTotal == 9 || cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "stand";
					}
					break;
				case 6:
					if (cardTotal == 8) {
						playCmd = "hit";
					}
					else if (cardTotal == 9 || cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "stand";
					}
					break;
				case 7:
					if (cardTotal == 17) {
						playCmd = "stand";
					}
					if (cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 8:
					if (cardTotal == 17) {
						playCmd = "stand";
					}
					if (cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 9:
					if (cardTotal == 17) {
						playCmd = "stand";
					}
					if (cardTotal == 10 || cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 10:
					if (cardTotal == 17) {
						playCmd = "stand";
					}
					if (cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
				case 11:
					if (cardTotal == 17) {
						playCmd = "stand";
					}
					if (cardTotal == 11) {
						playCmd = "double";
					}
					else {
						playCmd = "hit";
					}
					break;
			}
		}
		
	}
	
	public String getCmd() {
		return playCmd;
	}
}



class Blackjack {
	private String read(Socket n) throws IOException {
		DataInputStream dis = new DataInputStream(n.getInputStream());
		return dis.readUTF();
	}
	private void write(Socket n, String s) throws IOException {
		DataOutputStream dos = new DataOutputStream(n.getOutputStream());
		dos.writeUTF(s);
		dos.flush();
	}
	
	public static void main (String[] args) throws IOException { 
		Blackjack cmdObj = new Blackjack();
		String IpAddress = args[0];
		String IpPort = args[1];
		Socket socket = new Socket(IpAddress, Integer.valueOf(IpPort));
		String cmd;
		String cmdSwitch;
		int accountBal;
		

		int cont = 1;
		while (cont != 0) {
			cmd = cmdObj.read(socket);
			cmdSwitch = cmd.substring(0,3);
			
			
			switch (cmdSwitch) {
			
				case "log":
					cmdObj.write(socket, "taiyosson:taiyo");
					break;
				
					
				case "bet":
					String[] cardsPlayed = cmd.split(":");
					accountBal = Integer.parseInt(cardsPlayed[1]);
					
					int[] cardList = new int[cardsPlayed.length - 3];
					for (int i=0; i<cardList.length;i++) {
						Card card = new Card(cardsPlayed[i+3]);
						cardList[i] = card.value();
					}
					
					CardCounting betObj = new CardCounting(cardList, accountBal);
					cmdObj.write(socket, "bet:" + betObj.getBetAmt());
					break;
					
					
				case "pla":
					String[] playCmd = cmd.split(":");
					
					Card dealerCard = new Card(playCmd[2]);
					int dealerUp = dealerCard.value();
					
					
					
					int cardCount = 0;
					int[] cardHand = new int[playCmd.length - 4];
					for (int i=4;i<playCmd.length;i++) {
						Card playerCard = new Card(playCmd[i]);
						cardHand[cardCount] = playerCard.value();	
						cardCount++;
					}
					
					BasicStrategy playObj = new BasicStrategy(dealerUp, cardHand);
					cmdObj.write(socket, playObj.getCmd());
					break;
					
					
				case "sta":
					System.out.println(cmd);
					break;
			}
			
			if (cmdSwitch.equals("don")) {
				System.out.println(cmd);
				break;
			}
		}
		socket.close();
		
		
	}
}




















