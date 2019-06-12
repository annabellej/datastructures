/**
 * A Card represents a playing card for the game Solitaire. It has its own rank, suit,
 * and boolean to recall if it is facing up for the player to see. By default,
 * a Card is facing downwards.
 *
 * @author Annabelle Ju
 * @version 20 May 2019
 */
public class Card {
    //instance variables: characteristics of a Card
    private int rank; //from 1-13, 1 = Ace, 13 = King
    private String suit; //"c"=♣, "d"=♦, "h"=♥, and "s"=♠
    private boolean isFaceUp;

    public Card(int r, String s)
    {
        rank = r;
        suit = s;
        isFaceUp = false;
    }

    /**
     * Retrieves the rank of this Card.
     * @return the rank of this card.
     */
    public int getRank() {
        return rank;
    }

    /**
     * Retrieves the suit of this Card; either clubs, diamonds, hearts, or spades.
     * @return the suit of this card.
     */
    public String getSuit() {
        return suit;
    }

    /**
     * Determines whether this card is a red card, meaning
     * its suit is either hearts or diamonds.
     * @return  true    if this card's suit is either hearts or diamonds, else
     *          false   if this card's suit is not either hearts or diamonds.
     */
    public boolean isRed() {
        return suit.equals("d") || suit.equals("h");
    }

    /**
     * Determines whether this card is facing upwards.
     * @return  true    if this card is facing up, else
     *          false   if this card is facing down.
     */
    public boolean isFaceUp() {
        return isFaceUp;
    }

    /**
     * Turns this Card to be facing upwards.
     */
    public void turnUp() {
        isFaceUp = true;
    }

    /**
     * Turns this card to be facing downwards.
     */
    public void turnDown() {
        isFaceUp = false;
    }

    public String getFileName()
    {
        if (!isFaceUp) return "cards/back.jpg";
        String r;
        if (rank == 1) r = "a";
        else if (rank == 13) r = "k";
        else if (rank == 12) r = "q";
        else if (rank == 11) r = "j";
        else if (rank == 10) r = "t";
        else r = ((Integer) rank).toString();
        return "cards/" + r + suit + ".gif";
    }
}
