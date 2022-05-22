import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Boj23629 {
	static String input, ansStr;
	static ArrayList<Integer> numberList = new ArrayList<Integer>();
	static ArrayList<Character> opList = new ArrayList<Character>();
	static HashMap<String, Integer> strToNumMap = new HashMap<>();
	static HashMap<Integer, String> numToStrMap = new HashMap<>();
	static HashSet<Character> opSet = new HashSet<>();
	static final String err = "Madness!";
	static long ans; // �������� int���� ��� ���� ����.

	
	public static void main(String[] args) throws IOException {
		int nowIdx = 0, finalIdx = 0;
		initMapSet();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();		
		finalIdx = input.length();
		
		while(nowIdx < finalIdx) {
			// 1. i ~ j���� ���ڿ��� ���ڷ� ��ȯ
			//    0~9�� �ش��ϴ� ���ܾ �ƴҶ����� �ݺ� => ���� list�� ����
			//    �̿ܿ� ���� ������ Madness!
			int numEndIdx = findNumEndIdx(input, nowIdx, finalIdx);
			int opIdx = numEndIdx + 1;
			
			// �����ڸ� ��ã�Ұų�, �ε��������� ����ų�, ���ܾ� ���̿� �����ڰ� ���� �ִ� ���
			if(numEndIdx == -1 || !(opIdx < finalIdx || nowIdx <= numEndIdx) || (opIdx < nowIdx + 3)) {
				System.out.println(err);
				return;
			}
			
			int num = convToNum(input, nowIdx, numEndIdx + 1);
			Character op = input.charAt(opIdx);
			
			// ��ȯ�� ���� ���ڿ��� �̻��ϰų� �����ڰ� �̻��� ���
			if(num == -1 || !opSet.contains(op)) {
				System.out.println(err);
				return;				
			}
			numberList.add(num);
			opList.add(op);
			
			nowIdx = opIdx + 1;
		}
		// 2. ���� -> ������ �ݺ� ����ϸ鼭 print()
		ans = numberList.get(0);
		
		for(int i=0; i<numberList.size(); i++) {
			System.out.print(numberList.get(i));
			System.out.print(opList.get(i));
			
			if(i > 0) {
				if(opList.get(i - 1) == '+') ans += (long) numberList.get(i);
				else if(opList.get(i - 1) == '-') ans -= (long)numberList.get(i);
				else if(opList.get(i - 1) == 'x') ans *= (long)numberList.get(i);
				else if(opList.get(i - 1) == '/') ans /= (long)numberList.get(i);
			}
		}
		System.out.println();
		// 3. ��� �� �ٽ� ����� ��ȯ
		if(ans < 0) {
			System.out.print('-');
			ans *= -1;
		}
		while(ans > 0) {
			int tmp = (int) (ans % 10);
			if(ansStr == null) ansStr = numToStrMap.get(tmp);
			else ansStr = numToStrMap.get(tmp) + ansStr;
			
			ans /= 10;
		}
		
		if(ansStr == null) ansStr = numToStrMap.get(0);
		System.out.println(ansStr);
	}
	public static int findNumEndIdx(String str, int startIdx, int strLength) {
		// �����ڰ� �����ϴ� idx ã��
		int idx = strLength;
		
		int tmpIdx = str.indexOf("+", startIdx);
		if(tmpIdx > -1 && tmpIdx < idx)
			idx = tmpIdx;
		tmpIdx = str.indexOf("-", startIdx);
		if(tmpIdx > -1 && tmpIdx < idx)
			idx = tmpIdx;
		tmpIdx = str.indexOf("x", startIdx);
		if(tmpIdx > -1 && tmpIdx < idx)
			idx = tmpIdx;
		tmpIdx = str.indexOf("/", startIdx);
		if(tmpIdx > -1 && tmpIdx < idx)
			idx = tmpIdx;
		tmpIdx = str.indexOf("=", startIdx);
		if(tmpIdx > -1 && tmpIdx < idx)
			idx = tmpIdx;
		
		if(idx >= strLength) return -1;
		return idx - 1;
	}
	public static int convToNum(String str, int startIdx, int endIdx) {
		int ret = 0;
		
		while(startIdx < endIdx) {
			String strLen3 = null;
			String strLen4 = null;
			String strLen5 = null;
			int num3 = 0, num4 = 0, num5 = 0;
			int len = 0, num = 0;
//ONETWOTHREE
//SEVENTWO			
			if(startIdx + 3 <= endIdx) {
				strLen3 = str.substring(startIdx, startIdx + 3);
				//SEV
				num3 = strToNumMap.getOrDefault(strLen3, -1);
			}
			if(startIdx + 4 <= endIdx) {
				strLen4 = str.substring(startIdx, startIdx + 4);
				num4 = strToNumMap.getOrDefault(strLen4, -1);
			}
			if(startIdx + 5 <= endIdx) {
				strLen5 = str.substring(startIdx, startIdx + 5);
				num5 = strToNumMap.getOrDefault(strLen5, -1);
			}
			
			if(strLen3 != null && num3 != -1) {
				// ONE, TWO, SIX
				len = 3;
				num = num3;
			} else if(strLen4 != null && num4 != -1) {
				// ZERO, FOUR, FIVE, NINE
				len = 4;
				num = num4;
			} else if(strLen5 != null && num5 != -1) {
				// THREE, SEVEN, EIGHT
				len = 5;
				num = num5;
			}
			
			if(len == 0) {
				return -1;
			}
			startIdx += len;
			ret += num;
			
			if(startIdx < endIdx) ret *= 10;
		}
		
		return ret;
	}
	public static void initMapSet() {
		// strToNumMap�ʱ�ȭ
		strToNumMap.put("ZERO", 0); strToNumMap.put("ONE", 1); strToNumMap.put("TWO", 2);
		strToNumMap.put("THREE", 3); strToNumMap.put("FOUR", 4); strToNumMap.put("FIVE", 5);
		strToNumMap.put("SIX", 6); strToNumMap.put("SEVEN", 7); strToNumMap.put("EIGHT", 8);
		strToNumMap.put("NINE", 9);
		
		// numToStrMap �ʱ�ȭ
		numToStrMap.put(0, "ZERO"); numToStrMap.put(1, "ONE"); numToStrMap.put(2, "TWO");
		numToStrMap.put(3, "THREE"); numToStrMap.put(4, "FOUR"); numToStrMap.put(5, "FIVE");
		numToStrMap.put(6, "SIX"); numToStrMap.put(7, "SEVEN"); numToStrMap.put(8, "EIGHT");
		numToStrMap.put(9, "NINE");
		
		// opSet �ʱ�ȭ
		opSet.add('+'); opSet.add('-'); opSet.add('x'); opSet.add('/'); opSet.add('=');
	}
}
