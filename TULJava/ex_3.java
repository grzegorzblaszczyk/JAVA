import java.util.Random;
import java.util.Scanner;
	
public class ex_3
{
	/*public static void cls()
	{
		try
		{
			Runtime.getRuntime().exec("clear");
		}
		catch(final Exception e){}
	}*/
	public static int getRandomInt()
	{
		Random generator = new Random();
		int n = generator.nextInt(100);
		//System.out.println(n);
		return n;
	}
	public static void main(String[] args)
	{	
		Scanner read = new Scanner(System.in);
		boolean playGame=false, winner;
		char endGame, giveUp;
		int fail_count, jackpot, luckyShot;
		
		do
		{
		winner = false;
		fail_count =0;
		jackpot = getRandomInt();
		luckyShot = 0;
		
			while(!winner)
			{
				//cls();
				System.out.print("Gimme Your LuckyShot! >> ");
				while(!read.hasNextInt()) read.next();
				luckyShot = read.nextInt();
				if(luckyShot == jackpot)
				{	
					System.out.println("YES - YOU WON! CONGRATZ! Attempts: "+(++fail_count));
					winner = true;
				}
				else
				{
					System.out.println("Ahh damn! Wrong!");
					if(luckyShot>jackpot)
						System.out.print("(try lower!) ");
					else
						System.out.print("(try higher!) ");
					System.out.println("[Attempts: "+(++fail_count)+"]");
				}
			}
			System.out.print("Do you wish to continue? ([Y]es/[N]o) >>");
			do
			{
				endGame = read.next().charAt(0); //.charAt(0) to read exactly 1 token;
				switch(endGame)
				{
					case 'N': {playGame = true; fail_count--; break;}
					default: break;
				}
			}while(endGame!='Y' && endGame!='N');
		}while(!playGame);
		System.out.println("Thanks for playing!");
	}
}
