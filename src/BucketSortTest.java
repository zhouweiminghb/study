public class BucketSortTest {
	// public static int count = 0;

	public static void main(String[] args) {

		int[] data = new int[] { 5, 3, 6, 2, 1, 6, 4, 8, 7, 9 };
		print(data);
		bucketSort(data, 0, 10);
		print(data);

	}

	public static void bucketSort(int[] data, int min, int max) {
		// ��������
		int[] tmp = new int[data.length];
		// buckets���ڼ�¼������Ԫ�ص���Ϣ
		// buckets���鶨����max-min��Ͱ
		int[] buckets = new int[max - min];
		// ����ÿ��Ԫ�������г��ֵĴ���
		for (int i = 0; i < data.length; i++) {
			buckets[data[i] - min]++;
		}
		// ���㡰���롱��Ͱ�ڵ�Ԫ�������������е�λ��
		for (int i = 1; i < max - min; i++) {
			buckets[i] = buckets[i] + buckets[i - 1];
		}
		// ��data�е�Ԫ����ȫ���Ƶ�tmp������
		System.arraycopy(data, 0, tmp, 0, data.length);
		// ����buckets�����е���Ϣ���������еĸ�Ԫ�ط�����Ӧλ��
		for (int k = data.length - 1; k >= 0; k--) {
			data[--buckets[tmp[k] - min]] = tmp[k];
		}
	}

	public static void print(int[] data) {
		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + "\t");
		}
		System.out.println();
	}

}