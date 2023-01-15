class Solution {
    public List<List<Integer>> findWinners(int[][] matches) {
        Set<Integer> zeroLossSet = new HashSet<>();
        Set<Integer> oneLossSet = new HashSet<>();
        Set<Integer> theOthersSet = new HashSet<>();

        for (int[] match : matches) {
            int winner = match[0];
            int loser = match[1];

            // (1) �¸� ó��
            if (!oneLossSet.contains(winner) && !theOthersSet.contains(winner)) {
                zeroLossSet.add(winner);
            }

            // (2) Loser ó��
            if (zeroLossSet.contains(loser)) {
                // �ѹ��� ���� ���� -> 1�� ������
                zeroLossSet.remove(loser);
                oneLossSet.add(loser);
            } else if (oneLossSet.contains(loser)) {
                // �ѹ� ���� ���� -> 2�� �̻� ������
                oneLossSet.remove(loser);
                theOthersSet.add(loser);
            } else if (theOthersSet.contains(loser)) {
                // 2�� �̻� �̹� ���� -> �ƹ� ó�� �ʿ� ����
                continue;
            } else {
                // ��𿡵� ���� ���� ���� -> �ѹ� ������
                oneLossSet.add(loser);
            }
        }

    // ���� ����
        List<List<Integer>> answer = Arrays.asList(new ArrayList<>(), new ArrayList<>());
        answer.get(0).addAll(zeroLossSet);
        answer.get(1).addAll(oneLossSet);
        Collections.sort(answer.get(0));
        Collections.sort(answer.get(1));
        
        return answer;
    }
}