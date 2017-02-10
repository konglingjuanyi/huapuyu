package com.anders.ethan.dbproxy.protocol.mysql;

import java.nio.ByteBuffer;

import com.anders.ethan.dbproxy.mysql.BufferUtil;
import com.anders.ethan.dbproxy.mysql.MySQLMessage;

/**
 * From server to client in response to command, if no error and no result set.
 * 
 * <pre>
 * Bytes                       Name
 * -----                       ----
 * 1                           field_count, always = 0
 * 1-9 (Length Coded Binary)   affected_rows
 * 1-9 (Length Coded Binary)   insert_id
 * 2                           server_status
 * 2                           warning_count
 * n   (until end of packet)   message fix:(Length Coded String)
 * 
 * @see http://forge.mysql.com/wiki/MySQL_Internals_ClientServer_Protocol#OK_Packet
 * </pre>
 * 
 * @author mycat
 */
public class OkPacket extends MySQLPacket {
	public static final byte FIELD_COUNT = 0x00;
	public static final byte[] OK = new byte[] { 7, 0, 0, 1, 0, 0, 0, 2, 0, 0,
			0 };

	public byte fieldCount = FIELD_COUNT;
	public long affectedRows;
	public long insertId;
	public int serverStatus;
	public int warningCount;
	public byte[] message;

	public void read(BinaryPacket bin) {
		packetLength = bin.packetLength;
		packetId = bin.packetId;
		MySQLMessage mm = new MySQLMessage(bin.data);
		fieldCount = mm.read();
		affectedRows = mm.readLength();
		insertId = mm.readLength();
		serverStatus = mm.readUB2();
		warningCount = mm.readUB2();
		if (mm.hasRemaining()) {
			this.message = mm.readBytesWithLength();
		}
	}

	public void read(byte[] data) {
		MySQLMessage mm = new MySQLMessage(data);
		packetLength = mm.readUB3();
		packetId = mm.read();
		fieldCount = mm.read();
		affectedRows = mm.readLength();
		insertId = mm.readLength();
		serverStatus = mm.readUB2();
		warningCount = mm.readUB2();
		if (mm.hasRemaining()) {
			this.message = mm.readBytesWithLength();
		}
	}

//    public byte[] writeToBytes() {
//        ByteBuffer buffer=NetSystem.getInstance().getBufferPool().allocate();
//        BufferUtil.writeUB3(buffer, calcPacketSize());
//        buffer.put(packetId);
//        buffer.put(fieldCount);
//        BufferUtil.writeLength(buffer, affectedRows);
//        BufferUtil.writeLength(buffer, insertId);
//        BufferUtil.writeUB2(buffer, serverStatus);
//        BufferUtil.writeUB2(buffer, warningCount);
//        if (message != null) {
//            BufferUtil.writeWithLength(buffer, message);
//        }
//        buffer.flip();
//        byte[] data = new byte[buffer.limit()];
//        buffer.get(data);
//
//        return data;
//    }

//	public void write(GenalMySQLConnection c) {
//		ByteBuffer buffer=NetSystem.getInstance().getBufferPool().allocate();
//		BufferUtil.writeUB3(buffer, calcPacketSize());
//		buffer.put(packetId);
//		buffer.put(fieldCount);
//		BufferUtil.writeLength(buffer, affectedRows);
//		BufferUtil.writeLength(buffer, insertId);
//		BufferUtil.writeUB2(buffer, serverStatus);
//		BufferUtil.writeUB2(buffer, warningCount);
//		if (message != null) {
//			BufferUtil.writeWithLength(buffer, message);
//		}
//		c.write(buffer);
//	}

	
	@Override
	public int calcPacketSize() {
		int i = 1;
		i += BufferUtil.getLength(affectedRows);
		i += BufferUtil.getLength(insertId);
		i += 4;
		if (message != null) {
			i += BufferUtil.getLength(message);
		}
		return i;
	}

	@Override
	protected String getPacketInfo() {
		return "MySQL OK Packet";
	}

}