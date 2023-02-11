class Solution {
    public int countTime(String time) {
 		int answer = 1;

		if (time.charAt(4) == '?') // 0~9
			answer *= 10;

		if (time.charAt(3) == '?') // 0~5
			answer *= 6;

		if (time.charAt(0) == '?' && time.charAt(1) == '?') { // 0~23
			answer *= 24;
		}
		// 시간 맨 앞자리에 따라 달라짐
		else if (time.charAt(1) == '?') {
			switch (time.charAt(0)) {
			case '0': // 0?
			case '1': // 1?
				answer *= 10;
				break;

			case '2': // 2?
				answer *= 4;
				break;
			}
		}
		
		else if (time.charAt(0) == '?') {
			// 시간 뒷자리가 4이상이면 앞에 0, 1
			if (time.charAt(1) - '0' >= 4) {
				answer *= 2;
			}
			// 시간 뒷자리가 0, 1, 2, 3이면 상관없음 0~
			else
				answer *= 3;
		}

		return answer;       
    }
}