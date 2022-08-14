class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        
        int[] result = new int[temperatures.length];
        
        for(int i=0; i<temperatures.length-1; i++) {
            int count = 1;
            for(int j=i+1; j<temperatures.length; j++) {
                if(temperatures[j] > temperatures[i]) {
                    result[i] = count;
                    count = 0;
                    break;
                }    
                else
                    count++;
            
            }
        }
        return result;
    }
}