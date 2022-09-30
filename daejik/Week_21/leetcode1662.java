class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        String word1Sum = "";
        String word2Sum = "";
        
        for(int i=0; i<word1.length; i++){
            word1Sum = word1Sum + word1[i];
        }
        for(int i=0; i<word2.length; i++){
            word2Sum = word2Sum + word2[i];
        }
        return word1Sum.equals(word2Sum);
    }
}