package array;
import java.util.Scanner;
//-------------------------------------------------
//Tyler Sinclair
//100924537
//------------------------------------------------
public class Program {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		
		ArrayRefSortedStringList theList;
		Scanner in = new Scanner(System.in);
		String newSize;
		int newSizePass = 0;
		String choice ="";
		String removal = "";
		theList = new ArrayRefSortedStringList(5);	
		do{
			System.out.println("Please enter the desired list capacity: ");
			newSize = in.next();
			try{
				newSizePass = Integer.parseInt(newSize);
				if(newSizePass < 1)
					throw new IllegalArgumentException();
			}catch (Exception e){
				System.out.println("Please choose only numbers greater than 0!");
				newSizePass = 0;
			}
		}while (newSizePass == 0);
		
		theList = new ArrayRefSortedStringList(newSizePass);
		
		do{
		System.out.println("Please choose one of the following options:");
		System.out.println("(A)dd, (Remove), (D)isplay, (E)xit");
		choice = in.next();
		switch(choice.toLowerCase().trim()){
		case "a":
			if(!theList.isFull()){
			System.out.println("Please enter the student name:");
			in.nextLine();
			String x = in.nextLine();
			theList.add(x);
			}else
				System.out.println("The capacity for the list has been reached!!");
			break;
		case "r":
			System.out.println("Please enter the item you would like to find for removal");
			removal = in.next().toLowerCase().trim();
			if(theList.contains(removal)){
				System.out.println("Success! The item was found are you sure you want to remove it? Y / any "
						+ "other key to abort");
				choice = in.next().toLowerCase().trim();
				if(choice.equals("y"))
					theList.remove(removal);
				else
					System.out.println("Aborted!");
			}else
				System.out.println("Sorry, that item could not be found");
			break;
		case "d":
			System.out.println(theList.toString());
			break;
		case "e":
			System.out.println("The application will now exit");
			System.exit(0);
		default:
			System.out.println("Invalid choice");
		}
		}while (1 < 2); //FOREVER!!!!!!!!!!!!
	}

}
