public class MyTreeSet<E>
{
	private TreeNode root;
	private int size;
	private TreeDisplay display;

	public MyTreeSet()
	{
		root = null;
		size = 0;
		display = new TreeDisplay();

		//wait 1 millisecond when visiting a node
		display.setDelay(1);
	}

	public int size()
	{
		return size;
	}

	public boolean contains(Object obj)
	{
		return containHelper(obj, root);
	}

	public boolean containHelper(Object obj, TreeNode t)
	{
		if (t == null) return false;
		if (t.getValue().equals(obj)) return true;
		boolean result = false;
		result |= containHelper(obj, t.getLeft());
		result |= containHelper(obj, t.getRight());
		return result;
	}

	// if obj is not present in this set, adds obj and
	// returns true; otherwise returns false
	//adds 1 to size
	public boolean add(Integer obj)
	{
		if (contains(obj)) return false;
		TreeNode toAdd = new TreeNode(obj);
		TreeNode current = root;
		if (current == null) {
			root = toAdd;
			size++;
			return true;
		}
		TreeNode previous = new TreeNode(null);
		while (current != null){
			previous = current;
			if ((Integer) obj < (Integer) current.getValue()) current = current.getLeft();
			else current = current.getRight();
		}
		if ((Integer) obj < (Integer) previous.getValue()) previous.setLeft(toAdd);
		else previous.setRight(toAdd);
		size++;
		return true;
	}

	// if obj is present in this set, removes obj and
	// returns true; otherwise returns false}
	public boolean remove(Object obj)
	{
		if (!contains(obj))	return false;
		TreeNode node = find(obj, root);
		if (node.getLeft() == null && node.getRight() == null) node = null;
		else if (node.getLeft() == null) node = node.getRight();
		else if (node.getRight() == null) node = node.getLeft();
		else {
			TreeNode l = leftmostNode(node.getRight());
			remove(l.getValue());
			l.setLeft(node.getLeft());
			l.setRight(node.getRight());
			if (node == root) root = l;
		}
		size--;
		return true;
	}

	public static TreeNode leftmostNode(TreeNode t)
	{
		while (t.getLeft() != null){
			t = t.getLeft();
		}
		return t;
	}

	//precondition: a TreeNode w/ value obj exists in t
	public TreeNode find(Object obj, TreeNode t)
	{
		if (t != null && t.getValue().equals(obj)) return t;
		if (containHelper(obj, t.getRight())) return find(obj, t.getRight());
		else return find(obj, t.getLeft());
	}

	public String toString()
	{
		return toString(root);
	}

	private String toString(TreeNode t)
	{
		if (t == null)
			return " ";
		return toString(t.getLeft()) + t.getValue() + toString(t.getRight());
	}
}