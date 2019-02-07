package com.teivah.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;

public class Demo1 {

  public static void main(String[] args) {
    new Demo1().demo();
  }

  private void demo() {
    EventHandler<Event<String>> handler =
        (event, sequence, endOfBatch) -> System.out.println(event.getPayload());

    final RingBuffer<Event<String>> ringBuffer = Utils.createDisruptor(handler);

    final long sequence = ringBuffer.next();
    final Event<String> event = ringBuffer.get(sequence);
    event.setPayload("Hello, World 1!");
    ringBuffer.publish(sequence);
  }
}
