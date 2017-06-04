package Operations;

import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;

public class Writable {
	
	public static SocketChannel ch;
	public static String nickname;
	public static boolean logFlag;
	public static ByteBuffer b;
	public static String str;
	
	public static void write ( ) throws Exception {
		
		if ( logFlag ) {
			ch = ( SocketChannel ) Main.Main.k.channel ( );
			str = "<continue>:" + nickname;
			b = ByteBuffer.wrap ( str.getBytes ( ) );
			ch.write ( b );
		} // End if.
		
	} // End write.

} // End class.
