
class FoodRatings {
	static class Food {
		String name;
		String cuisine;
		int rating;

		Food(String name, String cuisine, int rating) {
			this.name = name;
			this.cuisine = cuisine;
			this.rating = rating;
		}
	}

	HashMap<String, Food> foodMap;
	HashMap<String, PriorityQueue<Food>> cuisineMap; // 음식카테고리 : 음식들

	public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
		foodMap = new HashMap<>();
		cuisineMap = new HashMap<>();

		for (int i = 0; i < foods.length; i++) {
			Food food = new Food(foods[i], cuisines[i], ratings[i]);
			foodMap.put(foods[i], food);
			PriorityQueue<Food> pq = cuisineMap.getOrDefault(cuisines[i], new PriorityQueue<Food>(
					(a, b) -> a.rating == b.rating ? a.name.compareTo(b.name) : b.rating - a.rating));
			pq.add(food);
            cuisineMap.put(cuisines[i], pq);
		}
	}

	public void changeRating(String food, int newRating) {
		Food f = foodMap.get(food);
		PriorityQueue<Food> pq = cuisineMap.get(f.cuisine);

		// pq에서 제거
		pq.remove(f);

		// 다시 넣기
		f.rating = newRating;
		pq.add(f);
	}

	public String highestRated(String cuisine) {
		return cuisineMap.get(cuisine).peek().name;
	}

}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */