public class FileTester {
    public static void main(String[] args) {
        int randomDepth = 4; //(int)(Math.random()*5) + 3;
        TreeNode t = TreeUtil.createRandom (randomDepth);

        TreeUtil util = new TreeUtil();
        util.saveTree("lorax", t);
        TreeNode result = util.loadTree("lorax");
        System.out.println(result.getValue());
    }
}
