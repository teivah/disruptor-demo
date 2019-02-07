package com.teivah.disruptor;

import static com.teivah.disruptor.Utils.createDisruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;

public class Demo3 {

  public static void main(String[] args) {
    new Demo3().demo();
  }

  private void demo() {
    EventHandler<Event<String>> handler1 =
        (event, sequence, endOfBatch) -> {
          if (sequence % 2 == 0) {
            System.out.println("Consumer 1: " + event.getPayload());
          }
        };

    EventHandler<Event<String>> handler2 =
        (event, sequence, endOfBatch) -> {
          if (sequence % 2 == 1) {
            System.out.println("Consumer 2: " + event.getPayload());
          }
        };

    final RingBuffer<Event<String>> ringBuffer = createDisruptor(handler1, handler2);

    send(ringBuffer, "Hello, World 1!");
    send(ringBuffer, "Hello, World 2!");
    send(ringBuffer, "Hello, World 3!");
    send(ringBuffer, "Hello, World 4!");
  }

  private void send(RingBuffer<Event<String>> ringBuffer, String payload) {
    final long sequence = ringBuffer.next();
    final Event<String> event = ringBuffer.get(sequence);
    event.setPayload(payload);
    ringBuffer.publish(sequence);
  }
}
