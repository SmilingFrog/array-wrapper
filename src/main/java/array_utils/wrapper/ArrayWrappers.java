package array_utils.wrapper;

public class ArrayWrappers {

	private ArrayWrappers() {

	}

	private static class ArrayWrapperAbstract implements ArrayWrapper {

		int[] backedArray;
		int dimensions = 0;
		int firstIndex;
		int lastIndex;

		private ArrayWrapperAbstract(int[] arr, int firstIndex, int lastIndex) {
			this.backedArray = arr;
			dimensions = 1;
			this.firstIndex = firstIndex;
			this.lastIndex = lastIndex;
		}

		public int[] getInitialArray() {
			return backedArray;
		}

		@Override
		public int getDimensions() {
			return dimensions;
		}

		@Override
		public int get(int i) {
			if (i >= 0 && i <= lastIndex - firstIndex) {
				return backedArray[firstIndex + i];
			} else {
				throw new IndexOutOfBoundsException();
			}
		}

		@Override
		public int set(int index, int value) {
			if (index >= 0 && index <= lastIndex - firstIndex) {
				int oldValue = backedArray[index];
				backedArray[firstIndex + index] = value;
				return oldValue;
			} else {
				throw new IndexOutOfBoundsException();
			}

		}

		@Override
		public int getSize() {
			return (lastIndex - firstIndex) + 1;
		}

		@Override
		public int[] toArray() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	private static class ArrayWrapper1D extends ArrayWrapperAbstract {
		private ArrayWrapper1D(int[] arr) {
			super(arr, 0, arr.length - 1);
		}

		public ArrayWrapper1D(int[] arr, int i, int j) {
			super(arr, i, j);
		}
	}

	private static class ArrayWrapper2DAbstract implements ArrayWrapper2D {

		int dimensions;
		int firstDim;
		int secondDim;
		int size;
		ArrayWrapper[] wrappers;

		public ArrayWrapper2DAbstract(ArrayWrapper aw, int firstDim, int secondDim) {
			if (firstDim * secondDim != aw.getSize()) {
				throw new WrongDimensionsException(
						"" + firstDim + " * " + secondDim + "does not equals " + aw.getSize());
			}
			dimensions = 2;
			this.firstDim = firstDim;
			this.secondDim = secondDim;
			size = firstDim;
			wrappers = new ArrayWrapper[size];
			for (int startIndex = 0, index = 0; startIndex < aw.getSize()
					&& index < size; startIndex += secondDim, index++) {
				ArrayWrapper array = ArrayWrappers.wrap(((ArrayWrapperAbstract) aw).getInitialArray(), startIndex,
						startIndex + (secondDim - 1));
				wrappers[index] = array;
			}
		}

		@Override
		public int getDimensions() {
			return dimensions;
		}

		@Override
		public int getSize() {
			return size;
		}

		@Override
		public ArrayWrapper get(int i) {
			return wrappers[i];
		}

		@Override
		public int[][] toArray() {
			int[][] arr2D = new int[size][];
			for (int i = 0; i < size; i++) {
				arr2D[i] = new int[wrappers[i].getSize()];
				for (int j = 0; j < wrappers[i].getSize(); j++) {
					arr2D[i][j] = wrappers[i].get(j);
				}
			}
			return arr2D;
		}

	}

	private static class ArrayWrapper2DWrapper extends ArrayWrapper2DAbstract {

		public ArrayWrapper2DWrapper(ArrayWrapper aw, int firstDim, int secondDim) {
			super(aw, firstDim, secondDim);
		}

	}

	private static class ArrayWrapperFrom2DAbstract implements ArrayWrapper {

		int[][] arr;
		int dimensions;
		int size;
		int firstDim;
		int secondDim;

		public ArrayWrapperFrom2DAbstract(int[][] arr2d) {
			this.arr = arr2d;
			dimensions = 1;
			firstDim = arr.length;
			secondDim = arr[0].length;
			size = calculateSize();
		}

		private int calculateSize() {
			int size = 0;
			for (int i = 0; i < firstDim; i++) {
				size += arr[i].length;
			}
			return size;
		}

		public int[][] getInitialArray() {
			return arr;
		}

		@Override
		public int getDimensions() {
			return dimensions;
		}

		@Override
		public int get(int i) {
			int firstIndex = 0;
			int secondIndex = 0;
			if (i >= 0 && i < size) {
				firstIndex = i / secondDim;
				secondIndex = i % secondDim;
			}
			return arr[firstIndex][secondIndex];
		}

		@Override
		public int set(int i, int j) {
			int firstIndex = 0;
			int secondIndex = 0;
			if (i >= 0 && i < size) {
				firstIndex = i / secondDim;
				secondIndex = i % secondDim;
			}
			int old = arr[firstIndex][secondIndex];
			arr[firstIndex][secondIndex] = j;
			return old;
		}

		@Override
		public int getSize() {
			return size;
		}

		@Override
		public int[] toArray() {
			int[] arrCopy = new int[size];
			for (int i = 0; i < size; i++) {
				arrCopy[i] = get(i);
			}
			return arrCopy;
		}

	}

	private static class ArrayWrapperFrom2D extends ArrayWrapperFrom2DAbstract {

		public ArrayWrapperFrom2D(int[][] arr2d) {
			super(arr2d);
		}

	}

	public static ArrayWrapper wrapAsDim(int[] arr, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayWrapper wrap(int[] arr) {

		if(arr == null){
			throw new IllegalArgumentException();
		}
		return new ArrayWrapper1D(arr);
	}

	public static ArrayWrapper wrap(int[] arr, int i, int j) {
		return new ArrayWrapper1D(arr, i, j);
	}

	public static ArrayWrapper2D wrap(ArrayWrapper aw, int firstDim, int secondDim) {
		return new ArrayWrapper2DWrapper(aw, firstDim, secondDim);
	}

	public static String toString(ArrayWrapper aw) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < aw.getSize(); i++) {
			sb.append(aw.get(i));
			if (i < aw.getSize() - 1) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	public static ArrayWrapper wrap(int[][] arr2d) {
		return new ArrayWrapperFrom2D(arr2d);
	}

}
