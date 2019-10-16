package com.pangpang6.books.offer.chapter5;

/**
 * Description: 最小的k个数
 **/
public class P209_KLeastNumbers {
    //选择排序,时间复杂度o(N*k),适合k较小的情况
    public static int getLeastNumbers1(int[] data, int k) {
        if (data == null || data.length == 0 || k > data.length) {
            return 0;
        }

        for (int i = 0; i < k; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex])
                    minIndex = j;
            }
            if (minIndex != i) {
                int temp = data[minIndex];
                data[minIndex] = data[i];
                data[i] = temp;
            }
        }
        //第k小，也就是排序后下标为k-1的元素。
        for (int i = 0; i < k; i++) {
            System.out.println(data[i]);
        }
        return data[k - 1];
    }

    //使用分区函数解决，时间复杂度o(n)(没懂),会修改原数组
    public static int getLeastNumbers2(int[] data, int k) {
        if (data == null || data.length == 0 || k > data.length) {
            return 0;
        }
        int left = 0, right = data.length - 1;
        int index = partition(data, left, right);
        while (index != k - 1) {
            if (index < k - 1) {
                index = partition(data, index + 1, right);
            } else {
                index = partition(data, left, index - 1);
            }
        }
        for (int i = 0; i < k; i++) {
            System.out.println(data[i]);
        }
        return data[k - 1];
    }

    public static int partition(int[] data, int left, int right) {
        int pivot = data[left];
        while (left < right) {
            while (left < right && data[right] >= pivot) {
                right--;
            }
            if (left < right) {
                data[left] = data[right];
            }
            while (left < right && data[left] < pivot) {
                left++;
            }
            if (left < right) {
                data[right] = data[left];
            }
        }
        data[left] = pivot;
        return left;
    }

    //https://www.cnblogs.com/chengxiao/p/6129630.html
    //使用最大堆解决，不会修改原数组，适合处理海量数据
    //k个元素的最大堆调整时间复杂度为o(logk),所以总的时间复杂度为o(nlogk)
    public static int getLeastNumbers3(int[] data, int k) {
        if (data == null || data.length == 0 || k > data.length) {
            return 0;
        }
        int[] heap = new int[k];

        for (int i = 0; i < k; i++) {
            heap[i] = data[i];
        }

        //构建最大堆  从下往上构建
        for (int i = heap.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从右至左调整结构
            adjustHeap(heap, i, heap.length);
        }

        //调整最大堆
        for (int i = k; i < data.length; i++) {
            if (data[i] > heap[0]) {
                continue;
            }
            heap[0] = data[i];
            adjustHeap(heap, 0, heap.length);
        }

        //长度为k的最大堆中下标为1的元素就是data数组中第k小的数据值
        System.out.println("最大堆: start");
        for (int t = 0; t < k; t++) {
            System.out.println(heap[t]);
        }
        System.out.println("最大堆: end");

        return heap[1];
    }

    public static void adjustHeap(int[] arr, int i, int length) {
        //先取出当前元素i
        int temp = arr[i];
        //从i结点的左子结点开始，也就是2i+1处开始
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            //如果左子结点小于右子结点，k指向右子结点
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            //如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;//将temp值放到最终的位置
    }

    public static void main(String[] args) {
        int[] data1 = {6, 1, 3, 5, 4, 2};
        System.out.println(getLeastNumbers1(data1, 3));
        int[] data2 = {6, 1, 3, 5, 4, 2};
        System.out.println(getLeastNumbers2(data2, 5));
        int[] data3 = {8, 1, 3, 5, 10, 2};

        System.out.println(getLeastNumbers3(data3, 4));

    }
}
