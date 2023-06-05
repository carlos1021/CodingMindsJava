import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    private BST bst;

    @BeforeEach
    public void setUp() {
        bst = new BST();
    }

    @Test
    public void testInsert() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        assertEquals(5, bst.root.val);
        assertEquals(3, bst.root.left.val);
        assertEquals(7, bst.root.right.val);
    }

    @Test
    public void testFind() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        BST.Node foundNode = bst.find(3);
        assertNotNull(foundNode);
        assertEquals(3, foundNode.val);
    }

    @Test
    public void testRemove() {
        bst.insert(5);
        bst.insert(3);
        bst.insert(7);

        bst.remove(3);
        assertNull(bst.find(3));

        assertEquals(5, bst.root.val);
        assertNull(bst.root.left);
        assertEquals(7, bst.root.right.val);
    }
}
