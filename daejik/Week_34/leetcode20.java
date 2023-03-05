class Solution {
    public boolean isValid(String s) {
        int strLen = s.length();
        Stack<String> stack = new Stack<>();

        for(String i : s.split("")){

            if(i.equals("(") || i.equals("{") || i.equals("[")){
                stack.push(i);
            } else {
                if(stack.empty()){
                    return false;
                }
            } 
            
            if(i.equals(")")){
                if(!"(".equals(stack.peek())){
                    return false;
                }
                stack.pop();
            } else if(i.equals("}")){
                if(!"{".equals(stack.peek())){
                    return false;
                }
                stack.pop();
            } else if(i.equals("]")){
                if(!"[".equals(stack.peek())){
                    return false;
                }
                stack.pop();
            }     
        }
        if(stack.size() == 0){
            return true;
        } else {
            return false;
        }
    }
}