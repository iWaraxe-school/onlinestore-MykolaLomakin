package store.Order;

import products.Product;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class OrderProcessor {
    private static final int THREAD_POOL_SIZE = 10;
    private static final long CLEANUP_INTERVAL = 120;

    private final ExecutorService executorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private OrderProcessor() {
    }

    private static class SingletonHelper {
        private static final OrderProcessor ORDER_PROCESSOR = new OrderProcessor();
    }

    public static OrderProcessor getInstance() {
        return SingletonHelper.ORDER_PROCESSOR;
    }

    public void processOrder(Product product) {
        CreateOrder createOrder = new CreateOrder(product);
        executorService.submit(createOrder);
    }

    public void startPeriodicCleanup() {
        ClearOrder clearOrder = new ClearOrder();
        scheduledExecutorService.scheduleAtFixedRate(clearOrder, 0, CLEANUP_INTERVAL, TimeUnit.SECONDS);
    }
}