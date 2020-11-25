package cn.com.onlinetool.tree;



import javax.security.auth.Destroyable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @author choice
 * @description:
 * @date 2018/8/15 下午3:47
 *
 */
public class DisorderlyTree<T> implements Destroyable {
    /**
     * 节点的数据。
     * @see #getAllDataOfTreeByAfterOrder()
     * @see #getAllDataOfTreeByPreOrder()
     * @see #visitAllByAfterOrder(List)
     * @see #visitAllByPreOrder(List)
     */
    private T data;

    /**
     * 父节点。
     */
    private DisorderlyTree<T> parent;

    /**
     * 树的子节点集。
     * @see #addNode(DisorderlyTree)
     * @see #removeNode(DisorderlyTree)
     * @see #getNodeByIndex(int)
     */
    private List<DisorderlyTree<T>> childs;



    /**
     * 构造一颗空树。
     */
    public DisorderlyTree() {
        data = null;
        childs = new ArrayList<DisorderlyTree<T>>();
        parent = null;
    }

    /**
     * 构造一个叶子节点。
     * @param data 节点数据
     */
    public DisorderlyTree(T data) {
        this.data = data;
        childs = new ArrayList<DisorderlyTree<T>>();
        this.parent = null;
    }

    /**
     * 获取子树。
     * @return
     */
    public List<DisorderlyTree<T>> getChilds(){
        return this.childs;
    }

    /**
     * 获取树的数据。
     * @return data
     */
    public T getData() {
        return data;
    }

    /**
     * 设定树的数据，如果是空树将会成为叶子节点。
     * @param data 树的数据
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * 获取父节点。
     * @return 父节点。
     */
    public DisorderlyTree<T> getParent() {
        return parent;
    }

    /**
     * 设置父节点。
     * @param parent
     */
    public void setParent(DisorderlyTree<T> parent) {
        this.parent = parent;
    }

    /**
     * 为一些树的根节点添加父节点，这个节点将成为新树的根节点。
     * @param data 节点数据
     * @param childs 子节点
     */
    public DisorderlyTree(T data, List<DisorderlyTree<T>> childs) {
        this.data = data;
        this.childs = childs;
        for (DisorderlyTree<T> tree : childs) {
            tree.setParent(this);
        }
    }

    /**
     * 获取当前树节点的度。
     * @return 节点的度
     * @see #childs
     */
    public int getNodeDegree() {
        return childs.size();
    }

    /**
     * 判断是否是空树。
     * @return 是否空树。
     * @see #size()
     */
    public boolean isEmptyTree() {
        return size() == 0;
    }

    /**
     * 判断是否是叶子节点。
     * @return 是否是叶子
     * @see #getNodeDegree()
     */
    public boolean isLeaf() {
        return getNodeDegree() == 0 && data != null;
    }

    /**
     * 获取树的深度。<br>
     * @return 树的深度
     * @see #childs
     */
    public int getDepth() {
        if(isEmptyTree()) {
            return 0;
        }else if(isLeaf()) {
            return 1;
        }else {
            int degree = getNodeDegree();
            int depth[] = new int[degree];
            int i = 0;
            for(DisorderlyTree<T> tree : childs) {
                depth[i] = tree.getDepth();
                i++;
            }
            Arrays.sort(depth);
            return depth[degree - 1] + 1;
        }
    }

    /**
     * 获取节点总数。
     * @return 节点总数
     */
    public int size() {
        int size = 0;
        if(isLeaf()) {
            size = 1;
        }else {
            for (DisorderlyTree<T> tree : childs) {
                size += tree.size();
            }
            if(data != null)
                size++;
        }
        return size;
    }

    /**
     * 叶子节点数
     * @return
     */
    public int getLeafCount() {
        int count = 0;
        if(!isLeaf()) {
            for (DisorderlyTree<T> tree : childs) {
                count += tree.getLeafCount();
            }
        }else {
            count = 1;
        }
        return count;
    }

    /**
     * 获取树的度。
     * @return 树的度
     * @see #childs
     */
    public int getDegree() {
        if(isEmptyTree()) {
            return 0;
        }else if(isLeaf()) {
            return 1;
        }else {
            int countOfNodes = childs.size();
            int degrees[] = new int[countOfNodes];
            int i = 0;
            for (DisorderlyTree<T> subTree : childs) {
                degrees[i] = subTree.getDegree();
                i++;
            }
            Arrays.sort(degrees);
            if(countOfNodes > degrees[countOfNodes - 1]) {
                return countOfNodes;
            }else {
                return degrees[countOfNodes - 1];
            }
        }
    }

