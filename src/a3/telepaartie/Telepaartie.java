package a3.telepaartie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Telepaartie {

	public static void main(String[] args) {
		File sourceFile = new File("src/a3/telepaartie/verteilungen");

		try {
			Scanner lineSc = new Scanner(sourceFile);
			lineSc.useDelimiter("\n");
			lineSc.next();

			Scanner sc = new Scanner(lineSc.next());
			sc.useDelimiter(",");
			int[] pot = new int[3];
			for (int i = 0; i < 3; i++) {
				pot[i] = sc.nextInt();
			}
			System.out.println(Arrays.toString(pot));

			int lll = 0;
			while (!finished(pot) && lll < 150) {
				if (equalsExist(pot)) {
					lll++;
					break;
				} else {
				int smallInd = indexSmallest(pot);
				int bigInd = indexBiggest(pot);
				pot[bigInd] -= pot[smallInd];
				pot[smallInd] += pot[smallInd];

				lll++;
				}
			}
			System.out.println(lll);

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

	private static boolean equalsExist(int[] pot) {
		if (pot[0] == pot[1] || pot[0] == pot[2] || pot[1] == pot[2])
			return true;
		else
			return false;
	}

	public static boolean finished(int[] pot) {
		if (pot[0] != 0 && pot[1] != 0 && pot[2] != 0)
			return false;
		else
			return true;
	}

	public static int indexSmallest(int[] pot) {
		int out = 0;
		if (pot[1] < pot[out])
			out = 1;
		if (pot[2] < pot[out])
			out = 2;
		return out;
	}

	public static int indexBiggest(int[] pot) {
		int out = 0;
		if (pot[1] > pot[out])
			out = 1;
		if (pot[2] > pot[out])
			out = 2;
		return out;
	}

}
