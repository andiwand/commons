package at.stefl.commons.swing;

import java.awt.Rectangle;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import at.stefl.commons.util.iterator.EnumerationIterable;
import at.stefl.commons.util.primitive.IntegerReference;

public class JTreeUtil {
    
    public static void setVisableRow(JTree tree, int row) {
        Rectangle rect = tree.getRowBounds(row);
        tree.scrollRectToVisible(rect);
    }
    
    public static void collapseAll(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (root == null) return;
        
        collapseAll(tree, new TreePath(root));
    }
    
    @SuppressWarnings("unchecked")
    private static void collapseAll(JTree tree, TreePath path) {
        TreeNode node = (TreeNode) path.getLastPathComponent();
        
        for (TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
            TreePath childPath = path.pathByAddingChild(child);
            collapseAll(tree, childPath);
        }
        
        tree.collapsePath(path);
    }
    
    public static void expandAll(JTree tree) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (root == null) return;
        
        expandAll(tree, new TreePath(root));
    }
    
    @SuppressWarnings("unchecked")
    private static boolean expandAll(JTree tree, TreePath path) {
        TreeNode node = (TreeNode) path.getLastPathComponent();
        boolean expanded = false;
        
        for (TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
            TreePath childPath = path.pathByAddingChild(child);
            expanded |= expandAll(tree, childPath);
        }
        
        if (expanded) return true;
        if (node.isLeaf()) return false;
        
        tree.expandPath(path);
        return true;
    }
    
    public static TreePath findNode(JTree tree, Object matcher) {
        return findNode(tree, matcher, null);
    }
    
    public static TreePath findNode(JTree tree, Object matcher, TreePath offset) {
        TreeNode root = (TreeNode) tree.getModel().getRoot();
        if (root == null) return null;
        
        return findNode(tree, matcher, offset, new IntegerReference(),
                new TreePath(root));
    }
    
    @SuppressWarnings("unchecked")
    private static TreePath findNode(JTree tree, Object matcher,
            TreePath offset, IntegerReference offsetReference, TreePath path) {
        TreeNode node = (TreeNode) path.getLastPathComponent();
        
        if ((offset != null) && (offset.getPathCount() > offsetReference.value)) {
            Object offsetComponent = offset
                    .getPathComponent(offsetReference.value);
            if (node != offsetComponent) return null;
            
            offsetReference.value++;
        } else {
            if (matcher.equals(node)) return path;
        }
        
        for (TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
            TreePath childPath = path.pathByAddingChild(child);
            TreePath result = findNode(tree, matcher, offset, offsetReference,
                    childPath);
            if (result != null) return result;
        }
        
        return null;
    }
    
    private JTreeUtil() {}
    
}