    /**
     * 获取某棵子树。<br>
     * 如果是空树或叶子节点则抛出<code>{@link NullPointerException}</code>。<br>
     * 如果输入值大于或等于节点数则抛出<code>{@link ArrayIndexOutOfBoundsException}</code>。
     * @param index 要查询的子树的索引
     * @return 子树
     * @see #childs
     */
    public DisorderlyTree<T> getChilds(int index){
        if(isEmptyTree()) {
            throw new NullPointerException("无法在空树中获取子树。");
        }else if(isLeaf()) {
            throw new NullPointerException("叶子节点中无子树。");
        }else if(index >= getDegree() - 1 || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }else {
            return childs.get(index);
        }
    }

    /**
     * 获取第一个孩子节点。<br>
     * 若是空节点或叶子节点则抛出<code>{@link NullPointerException}</code>。
     * @return 大哥节点
     * @see #childs
     */
    public DisorderlyTree<T> getEldestSubNode(){
        if(isEmptyTree()) {
            throw new NullPointerException("空树中无节点。");
        }
        if(isLeaf()) {
            throw new NullPointerException("叶子节点中无子节点。");
        }
        return childs.get(0);
    }

    /**
     * 获取可能是自己的最终节点。<br>
     * 若是空节点则抛出<code>{@link NullPointerException}</code>。
     * @return 最终节点
     */
    public DisorderlyTree<T> getYoungestNode(){
        if(isEmptyTree()){
            throw new NullPointerException("空树中无节点。");
        }else if(isLeaf()) {
            return this;
        }else {
            List<DisorderlyTree<T>> list = getAllTrees();
            DisorderlyTree<T> last = null;
            for (DisorderlyTree<T> tree : list) {
                last = tree;
            }
            return last;
        }
    }

