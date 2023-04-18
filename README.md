## 6. Multithreading
### Materials

[Concurrency](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
[L10 from slide 24](https://drive.google.com/file/d/1lQorg4OeGddgLf54a3NaSKCe3NbPKWXN/view?usp=sharing) 
[L11 from](https://drive.google.com/file/d/1hK3FwY2zJG0ChY3phqA2UlqJW15lZf\_O/view?usp=sharing) 

### VideoLectures
- [Multithreading, part 1](https://youtu.be/fH7Yb9HnK3Q)
- [Multithreading, part 2](https://youtu.be/RxrtmHPnOo0)

### Task #6

Please implement `create order` functionality. Each order should be processed in separate thread. Whe user select product, generate the random int from 1 to 30, and create thread that will process selected order for selected time, and after it place the product in another collection (for example, purchased goods). And create one more thread, that will be executed periodically, e.g. ones in 2 mins, that will clean up purchased collection.

You can implement this in "native" java methods but better and simplier to use `java.util.concurrent` package. If you need further guidance you can look at step-by-step guides for task solutions in the Hints section.
  
### Hints
#### Basic multithreading solution
To implement multithreading in the OnlineStore app, you can create two threads: one for processing orders and another for cleaning up the purchased goods collection.
1. Create a `Runnable` implementation for processing orders. In this implementation, you can generate a random number to select a product and process the order for a specified time. After processing the order, add the purchased product to a collection.
2. Create a `Runnable` implementation for cleaning up the collection of the purchased goods. In this implementation, you can periodically check the collection and remove any items that have been in the collection for a certain amount of time.
3. In the main class of the application, create instances of both `Runnable` implementations and pass them to `Thread` instances.
4. Start the threads using the `start()` method.
With these steps, you should have a basic multithreading implementation in the OnlineStore app. You can use any pattern or implementation you prefer to manage the creation and execution of threads.
#### Complex multithreading solution
1. Implement the `create order` functionality. To do this, create a class called `CreateOrderTask` that implements the `Runnable` interface. In the `run()` method of this class, generate a random integer between 1 and 30, select a random product, and process the order for the specified time. Afterward, add the product to a thread-safe `collection` such as a `ConcurrentLinkedQueue`.
2. Create a thread pool using the `ExecutorService` class. This pool will be used to execute instances of the `CreateOrderTask` class. The pool size should be configurable, but a good default value would be the number of available processors on the system.
3. Create a method called `startOrderProcessing()` that will submit instances of the `CreateOrderTask` class to the thread pool. This method should be called when the user selects the `create order` command.
4. Create a class called `ClearPurchasedGoodsTask` that implements the `Runnable` interface. In the `run()` method of this class, remove all items from the thread-safe `collection` of purchased goods. Use a `ScheduledExecutorService` to schedule this task to run periodically, for example, once every two minutes.
5. Create a thread-safe `collection` such as a `ConcurrentLinkedQueue` to store purchased goods.
6. Modify the `create order` functionality to add the selected product to the thread-safe `collection` of purchased goods.
7. Modify the `print` command to print the contents of the thread-safe `collection` of purchased goods.
8. Add error handling to ensure that the application does not crash if the thread pool or scheduled executor service encounters an error. Use a `try-catch` block to catch any exceptions thrown by these classes and log the error.
9. Add the ability to configure the thread pool size and scheduling interval via command line arguments or a configuration file. Use a `ThreadPoolExecutor` to create the thread pool and allow for custom configuration of the thread pool size and other parameters.
10. Create a method to gracefully shut down the thread pool and scheduled executor service when the application is terminated. Use the `ExecutorService.shutdown()` method to gracefully shut down the thread pool and the `ScheduledExecutorService.shutdown()` method to shut down the scheduled executor service gracefully. Call these methods when the application is terminated, for example, when the user selects the `quit` command.
