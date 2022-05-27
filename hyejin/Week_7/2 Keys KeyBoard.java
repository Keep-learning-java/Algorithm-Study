class Solution {

    boolean[] prime = new boolean[1001]; // true: 소수, false: 합성수
    int[] cache = new int[1001];


    public int minSteps(int n) {
        Arrays.fill(prime, true);
        prime[1] = false;
        setPrime();

        return copyAndPaste(n);
    }

    public int copyAndPaste(int n) {
        if(n == 1)
            return 0;

        // 소수인 경우
        if(prime[n]) {
            // 1번 복사, n-1번 붙여넣기
            // (1+n-1)
            return n;
        }
        // 짝수인 경우
        else if(n%2 == 0) {
            // n/2를 복사 후 붙여넣기
            if(cache[n/2] != 0) {
                return cache[n/2] + 2;
            }
            return copyAndPaste(n/2) + 2;
        }
        // 소수의 배수인 홀수
        else {
            // 이 수의 가장 큰 약수를 찾아야 함
            int factor = getMaxFactor(n);

            // 복사 -> 몫-1만큼 붙여넣기
            if(cache[factor] != 0)
                return cache[factor] + (n/factor-1) + 1;


            cache[factor] = copyAndPaste(factor) + (n/factor-1) + 1;
            return cache[factor];
        }

    }
    public int getMaxFactor(int number) {
        for(int i=number-1; i>2 ;i--) {
            if(number%i == 0) {
                return i;
            }
        }
        return number;
    }

    public void setPrime() {
        for(int i=2; i<prime.length; i++) {
            // 소수이면 배수 걸러줌
            if(prime[i]) {
                for(int j=i*2; j<prime.length; j+=i) {
                    prime[j] = false;
                }
            }
        }
    }
}