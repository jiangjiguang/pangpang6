package com.pangpang6.books.offer.chapter2;

/**
 * 数组排序算法
 */
public class P79_Sort {
    //数组快排，时间o(nlogn)(最差n^2)，空间o(logn)(最差n)，递归造成的栈空间的使用，不稳定
    public static void quickSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        quickSortCore(data, 0, data.length - 1);
    }

    public static void quickSortCore(int[] data, int start, int end) {
        if (end - start <= 0) {
            return;
        }
        int index = quickSortPartition(data, start, end);
        quickSortCore(data, start, index - 1);
        quickSortCore(data, index + 1, end);
    }

    public static int quickSortPartition(int[] data, int start, int end) {
        //选择第一个值作为基准
        int pivot = data[start];
        int left = start, right = end;
        while (left < right) {
            while (left < right && data[right] >= pivot) {
                right--;
            }

            if (left < right) {
                data[left++] = data[right];
            }
            while (left < right && data[left] < pivot) {
                left++;
            }

            if (left < right) {
                data[right--] = data[left];
            }
        }

        data[left] = pivot;
        return left;
    }

    public static void testQuickSort() {
        int[] data = {5, 4, 3, 1, 2};
        quickSort(data);
        System.out.print("数组快速排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    //数组二路归并，时间o(nlogn)(最差n^2)，空间o(n),稳定
    public static int[] mergeSort(int[] data) {
        if (data == null || data.length <= 1) {
            return data;
        }
        mergeSortCore(data, 0, data.length - 1);
        return data;
    }

    //对data[start~mid]，data[mid+1~end]归并
    //典型的分治：结束条件+分+和
    public static void mergeSortCore(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSortCore(data, start, mid);
        mergeSortCore(data, mid + 1, end);
        mergeSortMerge(data, start, mid, end);
    }

    public static void mergeSortMerge(int[] data, int start, int mid, int end) {
        if (start >= end) {
            return;
        }
        //临时数组
        int[] temp = new int[end - start + 1];
        int left = start, right = mid + 1, tempIndex = 0;
        while (left <= mid && right <= end) {
            if (data[left] < data[right]) {
                temp[tempIndex++] = data[left++];
            } else {
                temp[tempIndex++] = data[right++];
            }
        }
        while (left <= mid) {
            temp[tempIndex++] = data[left++];
        }
        while (right <= end) {
            temp[tempIndex++] = data[right++];
        }
        for (int i = 0; i < temp.length; i++) {
            data[start + i] = temp[i];
        }
    }

    public static void testMergeSort() {
        int[] data = {5, 4, 3, 1, 2};
        mergeSort(data);
        System.out.print("数组归并排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    //数组堆排序，时间o(nlogn)，空间o(1),不稳定
    //一般升序采用大顶堆，降序采用小顶堆
    public static void heapSort(int[] data) {
        //1.构建大顶堆
        for (int i = data.length / 2 - 1; i >= 0; i--) {
            //从第一个非叶子结点从下至上，从 右至左 调整结构（因为该节点右边没有叶子节点）
            //因为叶子节点已经在构建的范围内了，因此不需要从叶子节点开始
            adjustHeap(data, i, data.length);
        }
        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = data.length - 1; j > 0; j--) {
            swap(data, 0, j);//将堆顶元素与末尾元素进行交换
            adjustHeap(data, 0, j);//重新对堆进行调整
        }
    }

    //调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
    private static void adjustHeap(int[] arr, int i, int length) {
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

    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }


    public static void testHeapSort() {
        int[] data = {5, 4, 3, 1, 2};
        heapSort(data);
        System.out.print("数组堆排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    //数组冒泡，时间o(n^2)，空间o(1)，稳定
    public static void bubbleSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 1; j < data.length - i; j++) {
                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    public static void testBubbleSort() {
        int[] data = {5, 4, 3, 1, 2};
        bubbleSort(data);
        System.out.print("数组冒泡排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    //数组选择排序，时间o(n^2)，空间o(1)，不稳定
    public static void selectionSort(int[] data) {
        if (data == null || data.length <= 1)
            return;
        for (int i = 0; i < data.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < data.length; j++) {
                if (data[j] < data[minIndex])
                    minIndex = j;
            }
            if (i != minIndex) {
                int temp = data[i];
                data[i] = data[minIndex];
                data[minIndex] = temp;
            }
        }
    }

    public static void testSelectionSort() {
        int[] data = {5, 4, 3, 1, 2};
        selectionSort(data);
        System.out.print("数组选择排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    //数组插入排序，时间o(n^2)，空间o(1)，稳定
    public static void insertionSort(int[] data) {
        if (data == null || data.length <= 1) {
            return;
        }
        for (int i = 1; i < data.length; i++) {
            int j = i;
            int temp = data[i];
            while (j > 0 && data[j - 1] > temp) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = temp;
        }
    }

    public static void testInsertionSort() {
        int[] data = {5, 4, 3, 1, 2};
        insertionSort(data);
        System.out.print("数组插入排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    //数组希尔排序(插入排序缩小增量)，时间o(n^1.3)，空间o(1)，不稳定
    //有人在大量的实验后得出结论;当n在某个特定的范围后希尔排序的比较和移动次数减少至n^1.3
    public static void shellSort(int[] data) {
        if (data == null || data.length <= 1)
            return;
        for (int d = data.length / 2; d > 0; d = d / 2) {
            for (int i = d; i < data.length; i++) {
                int cur = i;
                int temp = data[i];
                while (cur >= d && data[cur - d] > temp) {
                    data[cur] = data[cur - d];
                    cur = cur - d;
                }
                data[cur] = temp;
            }
        }
    }

    public static void testShellSort() {
        int[] data = {5, 4, 3, 1, 2};
        shellSort(data);
        System.out.print("数组希尔排序：\t");
        for (int item : data) {
            System.out.print(item);
            System.out.print('\t');
        }
        System.out.println();
    }

    public static void main(String[] args) {
        testQuickSort();
        testMergeSort();
        testHeapSort();
        testBubbleSort();
        testSelectionSort();
        testInsertionSort();
        testShellSort();
    }
}
