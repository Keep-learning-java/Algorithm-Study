class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> answer = new ArrayList<>();
        
        for(String str : words){
            
            HashMap<String, String> mapFromPattern = new HashMap<>();
            HashMap<String, String> mapFromWords = new HashMap<>();
            boolean flag = true;

            for(int i=0; i<str.length(); i++){
                String fromPattern = Character.toString(pattern.charAt(i));
                String fromWord = Character.toString(str.charAt(i));
                
                String fromPatternMap = mapFromPattern.get(fromPattern);
                String fromWordMap = mapFromWords.get(fromWord);

                
                if(fromPatternMap == null && fromWordMap == null){
                    mapFromPattern.put(fromPattern, fromWord);
                    mapFromWords.put(fromWord, fromPattern);
                } else if((fromPatternMap == null && fromWord != null) || (fromPatternMap != null && fromWord == null)){
                    flag = false;
                    break;
                } else if (fromPatternMap.equals(fromWord) != true || fromWordMap.equals(fromPattern) != true){
                    flag = false;
                    break;
                }
            }
            if(flag) answer.add(str);
        }
        
        return answer;
    }
}