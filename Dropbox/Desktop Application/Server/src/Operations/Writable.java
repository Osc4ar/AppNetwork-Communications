package Operations;

import java.nio.channels.SocketChannel;
import java.nio.ByteBuffer;

public class Writable {
	
	public static boolean signError;
	public static SocketChannel ch;
	public static boolean signFlag;
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
			logFlag = false;
		} // End if.
		
		if ( signFlag ) {
			ch = ( SocketChannel ) Main.Main.k.channel ( );
			str = "<continue>";
			ch = ( SocketChannel ) Main.Main.k.channel ( );
			b = ByteBuffer.wrap ( str.getBytes ( ) );
			ch.write ( b );
			signFlag = false;
		} // End if.
		
		if ( signError ) {
			ch = ( SocketChannel ) Main.Main.k.channel ( );
			str = "Error at sign up.";
			ch = ( SocketChannel ) Main.Main.k.channel ( );
			b = ByteBuffer.wrap ( str.getBytes ( ) );
			ch.write ( b );
			signError = false;
		} // End if.
		
	} // End write.

} // End class.
