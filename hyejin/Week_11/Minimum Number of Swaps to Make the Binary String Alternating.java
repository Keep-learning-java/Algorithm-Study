class Solution {
    public int minSwaps(String s) {
        // 1과 0의 갯수 세서 저장해두기
        int[] count = new int[2];

        for(int i=0; i<s.length(); i++) {
            count[Character.getNumericValue(s.charAt(i))]++;
        }

        // alternating 하게 만들 수 없는 경우라면
        // 0과 1의 개수가 2개이상 차이난다
        if(Math.abs(count[0]-count[1])  >= 2) {
            return -1;
        }

        // alternating 하게 만들 수 있는 경우
        // swap 개수 구하는 방법
        // alternating한 문자열과 다른 글자의 수 / 2

        String alternating = "";

        // 더 갯수가 많은 숫자로 시작할 수 밖에 없다
        if(count[0] > count[1]) {
            alternating = "01";
            alternating = generateAlternating(alternating, s);
        }
        else if(count[0] < count[1]) {
            alternating = "10";
            alternating = generateAlternating(alternating, s);
        }
        // 같으면 어떡하지?
        else if(count[0] == count[1]) {
            alternating = "";
            String alt0 = generateAlternating("01", s);
            String alt1 = generateAlternating("10", s);

            int result0 = countDifference(s, alt0);
            int result1 = countDifference(s, alt1);

            return Math.min(result0, result1);
        }

        int result = countDifference(s, alternating);
        return result;
    }

    public String generateAlternating(String alternating, String s) {
        while(alternating.length() < s.length()) {
            if(alternating.charAt(alternating.length()-1) == '1') {
                alternating += "01";
            }
            if(alternating.charAt(alternating.length()-1) == '0') {
                alternating += "10";
            }
        }

        if(alternating.length() > s.length()) {
            alternating = alternating.substring(0, alternating.length()-1);
        }

        return alternating;
    }

    public int countDifference(String s, String alternating) {
        int result = 0;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) != alternating.charAt(i)) {
                result++;
            }
        }

        return result/2;
    }

}