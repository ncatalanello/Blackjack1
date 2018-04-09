import java.util.ArrayList;
import java.util.Scanner;
/**
 * Write a description of class Game here.
 *
 * @author (catalanello)
 * @version (a version number or a date)
 */
public class Game
{
    private Player p;
    private Player d;
    private Deck deckOfCards;   
    /**
     * Constructor for objects of class Game
     */
    public Game()
    {
        this.deckOfCards = new Deck();
        p = new Player("p", 100);
        d = new Dealer();
        
        Line0();
        playGame();
    }
    
    public void playGame(){
        this.deckOfCards.initializeDeck();
        this.deckOfCards.shuffle();
        int b = p.bet();      
        this.p.hand = this.deckOfCards.deal();
        this.d.hand = this.deckOfCards.deal();    
        playerTurn();
        dealerTurn();       
        int win = Winner();     
        if(win == -1){
            Line4();
            System.out.println("\nYou have " + p.getMoney() + " dollars");
        }
        else if(win == 0){
            Line3();
            p.addMoney(b);
            System.out.println("\nYou have " + p.getMoney() + " dollars");
        }
        else{
            Line2();
            p.addMoney(2*b);
            System.out.println("\nYou have " + p.getMoney() + " dollars");
        }     
        System.out.println("Do you want to play another game? (y/n)");
        Scanner scan = new Scanner(System.in);
        String cont = scan.nextLine();       
        if(cont.equals("y")){
            playGame();
        }
        else{
            endGame();
        }        
    }    
    public int Winner(){
        int pTotal = p.handTotal();
        int dTotal = d.handTotal();        
        // if 1 player wins, if -1 dealer wins, if 0 tie
        int winner = 0;                
        if(pTotal > 21 && dTotal <= 21){
            winner = -1;
        }
        else if(dTotal > 21 && pTotal <= 21){
            winner = 1;
        }
        else if(pTotal <= 21 && dTotal <= 21){
            if(pTotal > dTotal){
                winner = 1;
            }
            else{
                winner = -1;
            }
        }        
        return winner;
    }    
    public void endGame(){
        Line5();
    }   
    public void Line0(){
        System.out.println("Welcome to BlackJack");
        System.out.println("\nYou have " + p.getMoney() + " dollars");
        System.out.println();
    }    
    public void Line1(){
        printPlayerHand();        
        System.out.println("\nThe dealer is showing: ");
        System.out.println(d.hand.get(0));        
        System.out.println("\nChoose one of the following:");
        System.out.println("hit");
        System.out.println("stand");
    }    
    public void Line2(){
        System.out.println("\nYou win");
    }   
    public void Line3(){
        System.out.println("\nIt's a tie");
    }    
    public void Line4(){
        System.out.println("\nThe dealer wins");
    }    
    public void Line5(){
        System.out.println("Your final amount of money " + + p.getMoney() + " dollars");
        System.out.println();
        System.out.println("Good Game");
    }    
    public void printPlayerHand(){
        System.out.println("\nYour hand is: ");
        for(Card c : p.hand){
            System.out.println(c);
        }
    }    
    public void playerTurn(){
        while(true){
            if(p.handTotal() >= 21){
                printPlayerHand();
                System.out.println("\nThe dealer is showing: ");
                System.out.println(d.hand.get(0));
                break;
            }           
            Line1();            
            Scanner scan = new Scanner(System.in);
            String choice = scan.nextLine();            
            if(choice.equals("hit")){
                this.p.hand.add(deckOfCards.getTopCard());
            }else{
                break;
            }
        }
    }
    public void dealerTurn(){
        while(d.handTotal() < 17){
            this.d.hand.add(deckOfCards.getTopCard());
        }        
        System.out.println("\nThe dealer has: ");
        for(Card c : d.hand){
            System.out.println(c);
        }
    }    
    public void showHands(){
        System.out.println("Player");
        for(Card c: p.hand){
            System.out.println(c);
        }
        System.out.println("Dealer");
        for( Card c: d.hand){
            System.out.println(c);
        }
    }
}