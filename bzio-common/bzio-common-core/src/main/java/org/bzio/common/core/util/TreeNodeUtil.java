package org.bzio.common.core.util;

import org.bzio.common.core.web.entity.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 树结构构建工具类
 * 例：菜单、单位
 * <p>
 * 思路：
 * 1.查询到根节点
 * 2.根据给出的节点获取到下级节点，添加到节点对象的children属性中
 * 3.利用递归依次获取到重复2操作
 * 4.返回根节点（根节点中children属性包含其子级）
 * 5.递归完成每一级节点根据children关联起来
 *
 * @author: snow
 */
public class TreeNodeUtil {

    /**
     * stream方式构建树结构
     */
    public static List<TreeNode> buildTreeList(List<TreeNode> treeNodeList) {
        // 获取根节点
        // filter过滤StringUtil.isEmpty(m.getPid())的数据，查出根节点
        return treeNodeList.stream().filter(m -> StringUtil.isEmpty(m.getPid())).map((m) -> {
            // 子级集合添加到根节点对象
            m.setChildren(getChildrenList(m, treeNodeList));
            return m;
        }).collect(Collectors.toList());
    }

    /**
     * 获取子节点列表
     *
     * @param node         根节点
     * @param treeNodeList 所有节点信息
     */
    public static List<TreeNode> getChildrenList(TreeNode node, List<TreeNode> treeNodeList) {
        // 同根节点的操作，过滤出和node节点像
        return treeNodeList.stream().filter(item -> Objects.equals(item.getPid(), node.getId())).map(item -> {
            item.setChildren(getChildrenList(item, treeNodeList));
            return item;
        }).collect(Collectors.toList());
    }

    /******************************************************************************************************************/

    /**
     * stream方式构建树结构（第二种）
     * 1.获取根节点
     * 2.以根节点为key、根节点下的子节点作为value封装为一个map
     * 3.遍历获取下级子节点信息，添加到节点对象
     */
    public static List<TreeNode> buildDeptTree2(List<TreeNode> treeNodeList) {
        // 获取根节点
        List<TreeNode> list = treeNodeList.stream().filter(item -> StringUtil.isEmpty(item.getPid())).collect(Collectors.toList());
        // 根据pid进行分组（相当于根节点分组）
        // key：pid（所有根节点id）
        // value：pid相同的一组节点集合
        Map<String, List<TreeNode>> map = treeNodeList.stream().collect(Collectors.groupingBy(TreeNode::getPid));
        recursionFnTree(list, map);
        return list;
    }

    /**
     * 递归遍历节点，获取子列表
     */
    public static void recursionFnTree(List<TreeNode> list, Map<String, List<TreeNode>> map) {
        // 便利所有根节点
        for (TreeNode node : list) {
            // node节点的id作为pid，获取node所有子节点（pid=node.getId()的一组节点集合）
            List<TreeNode> childList = map.get(node.getId());
            // set子节点
            node.setChildren(childList);

            // 子节点不为空继续递归
            if (null != childList && 0 < childList.size()) {
                recursionFnTree(childList, map);
            }
        }
    }

    /******************************************************************************************************************/

    /**
     * 常规递归方法构建树结构
     */
    public static List<TreeNode> buildTreeTree3(List<TreeNode> treeNodeList) {
        // 结果集
        List<TreeNode> returnList = new ArrayList<>();
        // 临时集合，暂存节点id
        List<String> tempList = new ArrayList<>();

        // 遍历节点集合，id添加进入临时集合
        for (TreeNode node : treeNodeList) {
            tempList.add(node.getId());
        }
        for (TreeNode node : treeNodeList) {
            // tempList不包含dept父级id时，为根节点
            if (!tempList.contains(node.getPid())) {
                // 如果是根节点, 遍历该父节点的所有子节点
                // set节点node的子级节点集合
                recursionFn(treeNodeList, node);
                // 将set子级集合children的节点返回
                returnList.add(node);
            }
        }

        if (returnList.isEmpty()) {
            returnList = treeNodeList;
        }
        return returnList;
    }

    /**
     * 递归列表
     * 子级通过setChildren设置
     */
    private static void recursionFn(List<TreeNode> list, TreeNode node) {
        // 得到子节点列表
        List<TreeNode> childList = getChildList(list, node);
        // set节点node的子级集合
        node.setChildren(childList);

        // 递归查询节点t每个子孙级
        for (TreeNode tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private static List<TreeNode> getChildList(List<TreeNode> list, TreeNode node) {
        // 节点node子级集合
        List<TreeNode> tlist = new ArrayList<>();

        // 遍历节点列表
        for (TreeNode n : list) {
            // 当前循环中节点父级id不为空，并且父级id和节点node的id相同则为node的子级
            if (StringUtil.isNotEmpty(n.getPid()) && n.getPid().equals(node.getId())) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private static boolean hasChild(List<TreeNode> list, TreeNode node) {
        return getChildList(list, node).size() > 0;
    }
}
