package bst;

import org.junit.Before;
import org.junit.Test;
import utility.TestGenerator;

public class TreapTest {

    private static int[] keys = TestGenerator.generatePrimitivesSequence(100000);
    private Treap treap = new Treap();

    @Before
    public void initTree() {
        treap = treap.build(keys);
    }

    @Test
    //condition: all keys in left subtree must be less or equal to all keys in right subtree
    public void testTreap() {
        treap.printTreapWithLevel(treap);
    }

    @Test
    public void testTreapDelete() {
        treap.delete(5);
        treap.printTreapWithLevel(treap);
    }
}