    /**
     * 检测树中是否有该节点。
     * @param subTree 受检节点
     * @return 是否有该节点
     */
    public boolean isSubTreeExists(DisorderlyTree<T> subTree) {
        boolean flag = false;
        if(subTree == this) {
            return true;
        }else {
            for (DisorderlyTree<T> tree : childs) {
                if(tree == subTree) {
                    return true;
                }else if(!tree.isLeaf()) {
                    if(!flag) {
                        flag = tree.isSubTreeExists(subTree);
                    }else {
                        return flag;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 查询整棵树中是否有该节点。
     * @param tree 受检节点
     * @return FLAG
     * @see #isSubTreeExists(DisorderlyTree)
     */
    public boolean isNodeExistsInGlobalTree(DisorderlyTree<T> tree) {
        if(parent == null) {
            return isSubTreeExists(tree);
        }else if(this == tree) {
            return true;
        }else {
            return parent.isNodeExistsInGlobalTree(tree);
        }
    }

    /**
     * 通过索引来获取某个节点。<br>
     * 空树会抛出<code>{@link NullPointerException}</code>。<br>
     * 越界抛出<code>{@link ArrayIndexOutOfBoundsException}</code>。
     * @param index 查询索引
     * @return 节点
     * @throws Exception
     * @see #isEmptyTree()
     * @see #size()
     */
    public DisorderlyTree<T> getNodeByIndex(int index) throws Exception{
        if(isEmptyTree()) {
            throw new NullPointerException("不能从空节点中获取指定索引的节点。");
        }else if(index >= size() || index < 0) {
            throw new ArrayIndexOutOfBoundsException();
        }else if(index == 0) {
            return this;
        }else {
            int index2 = 0;
            List<DisorderlyTree<T>> trees = getAllTrees();
            for (DisorderlyTree<T> tree : trees) {
                if(index2 == index) {
                    return tree;
                }
                index2++;
            }
        }
        throw new Exception("其他错误！");
    }

    /**
     * 为这棵树添加节点/子树。<br>
     * 树的深度会增加。
     * 如果这棵树是空树或子节点是空树将抛出<code>{@link NullPointerException}</code>。<br>
     * 如果将存在的节点插入到树中将抛出<code>{@link IllegalArgumentException}</code>。
     * @param subTree 子节点/子树
     * @return flag
     * @see #childs
     * @see #isNodeExistsInGlobalTree(DisorderlyTree)
     */
    public int addNode(DisorderlyTree<T> subTree) {
        if(isEmptyTree()) {
            throw new NullPointerException("不能为空树增加子节点！");
        }else if(subTree.isEmptyTree()){
            throw new NullPointerException("不能增加空树为子节点！");
        }else if(isNodeExistsInGlobalTree(subTree)){
            throw new IllegalArgumentException("不能增加已存在的节点！");
        }else {
            childs.add(subTree);
            subTree.setParent(this);
        }
        return 1;
    }

    /**
     * 删除树中的指定节点。<br>
     * 如果树中无此节点，则抛出<code>{@link NullPointerException}</code>。
     * @param subTree 要删除的节点
     * @return FLAG
     * @see #isSubTreeExists(DisorderlyTree)
     */
    public int removeNode(DisorderlyTree<T> subTree) {
        if(isSubTreeExists(subTree)) {
            subTree.getChilds().clear();
            subTree.clear();
            System.out.println(subTree.size());
            subTree.destroy();
            childs.remove(subTree);
        }else {
            throw new NullPointerException("该树中无此节点。");
        }
        return 1;
    }

    /**
     * 删除指定索引的节点。
     * @param index 索引
     * @return FLAG
     * @throws Exception
     * @see #getNodeByIndex(int)
     * @see #removeNode(DisorderlyTree)
     */
    public int removeNode(int index) throws Exception {
        removeNode(getNodeByIndex(index));
        return 1;
    }

    /**
     * 先根遍历添加列表元素。
     * @param list 列表
     * @see #getAllDataOfTreeByPreOrder()
     */
    private void visitAllByPreOrder(List<T> list) {
        list.add(data);
        if(!isLeaf()) {
            for (DisorderlyTree<T> tree : childs) {
                tree.visitAllByPreOrder(list);
            }
        }
    }

    /**
     * 先根遍历，将数据添加在表中。
     * @return 带有数据的表
     * @see #visitAllByPreOrder(List)
     */
    public List<T> getAllDataOfTreeByPreOrder(){
        List<T> datas = new ArrayList<T>();
        visitAllByPreOrder(datas);
        return datas;
    }

    /**
     * 遍历所有的树。
     * @param list 列表
     * @see #getAllTrees()
     */
    private void visitAllTrees(List<DisorderlyTree<T>> list) {
        list.add(this);
        if(!isLeaf()) {
            for(DisorderlyTree<T> tree : childs) {
                tree.visitAllTrees(list);
            }
        }
    }

    /**
     * 遍历所有的树，将树打包成为一个列表。
     * @return 带有树的表
     * @see #visitAllTrees(List)
     */
    public List<DisorderlyTree<T>> getAllTrees(){
        List<DisorderlyTree<T>> trees = new ArrayList<DisorderlyTree<T>>();
        visitAllTrees(trees);
        return trees;
    }

    /**
     * 后根遍历添加列表元素。
     * @param list 列表
     * @see #getAllDataOfTreeByAfterOrder()
     */
    private void visitAllByAfterOrder(List<T> list) {
        if(!isLeaf()) {
            for (DisorderlyTree<T> tree : childs) {
                tree.visitAllByPreOrder(list);
            }
        }
    }

    /**
     * 后根遍历，将数据添加在表中。
     * @return 带有数据的表
     * @see #visitAllByAfterOrder(List)
     */
    public List<T> getAllDataOfTreeByAfterOrder(){
        List<T> datas = new ArrayList<T>();
        visitAllByAfterOrder(datas);
        datas.add(data);
        return datas;
    }

    /**
     * 置空树。
     */
    public void clear() {
        data = null;
        if(!isLeaf()) {
            for (DisorderlyTree<T> tree : childs) {
                tree.clear();
                tree.destroy();
                childs.remove(tree);
            }
        }
        childs.clear();
    }

    /**
     * 销毁一颗空树。
     * @see {@link #isEmptyTree()}
     */
    public void destroy() {
        if(isEmptyTree()) {
            data = null;
            childs = null;
            try {
                super.finalize();
                System.out.println("砍树。");
            }catch(Throwable e) {
                System.out.println("对象无需销毁。");
            }
        }else {
            try {
                throw new Exception("无法销毁非空树！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        String info = "";
        if(isEmptyTree()) {
            info = "这是一颗空树。";
        }else if(isLeaf()) {
            info = "这是一个叶子节点，它的值为" + data;
        }else {
            info = "该树的数据值为" + data + "。\n结点度为：" + getNodeDegree()
                + "。\n度为：" + getDegree() + "。\n深度为：" + getDepth() + "。\n拥有"
                + size() + "个结点。\n其中有" + getLeafCount() + "个叶子节点。";
        }
        return info;
    }
}
