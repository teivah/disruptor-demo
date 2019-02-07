package com.teivah.disruptor;

import static com.teivah.disruptor.Utils.createDisruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;

public class Demo2 {

  public static void main(String[] args) {
    new Demo2().demo();
  }

  private void demo() {
    EventHandler<Event<String>> handler1 =
        (event, sequence, endOfBatch) ->
            System.out.println("Consumer 1: " + event.getPayload());

    EventHandler<Event<String>> handler2 =
        (event, sequence, endOfBatch) ->
            System.out.println("Consumer 2: " + event.getPayload());

    final RingBuffer<Event<String>> ringBuffer = createDisruptor(handler1, handler2);

    final long sequence = ringBuffer.next();
    final Event<String> event = ringBuffer.get(sequence);
    event.setPayload("Hello, World!");
    ringBuffer.publish(sequence);
  }
}
