package com.itegg.yougravitybackend.common;

import java.io.Serializable;

/**
 * 树节点字段
 * @param <T> id类型
 * @param <C> 节点类型
 * @author ITEgg
 */
public interface Node<T, C> extends Comparable<Node<T, C>>, Serializable {

    /**
     * 获取 ID
     * @return ID
     */
    T getId();

    /**
     * 获取父节点 ID
     * @return ParentID
     */
    T getParentId();

    /**
     * 获取节点标签名称
     * @return 节点标签名称
     */
    CharSequence getName();

    /**
     * 获取权重
     * @return 权重
     */
    Comparable<?> getWeight();

    /**
     * 获取子节点集合
     * @return 子节点集合
     */
    default Boolean getHasChildren() {
        return false;
    }

    /**
     * 对比算法
     * @param node 需要比较的节点对象
     * @return -1小于 0等于 1大于
     */
    @Override
    default int compareTo(Node node) {
        final Comparable weight = this.getWeight();
        if(null != weight) {
            final Comparable weightOther = node.getWeight();
            return weight.compareTo(weightOther);
        } else {
            return 0;
        }
    }

}
