package custom.pi;

import java.lang.Thread;
import java.math.BigDecimal;

@SuppressWarnings("divide")
public class PiThread extends Thread{
	private int threadCount;
	private int threadRemainder;
	private long numberOfIteration;
	private int postDecimal;
	private BigDecimal sum;
	
	private void copyElements(int threadCount, int threadRemainder, long numberOfIteration, 
			int postDecimal) {
		this.threadCount = threadCount;
		this.threadRemainder = threadRemainder;
		this.numberOfIteration = numberOfIteration;
		this.postDecimal = postDecimal;
		this.sum = new BigDecimal(0);
	}
	
	PiThread() {
		this.copyElements(0, 0, 0, 0);
	}
	
	PiThread(int threadCount, int threadRemainder, long numberOfIteration, int postDecimal) {
		this.copyElements(threadCount, threadRemainder, numberOfIteration, postDecimal);
	}
	
	@Override
	public void run() { //sum += Math.pow(-1, i) / (2 * i + 1);
		for (int iteration = 0; iteration < this.numberOfIteration; ++iteration) {
			if ((iteration % this.threadCount) == this.threadRemainder) {
				this.sum = this.sum.add(new BigDecimal(Math.pow(-1, iteration))
						.divide(new BigDecimal(2 * iteration + 1), this.postDecimal, BigDecimal.ROUND_HALF_UP));
			}
		}
	}
	
	public BigDecimal getSum() {
		return this.sum;
	}
	
}
