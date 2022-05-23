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
	static long ans; // 계산과정은 int범위 벗어날 수도 있음.

	
	public static void main(String[] args) throws IOException {
		int nowIdx = 0, finalIdx = 0;
		initMapSet();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		input = br.readLine();		
		finalIdx = input.length();
		
		while(nowIdx < finalIdx) {
			// 1. i ~ j까지 문자열을 숫자로 변환
			//    0~9에 해당하는 영단어가 아닐때까지 반복 => 숫자 list에 저장
			//    이외에 무언가 나오면 Madness!
			int numEndIdx = findNumEndIdx(input, nowIdx, finalIdx);
			int opIdx = numEndIdx + 1;
			
			// 연산자를 못찾았거나, 인덱스범위가 벗어났거나, 영단어 사이에 연산자가 끼어 있는 경우
			if(numEndIdx == -1 || !(opIdx < finalIdx || nowIdx <= numEndIdx) || (opIdx < nowIdx + 3)) {
				System.out.println(err);
				return;
			}
			
			int num = convToNum(input, nowIdx, numEndIdx + 1);
			Character op = input.charAt(opIdx);
			
			// 변환할 숫자 문자열이 이상하거나 연산자가 이상한 경우
			if(num == -1 || !opSet.contains(op)) {
				System.out.println(err);
				return;				
			}
			numberList.add(num);
			opList.add(op);
			
			nowIdx = opIdx + 1;
		}
		// 2. 숫자 -> 연산자 반복 계산하면서 print()
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
		// 3. 결과 값 다시 영어로 변환
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
		// 연산자가 등장하는 idx 찾기
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
		// strToNumMap초기화
		strToNumMap.put("ZERO", 0); strToNumMap.put("ONE", 1); strToNumMap.put("TWO", 2);
		strToNumMap.put("THREE", 3); strToNumMap.put("FOUR", 4); strToNumMap.put("FIVE", 5);
		strToNumMap.put("SIX", 6); strToNumMap.put("SEVEN", 7); strToNumMap.put("EIGHT", 8);
		strToNumMap.put("NINE", 9);
		
		// numToStrMap 초기화
		numToStrMap.put(0, "ZERO"); numToStrMap.put(1, "ONE"); numToStrMap.put(2, "TWO");
		numToStrMap.put(3, "THREE"); numToStrMap.put(4, "FOUR"); numToStrMap.put(5, "FIVE");
		numToStrMap.put(6, "SIX"); numToStrMap.put(7, "SEVEN"); numToStrMap.put(8, "EIGHT");
		numToStrMap.put(9, "NINE");
		
		// opSet 초기화
		opSet.add('+'); opSet.add('-'); opSet.add('x'); opSet.add('/'); opSet.add('=');
	}
}
