import java.util.Arrays;

public class MyList<T> {

    Object[] list;
    private int count = 0;
    private int endIdx = count;  // index

    public MyList() {
        this.list = (T[]) new Object[10];
    }

    public MyList(int capacity) {
        this.list = (T[]) new Object[capacity];
    }

    public int size() {
        return count;
    }

    public int getCapacity() {
        return list.length;
    }

    public void add(T data) {
        if (list.length <= count) {
            Object[] tempList = (T[]) new Object[2* list.length];

            for (int i=0; i< list.length; i++) {
                tempList[i] = list[i];
            }

            list = tempList;
        }
        list[endIdx++] = data;
        count++;
    }

    public T get(int index) {
        return (T) list[index];
    }

    public void remove(int index) {
        if (index < 0 || list.length <= index) {
            return;
        }

        Object[] tempList = (T[]) new Object[getCapacity()];

        for (int i=0; i<list.length; i++) {
            if (i == index) {
                continue;
            }

            if (index < i) {
                tempList[i-1] = list[i];
            } else {
                tempList[i] = list[i];
            }
        }

        list = tempList;
        count--;
        endIdx--;
    }

    public void set(int index, T data) {
        if (index < 0 || list.length <= index) {
            return;
        }

        list[index] = data;
    }

    public String toString() {
        String stringList = "[";
        for (int i=0; i<size(); i++) {
            stringList += list[i];
            stringList +=",";
        }
        int end = stringList.length();
        stringList = stringList.substring(0, end - 1);  // remove the last comma inside the stringList "stringList"
        stringList +="]";

        return stringList;
    }

    public int indexOf(T data) {
        for (int i=0; i<list.length; i++) {
            if (data.equals(list[i])) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(T data) {
        for (int i=endIdx-1; 0<=i; i--) {
            if (data.equals(list[i])) {
                return i;
            }
        }

        return -1;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T[] toArray() {
        return Arrays.copyOf((T[])list, count);
    }

    public void clear() {
        for (int i=0; i<list.length; i++) {
            list[i] = null;
        }

        count = 0;
    }



    public MyList<T> sublist(int start, int end) {
        if ((start < 0 || list.length <= start) && (end < 0 || list.length <= end)) {
            return null;
        }

        int subListLength = end - start;
        MyList<T> subListObj = new MyList<>(subListLength);

        for (int i=start; i<end; i++) {
            subListObj.add((T) list[i]);
        }

        return subListObj;
    }

    public boolean contains(T data) {
        for (Object o : list) {
            if (data.equals(o)) {
                return true;
            }
        }

        return false;
    }

}
