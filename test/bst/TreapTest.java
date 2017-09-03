package bst;

import org.junit.Test;

public class TreapTest {

    private static int[] keys = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    @Test
    //condition: all keys in left subtree must be less or equal to all keys in right subtree
    public void testTreap() {
        Treap treap = new Treap();
        treap = treap.build(keys);
        treap.printTreap(treap);
    }
}
