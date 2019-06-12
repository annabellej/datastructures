import java.util.*;
/**
 * TreeUtil contains useful methods for manipulating binary trees.
 *
 * @author Annabelle Ju
 * @version 4/30/19
 *
 */
public class TreeUtil
{
	//used to prompt for command line input
	private static Scanner in = new Scanner(System.in);
	private static final boolean debug = false;


	/**
	 * leftmost returns the value in the leftmost node of the tree.  It is 
	 * implemented iteratively
	 * precondition:  t is non-empty
	 * @param t is the tree whose leftmost node is to be found
	 * @return the VALUE in the leftmost node of t.
	 */
	public static Object leftmost(TreeNode t)
	{
		while (t.getLeft() != null){
			t = t.getLeft();
		}
		return t.getValue();
	}

	/**
	 * rightmost returns the value in the rightmost node of the tree.  It is 
	 * implemented recursively
	 * precondition:  t is non-empty
	 * @param t is the tree whose rightmost node is to be found
	 * @return the VALUE in the rightmost node of t.
	 */
	public static Object rightmost(TreeNode t)
	{
		if(t.getRight() == null) return t.getValue();
		return rightmost(t.getRight());
	}
	/**
	 * maxDepth calculates the maximum depth of a binary tree.  An empty tree has 
	 * depth of 0 and a tree with one node has a depth of 1
	 * @param t a pointer to the root of a tree whose depth is to be calculated
	 * @return the depth of the tree rooted at t
	 */
	public static int maxDepth(TreeNode t)
	{
		if (t == null)	return 0;
		return 1 + Math.max(maxDepth(t.getRight()), maxDepth(t.getLeft()));
	}

