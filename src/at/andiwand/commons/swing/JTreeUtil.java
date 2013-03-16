package at.andiwand.commons.swing;

import java.awt.Rectangle;

import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import at.andiwand.commons.util.iterator.EnumerationIterable;
import at.andiwand.commons.util.object.ObjectMatcher;


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
	
	public static TreePath findNode(JTree tree, ObjectMatcher<TreeNode> matcher) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		if (root == null) return null;
		
		return findNode(tree, matcher, null);
	}
	
	public static TreePath findNode(JTree tree,
			ObjectMatcher<TreeNode> matcher, TreePath offset) {
		TreeNode root = (TreeNode) tree.getModel().getRoot();
		if (root == null) return null;
		
		return findNode(tree, matcher, offset, new int[1], new TreePath(root));
	}
	
	// TODO: int array?
	@SuppressWarnings("unchecked")
	private static TreePath findNode(JTree tree,
			ObjectMatcher<TreeNode> matcher, TreePath offset,
			int[] offsetMatch, TreePath path) {
		TreeNode node = (TreeNode) path.getLastPathComponent();
		
		if ((offset != null) && (offset.getPathCount() > offsetMatch[0])) {
			Object offsetComponent = offset.getPathComponent(offsetMatch[0]);
			if (node != offsetComponent) return null;
			
			offsetMatch[0]++;
		} else {
			if (matcher.matches(node)) return path;
		}
		
		for (TreeNode child : new EnumerationIterable<TreeNode>(node.children())) {
			TreePath childPath = path.pathByAddingChild(child);
			TreePath result = findNode(tree, matcher, offset, offsetMatch,
					childPath);
			if (result != null) return result;
		}
		
		return null;
	}
	
	private JTreeUtil() {}
	
}