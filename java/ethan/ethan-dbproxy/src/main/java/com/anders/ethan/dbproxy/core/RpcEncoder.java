package com.anders.ethan.dbproxy.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RpcEncoder extends MessageToByteEncoder {


	@Override
	public void encode(ChannelHandlerContext ctx, Object in, ByteBuf out)
			throws Exception {
	}
}
