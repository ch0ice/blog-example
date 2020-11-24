package cn.com.onlinetool.nativeio.bio.bioPlusServer;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * @author choice
 * @date create in 2020/3/23 14:58
 */
public class BioPlusTimeServerHandlerExecutePoll {
    private ExecutorService executor;

    public BioPlusTimeServerHandlerExecutePoll(int corePollSize, int maximumPollSize, int queueSize){
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("false-nio-time-server-pool-%d").build();
        executor = new ThreadPoolExecutor(corePollSize,maximumPollSize, 120L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(queueSize), threadFactory);
    }

    public void execute(Runnable task){
        executor.execute(task);
    }
}
