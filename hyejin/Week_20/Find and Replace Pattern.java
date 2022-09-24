class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
		List<String> result = new ArrayList<>();

		for (int i = 0; i < words.length; i++) {
			String word = words[i];
			
			// 길이 다르면 탈락
			if (word.length() != pattern.length())
				continue;
			
			// 한글자씩 대응시킴
			HashMap<Character, Character> map = new HashMap<>(); // <wordLetter, patternLetter>
			boolean[] isPatternOccupied = new boolean[26];
			boolean flag = true;
			
			for (int j = 0; j < word.length(); j++) {
				char wordLetter = word.charAt(j);
				char patternLetter = pattern.charAt(j);
				
				// wordLetter와 매핑된 글자 존재
				if (map.containsKey(wordLetter)) {
					// 매핑된 글자와 패턴이 불일치
					if (map.get(wordLetter) != patternLetter) {
						flag = false;
						break;
					}
				}
				// 매핑된 글자 없는 경우, 해당 패턴도 사용되지 않음 -> 할당
				else if (!isPatternOccupied[patternLetter - 'a']) {
					map.put(wordLetter, patternLetter);
					isPatternOccupied[patternLetter - 'a'] = true;
				}
				// 매핑된 글자 없는데, 패턴은 이미 사용되어서 매핑 불가 -> 불가능
				else {
					flag = false;
				}
				
			}
			
			if (flag) {
				result.add(word);
			}
		}

		return result;        
    }
}