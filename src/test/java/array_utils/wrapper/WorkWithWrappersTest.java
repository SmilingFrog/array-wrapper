package array_utils.wrapper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class WorkWithWrappersTest {

	@Test
	public void test() {
		int[] arr = {1,2,3,4,5,6,7,8,9,0};
		Arrays.sort(arr);
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2d = ArrayWrappers.wrap(aw, 2, 5);
		int[][] arr2d = aw2d.toArray();
		System.out.println(Arrays.deepToString(arr2d));
		aw = ArrayWrappers.wrap(arr2d);
		System.out.println(ArrayWrappers.toString(aw));
		aw.set(4, 100);
		System.out.println(Arrays.deepToString(arr2d));
		int[] newArr = aw.toArray();
		System.out.println(Arrays.toString(newArr));
	}

}
