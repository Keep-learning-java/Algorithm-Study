class FoodRatings {

    HashMap<String, TreeSet<String>> highestRatingFoodInCuisine = new HashMap<>();
    HashMap<String, String> foodToCuisine = new HashMap<>();
    HashMap<String, Integer> foodToRaiting = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;

        for(int i=0; i<n; i++){
            String food = foods[i];
            String cuisine = cuisines[i];
            int raiting = ratings[i];

            foodToCuisine.put(food, cuisine);
            foodToRaiting.put(food, raiting);

            TreeSet<String> foodOfThisCuisine = highestRatingFoodInCuisine.getOrDefault(cuisine, 
            new TreeSet<String> ((a,b)->
                foodToRaiting.get(a).equals(foodToRaiting.get(b)) ? a.compareTo(b) : foodToRaiting.get(b)-foodToRaiting.get(a)));
			// Both comparators are equal
			/* new Comparator<String>(){
                @Override
                public int compare(String a, String b){
                    int aRat = foodToRat.get(a);
                    int bRat = foodToRat.get(b);
                    
                    if(aRat != bRat) return bRat - aRat; // largest rating first
                    for(int i = 0; i < Math.min(a.length(), b.length()); i++){
                        if(a.charAt(i) != b.charAt(i)) return a.charAt(i) - b.charAt(i);
                    }
                    return a.length() - b.length();
                }
            })
			*/

            foodOfThisCuisine.add(food);
            highestRatingFoodInCuisine.put(cuisine, foodOfThisCuisine);
        }
    }
    
    // CompareTo() is used to compare whether 2 strings are equal in hashSet! 
    // So remove, change value of key in HashMap, then insert again
    public void changeRating(String food, int newRating) {
        String cuisine = foodToCuisine.get(food);
        foodToRaiting.put(food, newRating);

        TreeSet<String> foodOfThisCuisine = highestRatingFoodInCuisine.get(cuisine);
        foodOfThisCuisine.remove(food);
        foodOfThisCuisine.add(food);
        highestRatingFoodInCuisine.put(cuisine, foodOfThisCuisine);        
    }
    
    public String highestRated(String cuisine) {
        return highestRatingFoodInCuisine.get(cuisine).first();
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */