package com.xia.demo.common;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import lombok.extern.slf4j.Slf4j;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
@Slf4j
public class SerializationUtil {

    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<>();

    private static Objenesis objenesis = new ObjenesisStd(true);

    public SerializationUtil() {

    }

    /**
     * 获取类的schema
     *
     * @param cls
     * @return
     */
    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            if (schema != null) {
                cachedSchema.put(cls, schema);
            }
        }
        return schema;
    }

    /**
     * 序列化（对象-->字节数组）
     *
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> byte[] serialize(T obj) {
        Class<T> aClass = (Class<T>) obj.getClass();
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            Schema<T> schema = getSchema(aClass);
            //序列化
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            log.error("序列化错误：", e);
            throw new IllegalArgumentException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }

    /**
     * 反序列化（字节数组-->对象）
     *
     * @param data
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T deserialize(byte[] data, Class<T> cls) {
        try {
            /*
             * 如果一个类没有参数为空的构造方法时候，那么你直接调用newInstance方法试图得到一个实例对象的时候是会抛出异常的
             * 通过ObjenesisStd可以完美的避开这个问题
             * */
            T message = objenesis.newInstance(cls);
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(data, message, schema);
            return message;
        } catch (Exception e) {
            log.error("序列化错误：", e);
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }
}