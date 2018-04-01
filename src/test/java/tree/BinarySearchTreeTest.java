package tree;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by mazhibin on 2018/4/1
 */
public class BinarySearchTreeTest {

    @Test
    public void test() throws Exception {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();

        assertEquals(0, tree.size());
        assertEquals(null, tree.get(100));
        assertEquals(null, tree.get(5));
        assertEquals(null, tree.min());
        assertEquals(null, tree.max());
        assertEquals(null, tree.floor(100));
        assertEquals(null, tree.select(5));
    }

    @Test
    public void test1() throws Exception {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
        for (int i = 0; i < 10; i++) {
            tree.put(i, i);
        }
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();

        assertEquals(10, tree.size());
        assertEquals(null, tree.get(100));
        assertEquals(Integer.valueOf(5), tree.get(5));
        assertEquals(Integer.valueOf(0), tree.min());
        assertEquals(Integer.valueOf(9), tree.max());
        assertEquals(Integer.valueOf(9), tree.floor(100));
        assertEquals(Integer.valueOf(5), tree.select(5));
    }

    @Test
    public void test2() throws Exception {
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
        tree.put(5, 5);
        tree.put(3, 3);
        tree.put(2, 2);
        tree.put(4, 4);
        tree.put(7, 7);
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();

        assertEquals(5, tree.size());
        assertEquals(null, tree.get(100));
        assertEquals(Integer.valueOf(4), tree.get(4));
        assertEquals(Integer.valueOf(2), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());
        assertEquals(Integer.valueOf(5), tree.floor(6));
        assertEquals(Integer.valueOf(4), tree.select(2));
        assertEquals(Arrays.asList(2,3,4,5,7), tree.keys());
        
        tree.deleteMin();
        assertEquals(Integer.valueOf(3), tree.min());
        tree.deleteMin();
        assertEquals(Integer.valueOf(4), tree.min());
        tree.deleteMax();
        assertEquals(Integer.valueOf(5), tree.max());
        tree.deleteMax();
        assertEquals(Integer.valueOf(4), tree.max());
    }
    
    @Test
    public void testDelete(){
        BinarySearchTree<Integer, Integer> tree = new BinarySearchTree<Integer, Integer>();
        tree.put(5, 5);
        tree.put(3, 3);
        tree.put(2, 2);
        tree.put(4, 4);
        tree.put(7, 7);
        tree.preOrder();
        tree.inOrder();
        tree.postOrder();
        
        tree.delete(4);
        assertEquals(4, tree.size());
        assertEquals(Integer.valueOf(2), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());
        
        tree.delete(2);
        assertEquals(3, tree.size());
        assertEquals(Integer.valueOf(3), tree.min());
        assertEquals(Integer.valueOf(7), tree.max());

        tree.delete(7);
        assertEquals(2, tree.size());
        assertEquals(Integer.valueOf(3), tree.min());
        assertEquals(Integer.valueOf(5), tree.max());
    }

}