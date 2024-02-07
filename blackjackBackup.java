import java.net.Socket;
import java.io.IOException;
import java.io.DataInputStream;
import java.io.DataOutputStream;

class Card {
	private int cardNumValue;
	private char charSuit;

	//constructor, take 1 string as argument
	Card (String setCardValue) {
		//split 1 string into char array
		char[] cardArray = setCardValue.toCharArray();
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

		//setting card Suits
		//if index 1 has value of 0 (meaning number '10')
		if (cardArray[1] == '0') {
			charSuit = cardArray[2];
		}
		else {
			charSuit = cardArray[1];
		}
	}
	//getter method, returns number value
	public int value() {
		return cardNumValue;
	}
	//getter method, returns card suit
	public String suit() {
		String strSuit = "";
		strSuit += Character.toUpperCase(charSuit);
		return strSuit;
	}
}


class blackjackBackup {
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
		blackjackBackup cmdObj = new blackjackBackup();
		String IpAddress = args[0];
		String IpPort = args[1];
		Socket socket = new Socket(IpAddress, Integer.valueOf(IpPort));
		String cmd;
		String cmdSwitch;
		int accountBal;
		

		int count = 1;
		while (count != 0) {
			cmd = cmdObj.read(socket);
			cmdSwitch = cmd.substring(0,3);
			
			
			switch (cmdSwitch) {
			
				case "log":
					cmdObj.write(socket, "taiyosson:taiyo");
					break;
				
				case "bet":
					String[] cardsPlayed = cmd.split(":");
					accountBal = Integer.parseInt(cardsPlayed[1]);
					cmdObj.write(socket, "bet:15");
					break;
					
				case "pla":
					String[] playCmd = cmd.split(":");
					
					Card dealerCard = new Card(playCmd[2]);
					int dealerUp = dealerCard.value();
					
					
					int myCards = 0;
					int cardHandCount = 0;
					int[] cardHand = new int[playCmd.length - 4];
					for (int i=4;i<playCmd.length;i++) {
						Card playerCard = new Card(playCmd[i]);
						myCards += playerCard.value();
						cardHand[cardHandCount] = playerCard.value();	
						cardHandCount++;
						
					}
					
					if (cardHand[0] == cardHand[1] && cardHand[0] < 9) {
						cmdObj.write(socket, "split");
					}
					else if (myCards < 17) {
						cmdObj.write(socket, "hit");
					}
					else if(myCards >= 17) {
						cmdObj.write(socket, "stand"); 
					}
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




















