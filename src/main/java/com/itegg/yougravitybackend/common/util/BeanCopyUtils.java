package com.itegg.yougravitybackend.common.util;


import cn.hutool.core.util.StrUtil;

import java.lang.reflect.Field;

/**
 * 全自定义拷贝类
 * @author ITegg
 */
public class BeanCopyUtils {

    /**
     * 将 source 中非空、非空白字符串的属性拷贝到 target
     * 注意：此方法仅处理 String 类型的字段忽略空白，其他类型仅忽略 null
     *
     * @param source 源对象 (如 UserUpdateRequest)
     * @param target 目标对象 (如 User Entity)
     */
    public static void copyNonNullProperties(Object source, Object target) {
        if (source == null || target == null) {
            return;
        }

        // 获取源对象的所有字段
        Field[] fields = source.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                // 设置可访问
                field.setAccessible(true);

                // 获取源字段的值
                Object value = field.get(source);

                // 判断是否应该跳过拷贝
                if (value == null) {
                    continue;
                }

                // 如果是 String 类型，且为空白字符，也跳过
                if (value instanceof String && StrUtil.isBlank((String) value)) {
                    continue;
                }

                // 在 target 中寻找同名字段
                Field targetField = getField(target.getClass(), field.getName());
                if (targetField != null) {
                    targetField.setAccessible(true);
                    // 将值设置到 target 对象中
                    targetField.set(target, value);
                }
            } catch (IllegalAccessException e) {
                // 记录日志或抛出异常，视项目规范而定
                e.printStackTrace();
            }
        }
    }

    /**
     * 递归获取类及其父类的字段
     */
    private static Field getField(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            // 如果当前类没有，尝试从父类获取
            if (clazz.getSuperclass() != null) {
                return getField(clazz.getSuperclass(), fieldName);
            }
            return null;
        }
    }


}
