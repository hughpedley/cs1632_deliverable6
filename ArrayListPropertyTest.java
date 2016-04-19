import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/**
 * This is the final deliverable for CS 1632.
 * 
 * For this deliverable, I chose to do property testing on ArrayLists.
 * This is mostly because I find myself using ArrayLists very frequently
 * in my projects, and would like to feel reinforced that they work
 * exactly as intended and expected.
 */
public class ArrayListPropertyTest {
	
	Random random = new Random();
	ArrayList<ArrayList<Integer>> testArrayLists = new ArrayList<ArrayList<Integer>>();
	
	@Before
	public void setUp() throws Exception {
		ArrayList<Integer> addToList = new ArrayList<Integer>();
		
		//100 ArrayLists of random sizes with random ints at every index,
		//All in a single ArrayList indicated up top
		for(int i = 0; i < 100; i++) {
			addToList = new ArrayList<Integer>();
			for(int j = 0; j < random.nextInt(99) + 1; j++) {
				addToList.add(random.nextInt(100));
			}
			this.testArrayLists.add(addToList);
		}
	}
	
	/**
	 * ArrayLists should be the same size after the sort as they are before it
	 */
	@Test
	public void testSameSizeAfterSort() {
		for(int i = 0; i < testArrayLists.size(); i++) {
			ArrayList<Integer> testArrayListSorted = new ArrayList<Integer>();
			ArrayList<Integer> testArrayListNotSorted = new ArrayList<Integer>();
			Collections.sort(testArrayListSorted);
			
			//Compares size of each ArrayList with their sorted counterparts
			assertEquals(testArrayListSorted.size(), testArrayListNotSorted.size());
		}
	}
	
	/**
	 * ArrayLists of integers should still be exclusively arraylists of integers, even after
	 * being sorted. This method confirms that
	 */
	@Test
	public void testArrayListsSameClassType() {
		for(int i = 0; i < testArrayLists.size(); i++) {
			ArrayList<Integer> testArrayList = testArrayLists.get(i);
			Collections.sort(testArrayList);
			
			//Determines that every ArrayList is an instance of ArrayList<Integer> still after being sorted
			assertTrue(testArrayList instanceof ArrayList<?>);
			
		}
	}
	
	/**
	 * We want to confirm that an arraylist is actually sorted after
	 * we run collections.sort() on it.
	 */
	@Test
	public void testArrayListsSorted() {
		for(int i = 0; i < testArrayLists.size(); i++) {
			ArrayList<Integer> testArrayList = testArrayLists.get(i);
			Collections.sort(testArrayList);
			int difference = testArrayList.get(0) - testArrayList.get(testArrayList.size() - 1);
			
			//Checks that the last number is greater than the first
			assertTrue(difference <= 0);
		}
	}
	
	/**
	 * We want to confirm that sorting an ArrayList is idempotent,
	 * meaning that sorting an ArrayList once gives the same result
	 * as sorting an ArrayList any number of times.
	 */
	@Test
	public void testIdempotent() {
		for(int i = 0; i < testArrayLists.size(); i++) {
			ArrayList<Integer> testArrayList = testArrayLists.get(i);
			Collections.sort(testArrayList);
			ArrayList<Integer> sortedOnce = testArrayList;
			Collections.sort(testArrayList);
			//test now sorted twice
			
			//Checks that every ArrayList is the same even after sorting a second time
			assertEquals(testArrayList, sortedOnce);
		}
	}
	
	/**
	 * Duplicate sorted ArrayLists will give the exact same results as each other,
	 * despite being sorted after duplicated.
	 */
	@Test
	public void testDuplicateSorts() {
		for(int i = 0; i < testArrayLists.size(); i++) {
			ArrayList<Integer> testArrayList = testArrayLists.get(i);
			ArrayList<Integer> duplicate = testArrayList;
			Collections.sort(testArrayList);
			Collections.sort(duplicate);
			
			//Checks that two separate ArrayLists are equivalent after a sort
			assertEquals(testArrayList, duplicate);
		}
	}
}