class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {

		long answer = 0;
		int deliver = 0;
		int pickup = 0;
		int size = cap;

		// 처음 배달할 곳
		deliver = getNextDeliver(n, deliveries);

		// 처음 픽업할 곳
		pickup = getNextPickup(n, pickups);

		while (true) {
			if (deliver == -1 && pickup == -1)
				break;

			answer += (Math.max(deliver, pickup) + 1) * 2;

			// 배달
			while (true) {
				// 배달할 곳이 없음
				if (deliver == -1)
					break;
				// 실을 공간 없음
				if (size == 0)
					break;

				// 현재 위치에 배달 가능
				if (size >= deliveries[deliver]) {
					size -= deliveries[deliver]; // 적재가능 차감
					deliveries[deliver] = 0;
					deliver--;
				} else {
					deliveries[deliver] -= size;
					size = 0;
				}

				// 다음 목적지 정하기
				if (size == 0) {
					deliver = getNextDeliver(deliver+1, deliveries);
				}

			}

			size = cap;

			// 픽업
			while (true) {
				// 픽업할 곳이 없음
				if (pickup == -1)
					break;
				// 실을 공간 없음
				if (size == 0)
					break;

				// 현재 위치에 픽업 가능
				if (size >= pickups[pickup]) {
					size -= pickups[pickup]; // 픽업가능 차감
					pickups[pickup] = 0;
					pickup--;
				} else {
					pickups[pickup] -= size;
					size = 0;
				}

				// 다음 목적지 정하기
				if (size == 0) {
					pickup = getNextPickup(pickup+1, pickups);
				}

			}
			size = cap;
		}
		return answer;
    }
    
    private int getNextDeliver(int start, int[] deliveries) {
		for (int i = start - 1; i >= 0; i--) {
			if (deliveries[i] != 0) {
				return i;
			}
		}

		return -1;
	}

	private int getNextPickup(int start, int[] pickups) {
		for (int i = start - 1; i >= 0; i--) {
			if (pickups[i] != 0) {
				return i;
			}
		}

		return -1;
	}
}