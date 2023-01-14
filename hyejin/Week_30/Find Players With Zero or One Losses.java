class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        List<List<Integer>> result = new ArrayList<>();
		result.add(new ArrayList<>());
		result.add(new ArrayList<>());
		
		HashSet<Integer> persons = new HashSet<>();
		HashMap<Integer, Integer> loserMap = new HashMap<>();
		
		for (int[] match : matches) {
			int winner = match[0];
			int loser = match[1];
			
			persons.add(winner);
			persons.add(loser);
			
			int loseCount = loserMap.getOrDefault(loser, 0);
			loserMap.put(loser, ++loseCount);
		}
		
		for (int person : persons) {
			// 진 적 없음
			if (!loserMap.containsKey(person)) {
				result.get(0).add(person);
			}
			// 1번만 짐
			else if (loserMap.get(person) == 1) {
				result.get(1).add(person);
			}
		}
        
        Collections.sort(result.get(0));
		Collections.sort(result.get(1));
		return result;
    }
}