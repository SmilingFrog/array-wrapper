package array_utils.wrapper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayWrapper2DTest {

	@Test
	public void givenRightDimensionsCanWrapAs2Dimensional() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 2, 3);
		assertNotNull(aw2D);
	}

	@Test(expected = WrongDimensionsException.class)
	public void givenWrongDimensionsThrowException() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 3, 3);
	}

	@Test
	public void dimensionIs2() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 2, 3);
		int dimensions = aw2D.getDimensions();
		assertEquals(2, dimensions);
	}

	@Test
	public void givenFirstDimension2ReturnsSize2() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 2, 3);
		assertEquals(2, aw2D.getSize());
	}

	@Test
	public void givenIndexReturnWrapper() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 2, 3);
		aw = aw2D.get(0);
		assertNotNull(aw);
		assertEquals(3, aw.getSize());
		assertEquals(1, aw.get(0));
		assertEquals(2, aw.get(1));
		assertEquals(3, aw.get(2));
		aw = aw2D.get(1);
		assertEquals(3, aw.getSize());
		assertEquals(4, aw.get(0));
		assertEquals(5, aw.get(1));
		assertEquals(6, aw.get(2));
	}
	
	@Test
	public void createsNew2DArray(){
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 3, 2);
		int[][] arr2D = aw2D.toArray();
		assertEquals(3, arr2D.length);
		for(int i = 0 ; i < arr2D.length;i++){
			assertEquals(2, arr2D[i].length);
		}
	}

	@Test
	public void testWorkingWithWrapper() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		ArrayWrapper2D aw2D = ArrayWrappers.wrap(aw, 2, 3);
		for (int i = 0; i < aw2D.getSize(); i++) {
			for (int j = 0; j < aw2D.get(i).getSize(); j++) {
				aw2D.get(i).set(j, (aw2D.get(i).get(j)) * 10);
			}
		}
		for (int i = 0; i < aw2D.getSize(); i++) {
			System.out.println(ArrayWrappers.toString(aw2D.get(i)));
		}
	}
}
