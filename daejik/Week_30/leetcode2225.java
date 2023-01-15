class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> zeroLossSet = new HashSet<>();
        Set<Integer> oneLossSet = new HashSet<>();
        Set<Integer> theOthersSet = new HashSet<>();

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // (1) 승리 처리
            if (!oneLossSet.contains(winner) && !theOthersSet.contains(winner)) {
                zeroLossSet.add(winner);
            }

            // (2) Loser 처리
            if (zeroLossSet.contains(loser)) {
                // 한번도 진적 없음 -> 1번 져버림
                zeroLossSet.remove(loser);
                oneLossSet.add(loser);
            } else if (oneLossSet.contains(loser)) {
                // 한번 진적 있음 -> 2번 이상 져버림
                oneLossSet.remove(loser);
                theOthersSet.add(loser);
            } else if (theOthersSet.contains(loser)) {
                // 2번 이상 이미 졌음 -> 아무 처리 필요 없음
                continue;
            } else {
                // 어디에도 속해 있지 않음 -> 한번 져버림
                oneLossSet.add(loser);
            }
        }

    // 정답 생성
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        answer.get(0).addAll(zeroLossSet);
        answer.get(1).addAll(oneLossSet);
        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));
        
        return answer;
    }
}