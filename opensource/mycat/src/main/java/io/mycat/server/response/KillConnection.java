/*
 * Copyright (c) 2013, OpenCloudDB/MyCAT and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software;Designed and Developed mainly by many Chinese 
 * opensource volunteers. you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Any questions about this component can be directed to it's project Web address 
 * https://code.google.com/p/opencloudb/.
 *
 */
package io.mycat.server.response;
import io.mycat.net.Connection;
import io.mycat.net.NetSystem;
import io.mycat.server.MySQLFrontConnection;
import io.mycat.server.packet.OkPacket;
import io.mycat.util.SplitUtil;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * @author mycat
 */
public final class KillConnection {

    private static final Logger logger = Logger.getLogger(KillConnection.class);

    public static void response(String stmt, int offset, MySQLFrontConnection mc) {
        int count = 0;
        List<MySQLFrontConnection> list = getList(stmt, offset, mc);
        if (list != null)
            for (MySQLFrontConnection c : list) {
                StringBuilder s = new StringBuilder();
                logger.warn(s.append(c).append("killed by manager").toString());
                c.close("kill by manager");
                count++;
            }
        OkPacket packet = new OkPacket();
        packet.packetId = 1;
        packet.affectedRows = count;
        packet.serverStatus = 2;
        packet.write(mc);
    }

    private static List<MySQLFrontConnection> getList(String stmt, int offset, MySQLFrontConnection mc) {
        String ids = stmt.substring(offset).trim();
        if (ids.length() > 0) {
            String[] idList = SplitUtil.split(ids, ',', true);
            List<MySQLFrontConnection> fcList = new ArrayList<MySQLFrontConnection>(idList.length);
           
            for (String id : idList) {
                long value = 0;
                try {
                    value = Long.parseLong(id);
                } catch (NumberFormatException e) {
                    continue;
                }
                for (Connection fc:  NetSystem.getInstance().getAllConnectios().values()) {
                    if (fc instanceof  MySQLFrontConnection && fc.getId()==value) {
                        fcList.add((MySQLFrontConnection) fc);
                        break;
                    }
                }
            }
            return fcList;
        }
        return null;
    }

}