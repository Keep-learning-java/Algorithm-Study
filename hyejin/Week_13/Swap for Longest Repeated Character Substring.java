class Solution {
    public int maxRepOpt1(String text) {
		char[] textArr = text.toCharArray();
		
		// char grouping
		ArrayList<CharGroup> groups = new ArrayList<>();
		char cur;
		char prev = textArr[0];
		int startPosition = 0;
		int endPosition = 0;
		
		for(int i=1; i<textArr.length; i++) {
			cur = textArr[i];
			
			if(cur == prev) {
				endPosition = i;
			}
			else {
				groups.add(new CharGroup(prev, startPosition, endPosition));
				
				// init variables
				startPosition = i;
				endPosition = i;
			}
			prev = cur;
		}
		// 마지막 그룹 따로 넣어주기
		groups.add(new CharGroup(prev, startPosition, endPosition));
		
		int max = 0;
		for(CharGroup group : groups) {
			max = Math.max(group.endPosition - group.startPosition + 1, max);
		}
		
		// Case 1
		// aa bc a 인 경우
		for(int i=0; i<groups.size(); i++) {
			CharGroup group = groups.get(i);
			int lengthOfCurGroup = group.endPosition - group.startPosition + 1;
			
			if(isSwapable(textArr, group.startPosition-1, group.endPosition+1, group.letter)) {
				max = Math.max(lengthOfCurGroup+1, max);
			}
		}
		
		// Case 2
		// 그룹이 3개 이상일 때 aa b aa 이고, swap이 가능한 경우
		for(int i=1; i<groups.size()-1; i++) {
			CharGroup left = groups.get(i-1);
			CharGroup mid = groups.get(i);
			CharGroup right = groups.get(i+1);
			
			if(left.letter != right.letter) {
				continue;
			}
			
			int leftLength = left.endPosition - left.startPosition + 1;
			int midLength = mid.endPosition - mid.startPosition + 1;
			int rightLength = right.endPosition - right.startPosition + 1;
			
			// 가운데 글자 그룹의 길이가 1인 경우
			if(midLength == 1) {
				max = Math.max(leftLength + rightLength, max);
			}
			// 가운데 글자 그룹 길이가 1이고, 그 이후에 swap 가능한 경우
			if(midLength == 1 && isSwapable(textArr, left.startPosition-1, right.endPosition+1, left.letter)) {
				max = Math.max(leftLength + rightLength + 1, max);
			}
		}
		
		return max;
    }
    	
	public boolean isSwapable(char[] textArr, int leftIndex, int rightIndex, char target) {
		// left range check
		if(leftIndex >= 0) {
			for(int i=0; i<=leftIndex; i++) {
				if(textArr[i] == target)
					return true;
			}	
		}
		
		// right range check		
		if(rightIndex < textArr.length) {
			for(int i=rightIndex; i<textArr.length; i++) {
				if(textArr[i] == target)
					return true;
			}
		}
		return false;
	}
}

class CharGroup {
	char letter;
	int startPosition;
	int endPosition;
	
	public CharGroup(char letter, int startPosition, int endPosition) {
		super();
		this.letter = letter;
		this.startPosition = startPosition;
		this.endPosition = endPosition;
	}
}