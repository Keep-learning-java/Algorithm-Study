import java.math.BigInteger;
class Solution {
    public int numSteps(String s) {
		int count = 0;
		BigInteger number = new BigInteger(s, 2);
		
		while (!number.equals(BigInteger.ONE)) {
			count++;

			// 짝수: 나누기 2
			if (number.and(BigInteger.ONE).equals(BigInteger.ZERO)) {
				number = number.shiftRight(1);
			}
			// 홀수: 더하기 1
			else {
				number = number.add(BigInteger.ONE);
			}
			
		}
		return count;      
    }
}