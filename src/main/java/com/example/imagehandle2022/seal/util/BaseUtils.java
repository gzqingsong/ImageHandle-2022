package com.example.imagehandle2022.seal.util;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;


/**
 * @Author zhaotong
 * @Description TODO
 * @Date 2023/7/10
 * @Version 1.0
 */
public class BaseUtils {

    /**
     * 对两个对象的具有相同属性名进行赋值并返回目标对象
     * @param source
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (BeansException e) {
            e.printStackTrace();
        }finally {
            if (t!=null){
                return t;
            }else {
                return null;
            }
        }
    }

    /**
     * 将source中与target中类型和名称相同的属性值赋值给对应的entity的属性，并返回target
     * @param source          源对象
     * @param target          目标对象
     * @param ignoreNullField 是否忽略空值
     */
    public static void copyProperties(Object source, Object target, Boolean ignoreNullField) {
        List<Map<String, Object>> sourceFields = getFieldInfo(source);
        if (isEmpty(sourceFields)) {
            return;
        }
        for (Map sourceFieldMap : sourceFields) {
            try {
                Field field = target.getClass().getDeclaredField(sourceFieldMap.get("name").toString());
                // 源对象属性值为空 或属性类型不一致 则返回继续下一条
                if (ignoreNullField && isEmpty(sourceFieldMap.get("value")) ||
                        !sourceFieldMap.get("type").equals(field.getType().toString())) {
                    continue;
                }
                field.setAccessible(true);
                field.set(target, sourceFieldMap.get("value"));
            } catch (Exception ex) {
                // 查看其父类属性
                try {
                    Field superField = target.getClass().getSuperclass()
                            .getDeclaredField(sourceFieldMap.get("name").toString());
                    superField.setAccessible(true);
                    superField.set(target, sourceFieldMap.get("value"));
                } catch (Exception e1) {
                    try {
                        Class superclass = target.getClass().getSuperclass();
                        Object o = superclass.newInstance();
                        Field superField = o.getClass().getSuperclass()
                                .getDeclaredField(sourceFieldMap.get("name").toString());
                        superField.setAccessible(true);
                        superField.set(target, sourceFieldMap.get("value"));
                    } catch (Exception e2) {
                        try {
                            Class superclass = target.getClass().getSuperclass();
                            Object o = superclass.newInstance();
                            Class superclass1 = o.getClass().getSuperclass();
                            Object o1 = superclass1.newInstance();
                            Field superField = o1.getClass().getSuperclass()
                                    .getDeclaredField(sourceFieldMap.get("name").toString());
                            superField.setAccessible(true);
                            superField.set(target, sourceFieldMap.get("value"));
                        } catch (Exception e3) {
                            System.out.println(("目标字段反射失败：" + e1.getMessage()));
                        }
                    }
                }
            }
        }
    }

    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof CharSequence) {
            return ((CharSequence)object).length() == 0;
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else if (object instanceof Collection) {
            return ((Collection)object).isEmpty();
        } else {
            return object instanceof Map ? ((Map)object).isEmpty() : false;
        }
    }

    /**
     * 获取属性类型(type)，属性名(name)，属性值(value)的map组成的list
     */
    private static List<Map<String, Object>> getFieldInfo(Object o)  {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        if (isEmpty(o)) {
            return null;
        }
        List<Field> fields = new ArrayList<Field>(Arrays.asList(o.getClass().getDeclaredFields()));
        try {
            //如果存在父类，获取父类的属性值，类型，名称并添加到一起
            Class sc = o.getClass().getSuperclass();
            if (sc != null) {
                fields.addAll(Arrays.asList(sc.getDeclaredFields()));
            }
            Object scObject = sc.newInstance();
            Class sc1 = scObject.getClass().getSuperclass();
            if (sc1 != null) {
                fields.addAll(Arrays.asList(sc1.getDeclaredFields()));
            }
            Object sc1Object = sc.newInstance();
            Class sc2 = sc1Object.getClass().getSuperclass();
            if (sc2 != null) {
                fields.addAll(Arrays.asList(sc2.getDeclaredFields()));
            }
            for (Field field : fields) {
                Map<String, Object> infoMap = new HashMap<String, Object>();
                infoMap.put("type", field.getType().toString());
                infoMap.put("name", field.getName());
                infoMap.put("value", getFieldValueByName(field.getName(), o));
                list.add(infoMap);
            }
        }catch (Exception e){
            System.out.println("错误信息："+e.getMessage());
        }
        return list;
    }

    /**
     * 根据属性名获取属性值
     *
     * @param fieldName
     * @param o
     * @return
     */
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter);
            return method.invoke(o);
        } catch (Exception e) {
            System.out.println(o.getClass().getSimpleName() + "字段：" + fieldName + "取值失败 " + e.getMessage());
            return null;
        }
    }


}
