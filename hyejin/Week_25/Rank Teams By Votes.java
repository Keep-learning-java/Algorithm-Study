class Solution {
    public String rankTeams(String[] votes) {
		int[][] voteCount = new int[26][26]; // [알파벳][등수]

		for (String vote : votes) {
			for (int i = 0; i < vote.length(); i++) {
				char ch = vote.charAt(i);
				voteCount[ch - 'A'][i]++;
			}
		}

		char[] result = votes[0].toCharArray();
		Character[] resultTemp = new Character[votes[0].length()];
		
		// result - resultTemp 복사
		for(int i=0; i<result.length; i++) {
			resultTemp[i] = result[i];
		}
		
		// 정렬
		Arrays.sort(resultTemp, (c1, c2) -> {
			for (int i = 0; i < 26; i++) {
				// 경합하지 않으면 내림차순
				if (voteCount[c1 - 'A'][i] != voteCount[c2 - 'A'][i]) {
					return voteCount[c2 -'A'][i] - voteCount[c1-'A'][i];
				}
			}
			// 경합하면 알파벳순서
			return c1 - c2;
		});
		
		// resultTemp - result 복사
		for(int i=0; i<result.length; i++) {
			result[i] = resultTemp[i];
		}
		
		return new String(result);
    }
}