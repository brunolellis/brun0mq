package com.bruno.reqrep;

import org.zeromq.ZMQ;

/**
 * simple request reply client
 *
 */
public class RequestReplyClient {

	public static void main(String[] args) {
		try (ZMQ.Context ctx = ZMQ.context(1); ZMQ.Socket server = ctx.socket(ZMQ.REQ)) {
			server.connect("tcp://localhost:5556");

			for (int i = 0; i < 10; i++) {
				String request = String.valueOf(System.nanoTime());
				server.send(request, 0);

				String response = server.recvStr();
				System.out.println(i + ") " + request + " -> " + response);
			}
		}
	}
}
