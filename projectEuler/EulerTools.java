package projectEuler;

import java.util.List;

class EulerTools {
	public static boolean isIntegral(double d) {
		return d == (int) d;
	}

	public static <T> void printList(List<T> list) {
		printList(list, ", ");
	}

	public static <T> void printList(List<T> list, String separator) {
		for (int i = 0; i < list.size() - 1; i++)
			System.out.print(list.get(i) + separator);
		System.out.print(list.get(list.size() - 1));
	}

	public static <T> boolean equals(List<T> list1, List<T> list2) {
		if (list1.size() != list2.size())
			return false;
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equals(list2.get(i)))
				return false;
		}
		return true;
	}
}