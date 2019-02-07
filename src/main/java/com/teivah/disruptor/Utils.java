package com.teivah.disruptor;

import java.util.concurrent.ThreadFactory;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

public interface Utils {

  static RingBuffer<Event<String>> createDisruptor(EventHandler<Event<String>>... handler) {
    ThreadFactory threadFactory = DaemonThreadFactory.INSTANCE;
    WaitStrategy waitStrategy = new BlockingWaitStrategy();

    Disruptor<Event<String>> disruptor = new Disruptor<>(
        () -> new Event<>(),
        128, threadFactory, ProducerType.SINGLE, waitStrategy);

    disruptor
        .handleEventsWith(handler);

    return disruptor.start();
  }
}
