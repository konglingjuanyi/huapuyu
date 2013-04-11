package socket;

import java.io.InputStream;
import java.io.PrintStream;

import org.apache.commons.net.telnet.TelnetClient;

public class Shell {

	private TelnetClient telnet = new TelnetClient();

	private InputStream in;

	private PrintStream out;

	private char prompt = '$';// 普通用户结束

	public Shell(String ip, int port, String user, String password) {
		try {
			telnet.connect(ip, port);
			in = telnet.getInputStream();
			out = new PrintStream(telnet.getOutputStream());
			// 根据root用户设置结束符
			this.prompt = user.equals("root") ? '#' : '>';
			login(user, password);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录
	 * 
	 * @param user
	 * @param password
	 */
	public void login(String user, String password) {
		// readUntil("login:");
		readUntil("login:");
		write(user);
		readUntil("Password:");
		write(password);
		readUntil(prompt + "");
	}

	/**
	 * 读取分析结果
	 * 
	 * @param pattern
	 * @return
	 */
	public String readUntil(String pattern) {
		try {
			char lastChar = pattern.charAt(pattern.length() - 1);
			StringBuffer sb = new StringBuffer();
			char ch = (char) in.read();
			while (true) {
				sb.append(ch);
				if (ch == lastChar) {
					if (sb.toString().endsWith(pattern)) {
						return sb.toString();
					}
				}
				ch = (char) in.read();
				System.out.print(ch);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 写操作
	 * 
	 * @param value
	 */
	public void write(String value) {
		try {
			out.println(value);
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向目标发送命令字符串
	 * 
	 * @param command
	 * @return
	 */
	public String sendCommand(String command) {
		try {
			write(command);
			return readUntil(prompt + "");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭连接
	 */
	public void disconnect() {
		try {
			telnet.disconnect();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		TelnetClient telnet = new TelnetClient();
		try {
			Shell she = new Shell("192.168.2.88", 11222, "memcache", "");
			System.out.println(she);
			System.out.println(she.sendCommand("ls"));
			she.disconnect();

		}
		catch (Exception e) {
			// TODO: handle exception
		}

	}

}
