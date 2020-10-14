import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Driver {



	public static void main(String[] args) {
		SinglyLinkedList partAList = new SinglyLinkedList(); 
		SinglyLinkedList partBList = new SinglyLinkedList(); 
		System.out.println("PART A ----------------------------------------- ");
		partA(partAList); //Part A
		System.out.println("PART B ----------------------------------------- ");
		partB(partBList); // Part B
	}
	

	
	public static void partB(SinglyLinkedList Processes) {
		for(int i = 0; i < 20; i++) {
			addProcess(Processes);
		}
		int cycles = 1;
		int counter = 1;
		ArrayList<String> ar = new ArrayList();
		boolean contains = false;
		while((counter * 100) <= 1000) {
			if(cycles % 100 == 0) {
				System.out.println("Cycle " + (counter * 100) + " : " + Processes.size());
				counter++;
				
			}
			if (cycles == 1) {
				List<String> tempResources = (List<String>) Processes.first();
				for (int i = 0; i < tempResources.size(); i++) {
					ar.add(tempResources.get(i));
				}
				Processes.removeFirst();
				cycles++;
				addProcess(Processes);
				addProcess(Processes);
			}else {
				List<String> tempResources = (List<String>) Processes.first();
				
				for (int i = 0; i < tempResources.size(); i++) {
					if(ar.contains(tempResources.get(i))) {
						contains = true;
					}else {
						contains = false;
					}
				}
				if(contains == true ) {
					ar.clear();
					for (int i = 0; i < tempResources.size(); i++) {
						ar.add(tempResources.get(i));
					}
					cycles++;
;
					addProcess(Processes);
					addProcess(Processes);
					
					Processes.removeFirst();
				}else {
					for (int i = 0; i < tempResources.size(); i++) {
						ar.add(tempResources.get(i));
					}
					Processes.removeFirst();
				}
				
			}
			
		}
		
		
	}
	
	public static void addProcess(SinglyLinkedList p) {
		 Random rand = new Random();
		 int length = (rand.nextInt(3) + 1);
		 SinglyLinkedList Resources = new SinglyLinkedList();
		 Resources.addLast("A");
		 Resources.addLast("B");
		 Resources.addLast("C");
		 
		 ArrayList<String> tempList = new ArrayList();
		 
		 for(int i = 0; i < length; i++) {
			 tempList.add((String)Resources.first());
			 Resources.addLast(Resources.first());
			 Resources.removeFirst();
		 }
		 p.addLast(tempList);
		
	}
	
	public static void partA(SinglyLinkedList Processes) {
		
		File inFile = new File("TC_PP1.txt");
		try {
			Scanner fileInput = new Scanner(inFile);
			 
			String line = fileInput.nextLine();
			
			int processes = 0;
			for(int i = 0; i < line.length(); i++) {
			    if(line.charAt(i) == ';') processes++;
			}
			
			
			for(int i = 1; i < (processes * 2) + 2; i+=2) {
				String s = new String(line);
				String temp = s.split("[\\(\\)]")[i];
				List<String> tempList = Arrays.asList(temp.split(","));
				Processes.addLast(tempList);

			}
			System.out.println("Number of Cycles: " + cycleProcess(Processes));
			
				
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open file");
		}
		
	}
	
	public static int cycleProcess(SinglyLinkedList p) {
		int cycles = 1;
		ArrayList<String> ar = new ArrayList();
		boolean contains = false;
		while(p.first() != null) {
			if (cycles == 1) {
				List<String> tempResources = (List<String>) p.first();
				for (int i = 0; i < tempResources.size(); i++) {
					ar.add(tempResources.get(i));
				}
				p.removeFirst();
				cycles++;
			}else {
				List<String> tempResources = (List<String>) p.first();
				
				for (int i = 0; i < tempResources.size(); i++) {
					if(ar.contains(tempResources.get(i))) {
						contains = true;
					}else {
						contains = false;
					}
				}
				if(contains == true ) {
					ar.clear();
					for (int i = 0; i < tempResources.size(); i++) {
						ar.add(tempResources.get(i));
					}
					cycles++;
					p.removeFirst();
				}else {
					for (int i = 0; i < tempResources.size(); i++) {
						ar.add(tempResources.get(i));
					}
					p.removeFirst();
				}
				
			}
			
		}

		
		
		
		return cycles;
	}

	

}
