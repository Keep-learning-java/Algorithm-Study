


class Solution {
    public String smallestSubsequence(String s) {
		int[] freq = new int[26];
		boolean[] used = new boolean[26];
		Stack<Character> stack = new Stack<Character>();
		
		// 알파벳이 몇번 나왔는지
		for (char ch : s.toCharArray()) {
			freq[ch - 'a']++;
		}
		
		for (char ch : s.toCharArray()) {
			// 빈도수 차감 -> 뒤에 ch라는 글자가 몇개 남았는지를 알기 위함
			freq[ch -'a']--;
			
			// 이미 사용했다면 필요없다 SKIP
			if (used[ch - 'a'])
				continue;
			
			// 스택 top이 나보다 사전순으로 뒤 이고
			// 스택 top이 뒤에 또 있으면
			while(!stack.isEmpty() && ch < stack.peek() && freq[stack.peek() - 'a'] > 0) {
				// 나중에 넣어도 되는 상황이므로
				// 스택 top을 빼버린다.
				used[stack.pop() - 'a'] = false;
			}
			
			// 대신 내가 들어간다
			stack.push(ch);
			used[ch - 'a'] = true;
		}
		
		StringBuilder sb = new StringBuilder();
		while (!stack.isEmpty())
			sb.append(stack.pop());
		
		return sb.reverse().toString();        
    }
}