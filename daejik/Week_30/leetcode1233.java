class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder, Comparator.comparing(s -> s.length()));
        Set<String> seen = new HashSet<>();

        for(String str : folder){
            boolean isInside = false;
            int slashIndex = 1;

            while((slashIndex = str.indexOf("/", slashIndex)) > 0){
                String tempStr = str.substring(0, slashIndex);

                if(seen.contains(tempStr) == true){
                    isInside = true;
                    break;
                }
                slashIndex++;
            }

            if(isInside == false){
                seen.add(str);
            }
        }

        return new ArrayList<>(seen); 
    }
}