package cn.com.onlinetool;

public class TestThread {
    static class Store{
        private int value;
        private boolean flag = false;

        public void put(String name, int value){
            System.out.println(name + "调用 put");
            while (flag){
                try {
                    System.out.println(name + "等待对方...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = true;
            this.value = value;
            System.out.println(name + "通知对方...");
            notifyAll();
        }

        public int get(String name){
            System.out.println(name + "调用 get");
            while (!flag){
                try {
                    System.out.println(name + "等待对方...");
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            flag = false;
            System.out.println(name + "通知对方...");
            notifyAll();
            return this.value;
        }

    }

    static class Producer extends Thread{
        private Store store;
        private String name;
        public Producer(Store b,String n){
            this.store = b;
            this.name = n;
        }

        @Override
        public void run() {
            for(int i = 1; i < 6; i++){
                System.out.println(name + "等待对象锁释放，继续执行");
                synchronized (store){
                    store.put(this.name, i);
                    System.out.println(name + " producer 生产产品：" + i);
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    static class Consumer extends Thread{
        private Store store;
        private String name;
        public Consumer(Store b,String n){
            this.store = b;
            this.name = n;
        }

        @Override
        public void run() {
            for(int i = 1; i < 6; i++){
                System.out.println(name + "等待对象锁释放，继续执行");
                synchronized (store){
                    store.get(name);
                    System.out.println(name + " consumer 消费产品：" + i);
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }


    public static void main(String[] args) {
        Store store = new Store();
        Producer producer = new Producer(store, "生产者");
        Consumer consumer = new Consumer(store, "消费者");
        producer.start();
        consumer.start();

    }
}