	/**
	 * create a random tree of the specified depth.  No attempt to balance the tree
	 * is provided.
	 * @param depth of the tree
	 * @return TreeNode object that points to the generated tree
	 */
	public static TreeNode createRandom(int depth)
	{
		if (Math.random() * Math.pow(2, depth) < 1)
			return null;
		return new TreeNode(((int)(Math.random() * 10)),
			createRandom(depth - 1),
			createRandom(depth - 1));
	}
	/**
	 * counts the number of nodes (including leaves) that are in the tree rooted at t
	 * @param t the root of the tree
	 * @return the number of nodes in the tree rooted at t
	 */
	public static int countNodes(TreeNode t)
	{
		if (t == null) return 0;
		return 1 + countNodes(t.getRight()) + countNodes(t.getLeft());
	}
	/**
	 * counts the number of leaves in the tree rooted at t
	 * @param t the root of the binary tree
	 * @return the number of leaves in the tree
	 */
	public static int countLeaves(TreeNode t)
	{
		if (t != null) {
			if (t.getLeft()==null && t.getRight()==null) return 1;
			return countLeaves(t.getLeft()) + countLeaves(t.getRight());
		}
		return 0;
	}
	/**
	 * perform a pre-order traversal of the binary tree rooted at t, lighting
	 * up the nodes on the display as the traversal takes place
	 * @param t the root of the tree to traverse
	 * @param display the display that will show the traversal
	 * postcondition: each node in t has been lit up on display
	 */
	public static void preOrder(TreeNode t, TreeDisplay display)
	{
		if (t != null){
			display.visit(t);
			preOrder(t.getLeft(), display);
			preOrder(t.getRight(), display);
		}
	}
    /**
     * perform an in-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
	public static void inOrder(TreeNode t, TreeDisplay display)
	{
		if (t != null) {
			if (t.getLeft() == null) {
				display.visit(t);
				if (t.getRight() != null){
					inOrder(t.getRight(), display);
				}
			}
			else {
				inOrder(t.getLeft(), display);
				display.visit(t);
				inOrder(t.getRight(), display);
			}
		}
	}
    /**
     * perform a post-order traversal of the binary tree rooted at t, lighting
     * up the nodes on the display as the traversal takes place
     * @param t the root of the tree to traverse
     * @param display the display that will show the traversal
     * postcondition: each node in t has been lit up on display
     */
	public static void postOrder(TreeNode t, TreeDisplay display)
	{
		if (t != null){
			if (t.getLeft() == null) {
				display.visit(t);
				if (t.getRight() != null){
					postOrder(t.getRight(), display);
				}
			}
			else {
				postOrder(t.getLeft(), display);
				postOrder(t.getRight(), display);
				display.visit(t);
			}
		}
	}
	/**
	 * fill a list with the values of a binary tree rooted at t using a 
	 * pre-order traversal with '$' values added to the list whenever
	 * a null pointer is encountered
	 * @param t the root of the tree
	 * @param list the returned list of values in the tree
	 */
	public static void fillList(TreeNode t, List<String> list)
	{
		if (t != null){
			list.add(t.getValue().toString());
			fillList(t.getLeft(), list);
			fillList(t.getRight(), list);
		}
		else {
			list.add("$");
		}
	}
	/**
	 * saveTree uses the FileUtil utility class to save the tree rooted at t
	 * as a file with the given file name
	 * @param fileName is the name of the file to create which will hold the data
	 *        values in the tree
	 * @param t is the root of the tree to save
	 */
	public static void saveTree(String fileName, TreeNode t)
	{
		FileUtil reader = new FileUtil();
		List<String> list = new ArrayList<>();
		fillList(t, list);
		reader.saveFile(fileName, list.iterator());

	}
	/**
	 * buildTree takes in an iterator which will iterate through a valid description of
	 * a binary tree with String values.  Null nodes are indicated by "$" markers
	 * @param it the iterator which will iterate over the tree description
	 * @return a pointer to the root of the tree built by the iteration
	 */
	public static TreeNode buildTree(Iterator<String> it)
	{
		if (!it.hasNext()) return null;
		String s = it.next();
		if (s.equals("$")) return null;
		TreeNode current = new TreeNode(s);
		current.setLeft(buildTree(it));
		current.setRight(buildTree(it));
		return current;

	}
	/**
	 * read a file description of a tree and then build the tree
	 * @param fileName is a valid file name for a file that describes a binary tree
	 * @return a pointer to the root of the tree
	 */
	public static TreeNode loadTree(String fileName)
	{
		FileUtil reader = new FileUtil();
		Iterator<String> list = reader.loadFile(fileName);
		return buildTree(list);
	}
	/**
	 * utility method that waits for a user to type text into Std Input and then press enter
	 * @return the string entered by the user
	 */
	private static String getUserInput()
	{
		return in.nextLine();
	}
	/**
	 * plays a single round of 20 questions
	 * postcondition:  plays a round of twenty questions, asking the user questions as it
     *                 walks down the given knowledge tree, lighting up the display as it goes;
     *                 modifies the tree to include information learned.
	 * @param t a pointer to the root of the game tree
	 * @param display which will show the progress of the game
	 */
	private static void twentyQuestionsRound(TreeNode t, TreeDisplay display)
	{	
		throw new RuntimeException("Write ME!");
	}
	/** 
	 * plays a game of 20 questions
	 * Begins by reading in a starting file and then plays multiple rounds
	 * until the user enters "quit".  Then the final tree is saved
	 */
	public static void twentyQuestions()
	{
		throw new RuntimeException("Write ME!");
	}
	/**
	 * copy a binary tree
	 * @param t the root of the tree to copy
	 * @return a new tree, which is a complete copy
	 *         of t with all new TreeNode objects
	 *         pointing to the same values as t (in the same order, shape, etc)
	 */
	public static TreeNode copy(TreeNode t)
	{
		if (t == null)	return null;
		TreeNode result = new TreeNode(t.getValue());
		result.setLeft(copy(t.getLeft()));
		result.setRight(copy(t.getRight()));
		return result;
	}
	
