package com.bruno.reqrep;

import org.zeromq.ZMQ;

/**
 * simple request reply server
 *
 */
public class RequestReplyServer {

	public static void main(String[] args) {
		try (ZMQ.Context ctx = ZMQ.context(1); ZMQ.Socket worker = ctx.socket(ZMQ.REP)) {
			worker.bind("tcp://*:5556");

			while (true) {
				String message = worker.recvStr();
				System.out.println("request received: " + message);
				worker.send(message, 0);
			}
		}
	}
}