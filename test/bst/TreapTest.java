package bst;

import org.junit.Before;
import org.junit.Test;

public class TreapTest {

    private static int[] keys = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Treap treap = new Treap();

    @Before
    public void initTree() {
        treap = treap.build(keys);
    }

    @Test
    //condition: all keys in left subtree must be less or equal to all keys in right subtree
    public void testTreap() {
        treap.printTreap(treap);
    }

    @Test
    public void testTreapDelete() {
        treap.delete(5);
        treap.printTreap(treap);
    }
}
