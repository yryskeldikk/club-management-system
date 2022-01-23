import java.util.*;
import java.io.*;
public class Main {
	public static void main(String [] args) throws FileNotFoundException {	
		
		Scanner in = new Scanner(System.in);
		
		System.out.print("Please input the file pathname: ");
		String filepathname = in.nextLine();
	
		Scanner inFile = new Scanner(new File(filepathname));
		String cmdLine1 = inFile.nextLine();
		String[] cmdLine1Parts = cmdLine1.split(" ");
		System.out.println("\n> "+cmdLine1);
		SystemDate.createTheInstance(cmdLine1Parts[1]);

		while (inFile.hasNext()) {
			try{ 
			String cmdLine = inFile.nextLine().trim();
			
			if (cmdLine.equals("")) continue;  

			System.out.println("\n> "+cmdLine);
			
			String[] cmdParts = cmdLine.split(" "); 
			
			if (cmdParts[0].equals("register"))
				(new CmdRegister()).execute(cmdParts);
			else if (cmdParts[0].equals("listMembers"))
				(new CmdListMembers()).execute(cmdParts);
			else if (cmdParts[0].equals("startNewDay"))
				(new CmdStartNewDay()).execute(cmdParts);
			else if (cmdParts[0].equals("undo"))
				RecordedCommand.undoOneCommand();
			else if (cmdParts[0].equals("redo"))
				RecordedCommand.redoOneCommand();
			else if (cmdParts[0].equals("arrive"))
				(new CmdArrive()).execute(cmdParts);
			else if (cmdParts[0].equals("listItems"))
				(new CmdListItems()).execute(cmdParts);
			else if (cmdParts[0].equals("checkout"))
				(new CmdCheckout()).execute(cmdParts);
			else if (cmdParts[0].equals("checkin"))
				(new CmdCheckin()).execute(cmdParts); 
			else if (cmdParts[0].equals("request"))
				(new CmdRequest()).execute(cmdParts); 
			else if (cmdParts[0].equals("cancelRequest"))
				(new CmdCancelRequest()).execute(cmdParts); 
			else
				throw new ExUnknownCommand();
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
        inFile.close();
		in.close();	
	}
}