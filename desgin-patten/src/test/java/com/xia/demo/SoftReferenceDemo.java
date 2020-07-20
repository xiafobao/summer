package com.xia.demo;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SoftReferenceDemo {
    public static void main(String[] args) {
        OrderManager orderManager = new OrderManager();

        // 获取订单线程，不断获取订单，验证系统内存不足后缓存数据会被清理掉，重新从数据库获取。
        new Thread(() -> {
            while (true) {
                orderManager.getOrder("101");
                quietlySleep(2000);
            }
        }).start();

        // 不断创建新对象，模拟内存不足导致垃圾回收，新对象也是软引用，这样可以被回收，避免OOM异常。
        new Thread(() -> {
            List<SoftReference<BigObject>> list = new ArrayList<>();
            while (true) {
                list.add(new SoftReference<>(new BigObject()));
                quietlySleep(50);
            }
        }).start();

        // 主线程休眠等待
        quietlySleep(20 * 60 * 1000);
    }

    private static void quietlySleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // 模拟大对象
    private static class BigObject {
        byte[] b = new byte[4 * 1024];
    }
}

class OrderManager {
    public Order getOrder(String id) {
        Order order = OrderCache.getInstance().getCachedOrder(id);
        if (order == null) {
            order = getOrderFromDB(id);
        }
        return order;
    }

    private Order getOrderFromDB(String id) {
        Order order = new Order(id, (int) (Math.random() * 100));
        System.out.println(new Date() + " get order from DB. XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX " + order);
        OrderCache.getInstance().cache(order);
        return order;
    }
}

class OrderCache {
    private static volatile OrderCache singlonCache;
    private HashMap<String, OrderReference> refChache;
    private ReferenceQueue queue;

    private OrderCache() {
        this.queue = new ReferenceQueue();
        this.refChache = new HashMap<>();
    }

    public static OrderCache getInstance() {
        if (singlonCache == null) {
            synchronized (OrderCache.class) {
                if (singlonCache == null) {
                    singlonCache = new OrderCache();
                }
            }
        }
        return singlonCache;
    }

    public void cache(Order order) {
        cleanCache();//清除已经标记为垃圾的引用
        OrderReference reference = new OrderReference(order, queue);
        refChache.put(order.getId(), reference);//将对象的软引用保存到缓存中
    }

    public Order getCachedOrder(String key) {
        Order order = null;
        if (refChache.containsKey(key)) {
            order = (Order) refChache.get(key).get();
            System.out.println(new Date() + " get order from cache. " + order);
        }
        return order;
    }

    private void cleanCache() {
        OrderReference reference = null;
        while ((reference = (OrderReference) queue.poll()) != null) {
            System.out.println(new Date() + " cleanCache");
            refChache.remove(reference._key);
        }
    }

    static class OrderReference extends SoftReference {
        private String _key;

        public OrderReference(Order referent, ReferenceQueue q) {
            super(referent, q);
            _key = referent.getId();
        }
    }
}

class Order {
    private String id;
    private long price;

    public Order(String id, long price) {
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "order id: " + id + ", price: " + price;
    }
}
