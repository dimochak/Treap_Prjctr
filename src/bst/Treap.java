package bst;

import java.util.Random;

public class Treap {
    private int key;
    private int priority;

    private Treap left;
    private Treap right;
    private Treap parent;

    private static Random rand = new Random(System.currentTimeMillis());

    private Treap(int key, int priority, Treap left, Treap right, Treap parent) {
        this.key = key;
        this.priority = priority;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    private static Treap merge(Treap l, Treap r) {
        if (l == null) return r;
        if (r == null) return l;

        if (l.priority > r.priority) {
            Treap newR = merge(l.right, r);
            return new Treap(l.key, l.priority, l.left, newR, null);
        } else {
            Treap newL = merge(l, r.left);
            return new Treap(r.key, r.priority, newL, r.right, null);
        }
    }

    private void split(int key, Treap leftTreap, Treap rightTreap) {
        Treap newTree = null;
        if (this.key <= key) {
            if (this.right == null)
                rightTreap = null;
            else
                this.right.split(key, newTree, rightTreap);
            leftTreap = new Treap(this.key, priority, this.left, newTree, null);
        } else {
            if (this.left == null)
                rightTreap = null;
            else
                this.left.split(key, leftTreap, newTree);
            leftTreap = new Treap(this.key, priority, newTree, this.right, null);
        }
    }

    public Treap add(int key) {
        Treap left = null, right = null;
        split(key, left, right);
        Treap m = new Treap(key, rand.nextInt(), null, null, null);
        return merge(merge(this.left, m), this.right);
    }

    public Treap delete(int key) {
        Treap l = null, m = null, r = null;
        split(priority - 1, l, r);
        r.split(key, m, r);
        return merge(l, r);
    }

    public static Treap build(int[] keys, int[] priorities) {
        assert keys.length == priorities.length;
        Treap last = new Treap(keys[0], priorities[0], null, null, null);

        for (int i = 0; i < keys.length; i++) {
            if (last.priority > priorities[i]) {
                last.right = new Treap(keys[i], priorities[i], null, null, last);
                last = last.right;
            } else {
                Treap current = last;
                while (current.parent != null && current.priority <= priorities[i]) {
                    current = current.parent;
                }
                if(current.priority <= priorities[i])
                    last = new Treap(keys[i], priorities[i], current, null, last);
                else {
                    last = new Treap(keys[i], priorities[i], current.right, null, current);
                }
            }
        }
        while(last.parent != null) {
            last = last.parent;
        }
        return last;
    }
}
