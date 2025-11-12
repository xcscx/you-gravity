package com.itegg.yougravitybackend.common;

import cn.hutool.core.lang.Assert;
import com.itegg.yougravitybackend.config.TreeConfig;

import java.io.Serial;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 有序可排列的树结构
 * @param <T> 节点类型
 * @author ITEgg
 */
public class Tree<T> extends LinkedHashMap<String, Object> implements Node<T, Tree<T>> {

    @Serial
    private static final long serialVersionUID = 1L;

    private final TreeConfig treeConfig;

    public Tree() {
        this(null);
    }

    /**
     * 构造函数
     */
    public Tree(TreeConfig treeConfig) {
        super();
        if(null != treeConfig) {
            this.treeConfig = treeConfig;
        } else {
            this.treeConfig = TreeConfig.DEFAULT_CONFIG;
        }
        this.put(treeConfig.getChildrenKey(), new ArrayList<>());
    }

    @Override
    public T getId() {
        return (T) this.get(treeConfig.getIdKey());
    }

    public Tree<T> setId(T id) {
        this.put(treeConfig.getIdKey(), id);
        return this;
    }

    @Override
    public T getParentId() {
        return (T) this.get(treeConfig.getParentIdKey());
    }

    public Tree<T> setParentId(T parentId) {
        this.put(treeConfig.getParentIdKey(), parentId);
        return this;
    }

    @Override
    public CharSequence getName() {
        return (CharSequence) this.get(treeConfig.getNameKey());
    }

    public Tree<T> setName(CharSequence name) {
        this.put(treeConfig.getNameKey(), name);
        return this;
    }

    @Override
    public Comparable<?> getWeight() {
        return (Comparable<?>) this.get(treeConfig.getWeightKey());
    }

    public Tree<T> setWeight(Comparable<?> weight) {
        this.put(treeConfig.getWeightKey(), weight);
        return this;
    }

    public List<Tree<T>> getChildren() {
        return (List<Tree<T>>) this.get(treeConfig.getChildrenKey());
    }

    public void setChildren(List<Tree<T>> children) {
        this.put(treeConfig.getChildrenKey(), children);
    }

    /**
     * 扩展属性
     * @Param key 键
     * @Param value 值
     */
    public void putExtra(String key, Object value) {
        Assert.notNull(key, "key must not be empty!");
        this.put(key, value);
    }

}
