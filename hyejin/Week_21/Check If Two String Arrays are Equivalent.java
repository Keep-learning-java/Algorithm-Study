class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder concat1 = new StringBuilder();
		StringBuilder concat2 = new StringBuilder();
		
		for(int i=0; i<word1.length; i++) {
			concat1.append(word1[i]);
		}
		for(int i=0; i<word2.length; i++) {
			concat2.append(word2[i]);
		}
		
		return concat1.toString().equals(concat2.toString());   
    }
}