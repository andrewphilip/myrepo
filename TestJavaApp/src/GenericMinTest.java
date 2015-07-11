import java.net.URLClassLoader;
import java.util.Arrays;


public class GenericMinTest {

	public static  <E extends Comparable<E>>E min(E i, E j, E k ){
		E minimum = i;
		if(j.compareTo(minimum) < 0){
			minimum = j;
		}
		if(k.compareTo(minimum) < 0){
			minimum =k;
		}
		return minimum;
	}
	public static void main(String[] args) {

		System.out.println(Arrays.toString(((URLClassLoader)GenericMinTest.class.getClassLoader()).getURLs()));	
		System.out.println("Minimum of integers :"+ min(7,3,9));
		System.out.println("Minimum of String :"+ min("philip","andrew","sheela"));
		
	}

}
