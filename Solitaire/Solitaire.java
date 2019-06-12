import java.util.*;

/**
 * This class maintains and runs the card game Solitaire.
 *
 * @author Annabelle Ju
 * @version 24 May 2019
 */
public class Solitaire
{
	public static void main(String[] args)
	{
		new Solitaire();
	}

	private Stack<Card> stock;
	private Stack<Card> waste;
	private Stack<Card>[] foundations;
	private Stack<Card>[] piles;
	private SolitaireDisplay display;

	public Solitaire()
	{
		foundations = new Stack[4];
		for (int i = 0; i < 4; i++){
			foundations[i] = new Stack<Card>();
		}

		piles = new Stack[7];
		for (int i = 0; i < 7; i++){
			piles[i] = new Stack<Card>();
		}

		stock = new Stack<Card>();
		waste = new Stack<Card>();
		display = new SolitaireDisplay(this);
		createStock();
		deal();
	}

	//returns the card on top of the stock,
	//or null if the stock is empty
	public Card getStockCard()
	{
		if (stock.size() == 0) return null;
		return stock.peek();
	}

	//returns the card on top of the waste,
	//or null if the waste is empty
	public Card getWasteCard()
	{
		if (waste.size() == 0) return null;
		return waste.peek();
	}

	//precondition:  0 <= index < 4
	//postcondition: returns the card on top of the given
	//               foundation, or null if the foundation
	//               is empty
	public Card getFoundationCard(int index)
	{
		if (foundations[index].size() == 0) return null;
		return foundations[index].peek();
	}

	//precondition:  0 <= index < 7
	//postcondition: returns a reference to the given pile
	public Stack<Card> getPile(int index)
	{
		return piles[index];
	}

	/**
	 * Initializes 52 cards, shuffles them, and adds them to the stock.
	 */
	public void createStock()
	{
		//create all 52 cards
		ArrayList<Card> deck = new ArrayList<>();
		String[] suits = new String[4];
		suits[0] = "c";
		suits[1] = "d";
		suits[2] = "h";
		suits[3] = "s";
		for (int i = 0; i < 4; i++){
			for (int j = 1; j < 14; j++){
				int rank = j;
				String suit = suits[i];
				deck.add(new Card(rank, suit));
			}
		}
		//shuffle and add into stock
		while (!deck.isEmpty()){
			int index = (int) (Math.random() * deck.size());
			stock.push(deck.remove(index));
		}
	}

	/**
	 * Deals cards from the stock into the seven piles.
	 * The first pile has 7 cards, the next has 6 cards, and so on.
	 * The top card of each pile should be face up.
	 */
	public void deal()
	{
		for (int i = 0; i < 7; i++){
			for (int j = 1; j <= i+1; j++){
				Card toAdd = stock.pop();
				if (j == i+1) toAdd.turnUp();
				piles[i].push(toAdd);
			}
		}
	}

	/**
	 * Moves the top three cards from the stock onto the waste.
	 * Turns all three of them face up.
	 */
	public void dealThreeCards()
	{
		int num = 3;
		if (stock.size() < 3) num = stock.size();
		for (int i = 0; i < num; i++){
			Card nextCard = stock.pop();
			nextCard.turnUp();
			waste.push(nextCard);
		}
	}

	/**
	 * Moves all cards in waste back onto the stock,
	 * turning all of the to face downwards.
	 */
	public void resetStock()
	{
		while (!waste.isEmpty()){
			Card nextCard = waste.pop();
			nextCard.turnDown();
			stock.push(nextCard);
		}
	}

	//precondition:  0 <= index < 7
	// postcondition: Returns true if the given card can be
	//               legally moved to the top of the given
	//               pile
	private boolean canAddToPile(Card card, int index)
	{
		if (piles[index].isEmpty()) {
			if (card.getRank() == 13) return true;
			return false;
		}
		Card top = piles[index].peek();
		int rank = top.getRank();
		String suit = top.getSuit();
		if (top.isFaceUp()){
			return card.getRank() == rank - 1 && card.isRed() != top.isRed();
		}
		return false;
	}

	//precondition:  0 <= index < 7
	// postcondition: Removes all face-up cards on the top of
	//               the given pile; returns a stack
	//               containing these cards
	private Stack<Card> removeFaceUpCards(int index)
	{
		Stack<Card> removed = new Stack<>();
		Stack<Card> pile = piles[index];
		while (!pile.isEmpty() && pile.peek().isFaceUp()){
			removed.push(pile.pop());
		}
		return removed;
	}

	//precondition:  0 <= index < 7
	// postcondition: Removes elements from cards, and adds
	//               them to the given pile.
	private void addToPile(Stack<Card> cards, int index)
	{
		Stack<Card> pile = piles[index];
		while (!cards.isEmpty()){
			pile.push(cards.pop());
		}
	}

	//called when the stock is clicked
	public void stockClicked()
	{
		if (display.isWasteSelected() || display.isPileSelected()) return;
		if (!stock.isEmpty()) dealThreeCards();
		else resetStock();
	}

	//called when the waste is clicked
	public void wasteClicked()
	{
		if (display.isWasteSelected()) display.unselect();
		else if (!waste.isEmpty() && !display.isPileSelected()){
			display.selectWaste();
		}
	}

	//precondition:  0 <= index < 4
	//called when given foundation is clicked
	public void foundationClicked(int index)
	{
		Card card = null;
		if (display.isWasteSelected()) card = waste.peek();
		else if (display.isPileSelected()) card = piles[display.selectedPile()].peek();
		if (card == null) return;
		if (canAddToFoundation(card, index)) {
			foundations[index].push(card);
			if (display.isWasteSelected()) waste.pop();
			else if (display.isPileSelected()) piles[display.selectedPile()].pop();
			display.unselect();
		}
	}

	//precondition:  0 <= index < 7
	//called when given pile is clicked
	public void pileClicked(int index)
	{
		if (display.isWasteSelected() && canAddToPile(waste.peek(), index)){
			piles[index].push(waste.pop());
			display.unselect();
		}
		else {
			int selected = display.selectedPile();
			if (selected < 0) {
				display.selectPile(index);
				if(!piles[index].isEmpty()) piles[index].peek().turnUp();
			}
			else if (selected == index) display.unselect();
			else { //move from selected to index
				Stack<Card> removed = removeFaceUpCards(selected);
				if (removed.isEmpty()) return;
				if (canAddToPile(removed.peek(), index)) addToPile(removed, index);
				else addToPile(removed, selected);
			}
		}
	}

	//precondition:  0 <= index < 4
	// postcondition: Returns true if the given card can be
	//               legally moved to the top of the given
	//               foundation
	private boolean canAddToFoundation(Card card, int index)
	{
		if (foundations[index].isEmpty()) return card.getRank() == 1;
		Card found = foundations[index].peek();
		String suit = found.getSuit();
		int rank = found.getRank();
		return card.getSuit() == suit && card.getRank() == rank + 1;
	}
}