	/**
	 * tests to see if two trees have the same shape, but not necessarily the
	 * same values.  Two trees have the same shape if they have TreeNode objects
	 * in the same locations relative to the root
	 * @param t1 pointer to the root of the first tree
	 * @param t2 pointer to the root of the second tree
	 * @return true if t1 and t2 describe trees having the same shape, false otherwise
	 */
	public static boolean sameShape(TreeNode t1, TreeNode t2)
	{
		boolean result;
		if (t1 == null && t2 != null || t2 == null && t1 != null) result = false;
		else if (t1 == null && t2 == null)	result = true;
		else {
			result = sameShape(t1.getLeft(), t2.getLeft());
			result = sameShape(t1.getRight(), t2.getRight());
		}
		return result;
	}
	/**
	 * Generate a tree for decoding Morse code
	 * @param display the display that will show the decoding tree
	 * @return the decoding tree
	 */
	public static TreeNode createDecodingTree(TreeDisplay display)
	{
		TreeNode tree = new TreeNode("Morse Tree");
		display.displayTree(tree);
		insertMorse(tree, "a", ".-", display);
		insertMorse(tree, "b", "-...", display);
		insertMorse(tree, "c", "-.-.", display);
		insertMorse(tree, "d", "-..", display);
		insertMorse(tree, "e", ".", display);
		insertMorse(tree, "f", "..-.", display);
		insertMorse(tree, "g", "--.", display);
		insertMorse(tree, "h", "....", display);
		insertMorse(tree, "i", "..", display);
		insertMorse(tree, "j", ".---", display);
		insertMorse(tree, "k", "-.-", display);
		insertMorse(tree, "l", ".-..", display);
		insertMorse(tree, "m", "--", display);
		insertMorse(tree, "n", "-.", display);
		insertMorse(tree, "o", "---", display);
		insertMorse(tree, "p", ".--.", display);
		insertMorse(tree, "q", "--.-", display);
		insertMorse(tree, "r", ".-.", display);
		insertMorse(tree, "s", "...", display);
		insertMorse(tree, "t", "-", display);
		insertMorse(tree, "u", "..-", display);
		insertMorse(tree, "v", "...-", display);
		insertMorse(tree, "w", ".--", display);
		insertMorse(tree, "x", "-..-", display);
		insertMorse(tree, "y", "-.--", display);
		insertMorse(tree, "z", "--..", display);
		return tree;
	}
	/**
	 * helper method for building a Morse code decoding tree.
	 * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
	 * @param decodingTree is the partial decoding tree
	 * @param letter is the letter to add
	 * @param code is the Morse code for letter
	 * @param display is the display that will show progress as the method walks 
	 *        down the tree
	 */
	private static void insertMorse(TreeNode decodingTree, String letter,
									String code, TreeDisplay display)
	{
		TreeNode current = decodingTree;
		display.visit(current);
		while (code.length()>0){
			String side = code.substring(0,1);
			if (side.equals(".")) {
				if (current.getLeft()==null)	current.setLeft(new TreeNode("null"));
				current = current.getLeft();
				display.visit(current);
			}
			else {
				if (current.getRight()==null)	current.setRight(new TreeNode("null"));
				current = current.getRight();
				display.visit(current);
			}
			code = code.substring(1);
		}
		current.setValue(letter);
	}
	/**
	 * decodes Morse code by walking the decoding tree according to the input code
	 * @param decodingTree is the Morse code decoding tree
	 * @param cipherText is Morse code consisting of dots, dashes, and spaces
	 * @param display is the display object that will show the decoding progress
	 * @return the string represented by cipherText
	 */
	public static String decodeMorse(TreeNode decodingTree, String cipherText, TreeDisplay display)
	{
		String result = "";
		TreeNode current = decodingTree;
		while (!cipherText.equals("")){
			String piece = cipherText.substring(0,1);
			if (piece.equals(".")) {
				current = current.getLeft();
			}
			else if (piece.equals("-")){
				current = current.getRight();
			}
			else {
				result += current.getValue();
				current = decodingTree;
			}
			cipherText = cipherText.substring(1);
		}
		result += current.getValue();
		return result;
	}
	/**
	* optional work
	*/
	public static int eval(TreeNode expTree)
	{
		throw new RuntimeException("Write ME!");
	}
	/**
	* optional work
	*/
	public static TreeNode createExpressionTree(String exp)
	{
		throw new RuntimeException("Write ME!");
	}

	/**
	 * debug printout
	 * postcondition: out is printed to System.out
	 * @param out the string to send to System.out
	 */
	
	private static void debugPrint(String out)
	{
	    if(debug) System.out.println("debug: " + out);
	}
}
	