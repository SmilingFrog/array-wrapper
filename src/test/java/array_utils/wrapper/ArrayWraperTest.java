package array_utils.wrapper;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class ArrayWraperTest {

	@Test(expected=IllegalArgumentException.class)
	public void givenNullThrowsException() throws Exception {
		int[] arr = null;
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
	}

	@Test
	public void givenOneDimensionalArrayWrapsIt() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		assertEquals(ArrayWrappers.toString(aw), "[1, 2, 3, 4, 5, 6]");
	}

	@Test
	public void givenOneDimensionalArrayReturnsDImensions1() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		int dimensions = aw.getDimensions();
		assertEquals(1, dimensions);
	}

	@Test
	public void givenIndexReturnTheElement() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		assertEquals(1, aw.get(0));
		assertEquals(6, aw.get(5));
		assertEquals(3, aw.get(2));
	}

	@Test
	public void givenIndexSetElementToNewValue() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		aw.set(0, 10);
		assertEquals(10, arr[0]);
		aw.set(5, 22);
		assertEquals(22, arr[5]);
	}

	@Test
	public void returnsSize() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr);
		int size = aw.getSize();
		assertEquals(6, size);
	}

	@Test
	public void givenIndexesWrapPartOfTheInitialArray() {
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr, 0, 2);
		assertEquals(3, aw.getSize());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenIndexIsOutBoundOfPartiallyWrappedArrayThrowExceptionWhenGet(){
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr, 0, 2);
		aw.get(3);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void givenIndexIsOutBoundOfPartiallyWrappedArrayThrowExceptionWhenSet(){
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr, 0, 2);
		aw.set(3, 4);
	}
	
	@Test
	public void givenPartiallyWrappedArrayIndexesFrom0ToSizeMinus1(){
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr, 3, 4);
		assertEquals(4, aw.get(0));
		assertEquals(5, aw.get(1));
		aw.set(0, 100);
		assertEquals(100, aw.get(0));
	}
	
	@Test
	public void whenToString(){
		int[] arr = { 1, 2, 3, 4, 5, 6 };
		ArrayWrapper aw = ArrayWrappers.wrap(arr, 0, 5);
		assertEquals(Arrays.toString(arr), ArrayWrappers.toString(aw));
	}
	
	@Test
	public void canWrap2DAraay(){
		int[][] arr2D = new int[3][];
		for(int i = 0; i< 3; i++){
			arr2D[i] = new int[3];
			for(int j= 0;j< arr2D[i].length;j++){
				arr2D[i][j] = j;
			}
		}
		ArrayWrapper aw = ArrayWrappers.wrap(arr2D);
		assertEquals(9, aw.getSize());
		assertEquals(1, aw.get(4));
		aw.set(4, 19);
		assertEquals(19, aw.get(4));
	}

}
