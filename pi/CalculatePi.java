package custom.pi;

import java.math.BigDecimal;

public class CalculatePi {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		int postDecimal = 100;
		int numberOfThreads = 16;
		long numberOfIteration = 1_000_000_000;
		BigDecimal result = new BigDecimal(0);
		
		PiThread[] threads = new PiThread[numberOfThreads];
		
		try {
			for (int index = 0; index < numberOfThreads; ++index) {
				threads[index] = new PiThread(numberOfThreads, index, numberOfIteration, postDecimal);
				threads[index].start();
			}
		
			for (int index = 0; index < numberOfThreads; ++index) {
				threads[index].join();
			}
			
			for (int index = 0; index < numberOfThreads; ++index) {
				result = result.add(threads[index].getSum());
			}
		} catch (Exception ie) {
			System.out.print(ie);
		}
		
		
		System.out.print("PI -> " + result.multiply(BigDecimal.valueOf(4)).toString().equals("3.14159265358979323846264338327950288419716939937510" + 
				"58209749445923078164062862089986280348253421170679"));
		
		System.out.print("PI from site -> " + ("3.14159265358979323846264338327950288419716939937510" + 
				"58209749445923078164062862089986280348253421170679"));
		System.out.print("PI from threads -> " + result.multiply(BigDecimal.valueOf(4)).toString());
		
	}

}
