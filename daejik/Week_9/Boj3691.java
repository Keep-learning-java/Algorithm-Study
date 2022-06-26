package Week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj3691 {
	static int totalTestCase;
	static int numOfIngredients, money;
	static int resultOfSimulation;
	static HashMap<String, ArrayList<IngredientsClass>> ingredientsMap = new HashMap<>();
	static ArrayList<IngredientsClass>[] ingredientArrayList;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		totalTestCase = Integer.parseInt(br.readLine());
		
		while(totalTestCase-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			numOfIngredients = Integer.parseInt(st.nextToken());
			money = Integer.parseInt(st.nextToken());
			
			inputIngredientsInfo(br, st);
			moveFromMapToList();
			resultOfSimulation = 0;
			simulation(0, money, Integer.MAX_VALUE);
			System.out.println(resultOfSimulation);
		}
	}
	public static void moveFromMapToList(){
		ingredientArrayList = new ArrayList[ingredientsMap.size()];
		int idx = 0;
		for(String type : ingredientsMap.keySet()) {
			ingredientArrayList[idx] = ingredientsMap.get(type);
			idx++;
		}
	}
	public static void simulation(int idx, int testMoney, int testQuality) {
		if(idx >= ingredientArrayList.length) {
			if(resultOfSimulation < testQuality) resultOfSimulation = testQuality;
			return;
		}
		
		for(int i=0; i < ingredientArrayList[idx].size(); i++) {
			if(testMoney >= ingredientArrayList[idx].get(i).price) {
				if(testQuality > ingredientArrayList[idx].get(i).quality)
					simulation(idx + 1, testMoney - ingredientArrayList[idx].get(i).price, ingredientArrayList[idx].get(i).quality);
				else
					simulation(idx + 1, testMoney - ingredientArrayList[idx].get(i).price, testQuality);
			}
		}
	}
	public static void inputIngredientsInfo(BufferedReader br, StringTokenizer st) throws IOException {
		ingredientsMap.clear();
		
		while(numOfIngredients-- > 0) {
			st = new StringTokenizer(br.readLine());

			String type = st.nextToken();
			String name = st.nextToken();
			Integer price = Integer.parseInt(st.nextToken());
			Integer quality = Integer.parseInt(st.nextToken());
			
			IngredientsClass ingredientsClass = new IngredientsClass(price, quality);
			
			ArrayList<IngredientsClass> arrayListOfIngredients = ingredientsMap.get(type);
			
			if(arrayListOfIngredients == null) {
				arrayListOfIngredients = new ArrayList<>();
			}
			arrayListOfIngredients.add(ingredientsClass);
			
			ingredientsMap.put(type, arrayListOfIngredients);
		}
	}	
}
class IngredientsClass{
	int price, quality;
	
	IngredientsClass(int price, int quality){
		this.price = price;
		this.quality = quality;
	}
	
	@Override
	public String toString() {
		return "price:"+this.price+", quality:"+this.quality;
	}
}