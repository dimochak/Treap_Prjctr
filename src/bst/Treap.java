package bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;


/**
 * A simple data structure that holds 2 values: key and priority in such order that:
 * 1) By keys this structure is a binary tree search
 * 2) By priorities this structure is a heap
 * <p>
 * <p>
 * Operations, that are supported:
 * <ul>
 * <li><i>build tree from array of keys</i></li>
 * <li><i>add key into treap</i></li>
 * <li><i>delete key from treap</i></li>
 * <li><i>print all nodes of treap(width traversal)</i></li>
 * </ul>
 * 2 main operations that are used are merge 2 treaps into 1 and split a correct treap into 2 by key.
 * It is proved that maximal height of this structure is no more than 4*log(n), where n is a number of keys.
 * However, in this implementation
 */
public class Treap {

    /**
     * Additional data structure for holding subtrees; is used when tree is split into 2 parts by some key
     */
    private static class TupleTreap {
        private Treap left, right;

        protected TupleTreap(Treap left, Treap right) {
            this.left = left;
            this.right = right;
        }
    }

    private int key;
    private int priority;

    private Treap left;
    private Treap right;
    private Treap root;

    private static Random rand = new Random(System.currentTimeMillis());

    private static int randInt(int min, int max) {
        return rand.nextInt((max - min) + 1) + min;
    }

    public Treap(int key, int priority) {
        this.key = key;
        this.priority = priority;
    }

    public Treap() {
    }

    private Treap merge(Treap l, Treap r) {
        if (l == null) return r;
        if (r == null) return l;

        if (l.priority > r.priority) {
            l.right = merge(l.right, r);
            return l;
        } else {
            r.left = merge(l, r.left);
            return r;
        }
    }

    private TupleTreap split(int key, Treap treap) {
        if (treap == null) return new TupleTreap(null, null);
        if (treap.key < key) {
            TupleTreap tupleTreap = split(key, treap.right);
            treap.right = null;
            return new TupleTreap(merge(treap, tupleTreap.left), tupleTreap.right);
        } else {
            TupleTreap tupleTreap = split(key, treap.left);
            treap.left = null;
            return new TupleTreap(tupleTreap.left, merge(tupleTreap.right, treap));
        }
    }

    public void add(int key) {
        Treap temp = root;
        while (temp != null && temp.key != key) {
            if (key < temp.key)
                temp = temp.left;
            else
                temp = temp.right;
        }

        if (temp == null) {
            Treap m = new Treap(key, randInt(0, 1000));
            TupleTreap tupleTreap = split(key, root);
            root = merge(tupleTreap.left, merge(m, tupleTreap.right));
        }
    }

    public Treap delete(int key) {
        TupleTreap splittedTreap = split(key, this);
        TupleTreap anotherTreap = split(key + 1, splittedTreap.right);
        merge(splittedTreap.left, anotherTreap.right);
        return anotherTreap.left;
    }

    public Treap build(int[] keys) {
        for (int key : keys) {
            add(key);
        }
        return root;
    }

    public void printTreap(Treap top) {
        Queue<Treap> queue = new LinkedList<>();
        do {
            System.out.println("Node key: " + top.key + " , priority: " + top.priority);
            if (top.left != null) queue.add(top.left);
            if (top.right != null) queue.add(top.right);
            if (!queue.isEmpty()) {
                top = queue.poll();
            } else top = null;
        }
        while (!queue.isEmpty() || top != null);
    }

    public void printTreapWithLevel(Treap top) {
        Queue<Treap> currentLevel = new LinkedList<>();
        Queue<Treap> nextLevel = new LinkedList<>();
        currentLevel.add(top);
        int level = 1;
        while (!currentLevel.isEmpty()) {
            top = currentLevel.poll();
            System.out.println("Node key: " + top.key + ", priority: " + top.priority + " level: " + level);
            if (top.left != null) nextLevel.add(top.left);
            if (top.right != null) nextLevel.add(top.right);
            if (currentLevel.isEmpty()) {
                currentLevel = swapQueues(nextLevel, nextLevel = currentLevel);
                ++level;
            }
        }
    }

    private static Queue<Treap> swapQueues(Queue<Treap> a, Queue<Treap> b) {
        return a;
    }
}
