package array_utils.wrapper;

public interface ArrayWrapper {

	int getDimensions();

	int get(int i);

	int set(int i, int j);

	int getSize();

	int[] toArray();

}
