package com.bruno.pubsub;

import java.util.Random;

import org.zeromq.ZMQ;

/**
 * message publisher.
 * 
 * each message is generated with a prefix ("A" or "B").
 *
 */
public class PubSubServer {
	
	public static void main(String[] args) {
		try (ZMQ.Context ctx = ZMQ.context(1); ZMQ.Socket publisher = ctx.socket(ZMQ.PUB)) {
			publisher.bind("tcp://*:5556");

			String prefix = "A";
			Random random = new Random();
			while (true) {
				int id = random.nextInt(100000);
				int data = random.nextInt(500);
				publisher.send(String.format("%s %05d %d", prefix, id, data));
				prefix = prefix == "A" ? "B" : "A";
			}
		}
	}
}
