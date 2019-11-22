package a2.nummernmerker;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Merker {

	public static void main(String[] args) {
		// use first parameter as argument
		File numFile = new File(args[0]);
		// show whether file exists or not
		System.out.println("File " + numFile.getPath() + " exists: " + numFile.exists());

		try {
			// create a scanner on the file to read in the numbers
			Scanner lineSc = new Scanner(numFile);
			lineSc.useDelimiter("\n");

			// run through all numbers in file
			while (lineSc.hasNext()) {
				// save current number as String
				String rawNum = lineSc.next();
				System.out.println(rawNum);

				// ArrayList for counting zeros (just for visualization)
				ArrayList<Integer> zeros = new ArrayList<Integer>();
				// Array for dividing the output into multiple blocks
				ArrayList<String> output = new ArrayList<String>();

				// rush through number and mark the positions with zeros
				for (int i = 0; i < rawNum.length(); i++) {
					if (rawNum.substring(i, i + 1).equals("0"))
						zeros.add(i);
				}

				// prepare the visualization of zeros
				String outStr = "";
				for (int i = 0; i < rawNum.length(); i++) {
					if (zeros.contains(i))
						outStr += "0";
					else
						outStr += "-";
				}
				// print out verbose stuff
				System.out.println(outStr);
				System.out.println("number has " + rawNum.length() + " digits");
				System.out.println(zeros.size() + " zeros in number");

				// start of actual algorithm
				// marks the current block of the output
				int curBlock = 0;
				// index must exist to add something to it
				output.add("");

				// go through the whole number and divide it into blocks
				for (int i = 0; i < rawNum.length(); i++) {
					// if the current block is shorter than 2, there is no choice in adding the next
					// number to it
					if (output.get(curBlock).length() < 2) {
						output.set(curBlock, output.get(curBlock) + rawNum.substring(i, i + 1));
					}
					// if the next number is a zero and can be added to the current block, do it so
					// it isn't in front of the next block
					else if (rawNum.substring(i, i + 1).equals("0") && output.get(curBlock).length() < 4) {
						output.set(curBlock, output.get(curBlock) + rawNum.substring(i, i + 1));
					}
					// if these cases aren't fulfilled, go ahead and start next block
					else {
						// same as above
						curBlock++;
						output.add("");
						// no digit is appended in this run, so the counter has to be decreased
						i--;
					}
				}

				// show us the generated blocks in a nice and preformated way
				System.out.println(output);

				// logical division to next number in file
				if (lineSc.hasNext())
					System.out.printf("\n\n\n");
			}
		}
		// needed for scanner and file processing
		catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

}
