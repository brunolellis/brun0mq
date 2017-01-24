package com.bruno.pubsub;

import org.zeromq.ZMQ;

/**
 * client subscriber
 * 
 * this client subscribes to messages prefixed with "B".
 */
public class PubSubClientB {

	public static void main(String[] args) {
		try (ZMQ.Context ctx = ZMQ.context(1); ZMQ.Socket subscriber = ctx.socket(ZMQ.SUB)) {
			subscriber.connect("tcp://localhost:5556");
			subscriber.subscribe("B".getBytes());

			int count = 0;
			while (count < 5) {
				String message = subscriber.recvStr();
				System.out.println(message);
				count++;
			}
		}
	}
}
