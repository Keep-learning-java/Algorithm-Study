class Solution {
    public boolean isValid(String s) {
		Stack<Character> stack = new Stack<>();

		HashMap<Character, Character> pairs = new HashMap<>();
		pairs.put(')', '(');
		pairs.put(']', '[');
		pairs.put('}', '{');
				
		// 한 글자씩
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);

			// 열리는 괄호면
			if (ch == '(' || ch == '[' || ch == '{')
				stack.push(ch);
			// 닫히는 괄호라면
			else {
				// 비어 있으면 실패
				if (stack.isEmpty())
					return false;
				
				// 맨 위 꺼냄
				char top = stack.pop();

				// 맨위가 짝꿍이어야 함
				char pair = pairs.get(ch);
				if (pair != top)
					return false;
			}
		}
		
		return stack.isEmpty() ? true : false;        
    }
}