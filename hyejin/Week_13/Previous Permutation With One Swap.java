class Solution {
    public int[] prevPermOpt1(int[] arr) {
        // 뒤에서부터 탐색하며, 처음으로 증가하는 부분 인덱스 구하기
        int index = arr.length-1;
        for(int i=arr.length-1; i>0; i--) {
            if(arr[i-1] > arr[i]) {
                index = i-1;
                break;
            }
        }

        // 뒤에서부터 index 바로 뒤까지 탐색 -> arr[index]보다 작은 값과 swap
        for(int i=arr.length-1; i>index; i--) {
            if(arr[i] < arr[index] && arr[i] != arr[i-1]) {
                arr = swap(arr, i, index);
                break;
            }
        }
        return arr;
    }

    public int[] swap(int[] arr, int index1, int index2) {
        int temp = arr[index2];
        arr[index2] = arr[index1];
        arr[index1] = temp;

        return arr;
    }

}