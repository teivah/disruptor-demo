package com.teivah.disruptor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Event<T> {

  private T payload;
}
