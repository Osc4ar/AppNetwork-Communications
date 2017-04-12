package MulticastSocket;

import java.net.UnknownHostException;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
	
	protected static ArrayList <String> aList;
	private static MulticastSocket s;
	public static InetAddress group;
	public static int ports, portc;
	public static String address;

	public static void main ( String [ ] args ) {
		
		try {
			aList = new ArrayList <String> ( );
			System.setProperty ( "java.net.preferIPv4Stack", "true" );
			ports = 9000;
			portc = 9001;
			address = "230.1.1.1";
			s = new MulticastSocket ( ports );
			System.out.println ( "\n\tMulticast service initialized..." );
			group = null;
			try {
				group = InetAddress.getByName ( address );
			} catch ( UnknownHostException e ) {
				System.err.println ( "\n\tInvalid address." );
				System.exit ( 0 );
			} // End try - catch.
			s.joinGroup ( group );
			s.setTimeToLive ( 200 );
			for ( ; ; ) {
				DatagramPacket p = new DatagramPacket ( new byte [ 1500 ], 1500 );
				s.receive ( p );
				String msg = new String ( p.getData ( ), 0, p.getLength ( ) );
				System.out.println ( "\n\tMessage received from: " + p.getAddress ( ) + " : " + p.getPort ( ) + "\n\tMessage: " + msg );
				Type ( msg );
				try {
					Thread.sleep ( 5000 );
				} catch ( InterruptedException ie ) { } // End try - catch.
			} // End for.
		} catch ( Exception e ) {
			e.printStackTrace ( );
		} // End try - catch.	
		
	} // End main.
	
	/* The type of the message received from the client
	 * can be <msg>, <init> or <private>. If it's <msg>, the
	 * server will send the message to the common chat window, 
	 * <init> if a new user wants to join to the conversation,
	 * and <private> to open a personal chat with another user.
	 */
	
	public static void Type ( String msg ) throws IOException {
		
		String [ ] sp = msg.split ( " " );
		msg = "";
		
		if ( sp [ 0 ].equalsIgnoreCase ( "<init>" ) ) {
			String type = "<init>";
			String userName = "";
			byte [ ] b = type.getBytes ( );
			byte [ ] b1;
			String user;
			DatagramPacket p = new DatagramPacket ( b, b.length, group, portc );
			s.send ( p );
			for ( int i = 1 ; i < sp.length ; i++ ) {
				userName = userName + sp [ i ] + " ";  
			} // End for.
			aList.add ( userName );
			OnlineUsers ( );
			for ( int i = 0 ; i < aList.size ( ) ; i++ ) {
				user = aList.get ( i );
				b1 = user.getBytes ( );
				DatagramPacket p1 = new DatagramPacket ( b1, b1.length, group, portc );
				s.send ( p1 );
			} // End for.
		} // End if.
		
		if ( sp [ 0 ].equalsIgnoreCase ( "<msg>" ) ) {
			String type = "<msg>";
			String aux = "";
			byte [ ] b1 = type.getBytes ( );
			DatagramPacket p1 = new DatagramPacket ( b1, b1.length, group, portc );
			s.send ( p1 );
			for ( int i = 1 ; i < sp.length ; i++ ) {
				msg = msg + sp [ i ] + " ";  
			} // End for.
			aux = Emoticon ( msg );
			msg = aux;
			byte [ ] b = msg.getBytes ( );
			DatagramPacket p = new DatagramPacket ( b, b.length, group, portc );
			s.send ( p );
		} // End if.
		
		if ( sp [ 0 ].equalsIgnoreCase ( "<private>" ) ) {
			String msgFrom = sp [ sp.length - 1 ];
			String msgFor = "";
			for ( int i = 1 ; i < sp.length - 2 ; i++ ) {
				msgFor = msgFor + sp [ i ];  
			} // End for.
			System.out.println( "\n\tPrivate Message for: " + msgFor + ". From: " + msgFrom + "." );
			String type = "<private>";
			byte [ ] b = type.getBytes ( );
			DatagramPacket p = new DatagramPacket ( b, b.length, group, portc );
			s.send ( p );
			b = msgFrom.getBytes ( );
			DatagramPacket p1 = new DatagramPacket ( b, b.length, group, portc );
			s.send ( p1 );
			b = msgFor.getBytes ( );
			DatagramPacket p2 = new DatagramPacket ( b, b.length, group, portc );
			s.send ( p2 );
		} // End if.
		
	} // End Type.
	
	/* The client needs to know how man users are online, the method OnlineUsers
	 * gets the size of the arraylist, convert the integer into a byte array,
	 * and the server send the information as a datagram packet.
	 */
	
	public static void OnlineUsers ( ) throws IOException {
		
		int size = aList.size ( );
		ByteArrayOutputStream baos = new ByteArrayOutputStream ( );
		DataOutputStream dos = new DataOutputStream ( baos );
		dos.writeInt ( size );
		dos.flush ( );
		byte [ ] b = baos.toByteArray ( );
		DatagramPacket p = new DatagramPacket ( b, b.length, group, portc );
		s.send ( p );
		baos.close ( );
		dos.close ( );
		
	} // End OnlineUsers.
	
	/* Emoticon method search for the substring :), :D, :P, :*, ;) and :v, and replace those 
	 * substring for a HTML img label, this new string is sended to the client, and
	 * the JEditoPane detects the label ans search for the specified image URL.
	 */
	
	public static String Emoticon ( String msg ) {
		
		String smile = "<img src=https://lh3.googleusercontent.com/XL_lcQihANqec2RAaxrCgLSnlZWBQI4R6GtUFV2zjl0UThvFRQKwgsHRS0hn5rwXlDeMpJuUhThh1xtvuDGQfaVyQlvQmTPuowfADo_-Nz2gjzSuy33HLu2VfFlLLf9THNyFYTsuRaxViUlH6H2tBh_Re0OhV8ffMUDq-RP_-jfsRk8TQyHqskThu-14RBs30jdynDU8qgablFi4tUyW1tA7VQxdkgjRq9o9UX3B5CNLjyAZhMmrOFxxMlL4uw0q82qcd8razLjkaZrObTFbPI4mxXLM_4XhFtylkTJFiAHiM5-X-B4zZkj_xFaqmziiJMypzIdASl32_Jvf50_IAQm4JizkSCYfCuHjBg9uqPqhZ831Kv5ny6kLkehbTu7ke1xA8lI0CJrpZiuYb6IgLb_gTW5pH-nBMoqDSI6wXnJHVrOY3-3r0C5sD0HAr4bi44jzzKbYhm6No7wN6U_8Zb5ZrW047qXDJhZiyCqFwJu4-es5umAZaYzNoMMu37MHiofNivCsH5nF_WdFdoQttCNoQEt2NjBpFCrZVH175OOCzcDo1ORn4QxNRPnVJIOfLwELtOQIZ0_FTaeOhWJ8XdGJV_06VdrjZ3fTq_nCIfIYT89O=s11-no>";		
		String tongue = "<img src =https://lh3.googleusercontent.com/YzAKc0LdlXyo64K7eWAl2SsQBSlD5UEzx6747bS0OvybLw_QyXnJ9PgFXUvNQ6VRZ7Le2Ih8LVEpihYpWYNJV2yk61AsdIrs9GxTqGjcrjSRQ2hLh-7U7_anFscW3Kwag43zZMxE2ahPMyPh-iP81CjTiVIl1X3eRpEcyO7O531fe_CK03mIuX6GM1cMEQVexmNUbw4U3AieInYWsNG13Cg7-0hUFLUvzfaJwVbvnJrKEoIlLrqk_e4MjFOv3VX8hAVJrOK0DZsjFN8fwifSIBSHXFlSYyc8y7b86Dfat_mAA47sTDgN8PjuFKZQrgPYV1hTTVi5ro4aOGGLuV60uDXnCzlWiTYzeeYzLigs8MBt2RtKnQ2MDSZaNxntm4ZLbutnwrFxss3fIqpCiY69c_qmVBwZnAEK5F26K2iyhR2mNCRQNPPEM77jYuBwzki1NiZA6oPwt4vWQuPWpZiIFWIC3JdGIBaX22cgaEQrwX0hX4GDPybHaWI0bn41_ykKk7Eglhar1r92GCRkW62GqNqxS0E-YZBOdxDF07NZ53MOVhQ2qm7xArm2D8me8DRv-dzqYMJMQb_LMLLv2dP2uRr1J2eOY_TGS_GzNuwZ3iHPZyAwD2dr=s11-no>";
		String pacman = "<img src =https://lh3.googleusercontent.com/sWqhY2uvMQNCBZmS2z1O8fCxaLChX-_GpF49pqeJpYIfhxLwulmvY4By4HB5DsDuPi9d3k43tszoycvql5uXtS2Vaj_O5rlKNmrZxoTd6sQg-N_uXXh9x7e3lnosiNqETgjD4R6yHJDkcBWpkzXAgV05q3-EjFa15mVNto8t09DTvMuEUWE416QBSIXR4e4psua2vyDx1fVeQcGH-qOhTw7kjSKOb20hW9M8ZkT6y_sT_e7wulGnEH6BWt0htA_bX1AEmtsW4BUiOh3mxlRBFYS9xHuLmlHw-lClrFkz8QhfNQIVoFtz8IE0znqEHhoHp3-kN7SLUhd71q-kHgfTbhZ0CLiT8QVmjszfPIIht-b2QZr_DzTa6eHrdLgB0CkzCdqiySeBLPMDd8IyTUX_1YIdpXnyDZ4sudNm0ffxc4-IezseM3VEDvGt5Ei9JtcHr2IePgy3e-CvIxrZVlyflfcRP2XpHxF_xsSoGztSaoHAf0aogBzO8EZ1UkN4fKxAdS_TbpRgwS_hIkvyXBXXL1U8CuUcLksj1YzZu2ISaooYoxkFZXNA9yzYFaEEJi7isdkINMkWgUT5acnkO20Fd135Htfn7-jjn-gjhBT-FWZwfk-UaqIs=s11-no>";
		String happy = "<img src =https://lh3.googleusercontent.com/6YS7knecnzL9DlZ9Jqo97PAsyqTLUtudcK3B40pXg_v6zpov6AlEZjX2DlHUuyrYsGOJV3Yrok-Nly9qTAWb1GG8LHI-M9yx_HsVMDV8x1lzTLD3CyMdMSOzvWjPUY6jMizvkYZYbojT3hH0hBvZO0-Tuqkm4ry6pvqXcVAtM4jIb7DIqHEle5pnHpcBNBQfIsVXMcA0XFFoxobxJeRDj3TEz96khiCAqzrKgA5B7qO_9eNA2hXw8h9GCeESLzL09VmgpV2rx1gRkCy3dnJZGtvqeTa_t1e0P9FKmnIW2X9R9iyOYKNj5OVh4V0GdhcG-S9LjBkQyPIK9PoClzo_w_iwwGuJgs_x5igmB3uWhTJcfh4g6bsNkyLqMwsato4bORFwZOOplsKN5XQBZ7TkNHFNL_hSRo600XNaZS8J0Ar8odyuEJBxsfmVVljkWICtZGAisT3wtOAkgZcN3rMyWEr_StWvC9FYFd6NXbgHfTlkOOkqN7t63TJN0G6yuhXwHFRXt3DhM9Im5HPr-3ZZnkyAWCfXd3ZgLTexk5EmtJrwAwLk0ysGDKpG9-pdB6fTYGoxSNXWOf4Zs1CkOt9NkROKnfxTOHktDld9jG6-qXEtEg9H=s11-no>";
		String sad = "<img src =https://lh3.googleusercontent.com/GQU9rqddtnsBz9emLmeYQCOo1duA-LFxuXGRqrjsJDai5-oyQWiko6pBOTZ1-8UIw93gBofVrcPAZciJ-fiFKZJjWs57JaFse6aIwX-aEkMAQLbI85UXNFWAmP8ItKqY-lVMQiGrWa8YF4BF4UUfeR1zOaHi-PMhzVKueiY7JnsZj2qKqemGeA3jVntHj1wdCmyA4ySW2ZiStwbDZ98dR3NM6VpS7PZOUWzr2GPntt7ditu4pV8kQY5sr3rZZmDj6KVkydRVq-jV0AA22A74HhQE0__7cFK911c3VCCBQXLbnSXOoCyP_7Y4GecWnAUgdQJmx7hu5eL6HQ2j5zBOJnERRICodLGn7n1_SIcGIx024IlTAgKj7HmLR5knnhSQe8DH1q8QyByhXKl4H0a-KLxafmS2TczctHUvFri3McWmtmLvwyIHVLKM4l1gyQEXotXzcyD5cq2cnDSDutqv9FmAgsz9uT982TRRAzg7NrZbn3MpnF-t2lY9-zU4vk4Rf3HNNteLeLZXlSSXBBSewPTQkdHIhlJzYijJ8nmBNJz9IC9rHsB_mT4ZLpd2-jnQKmReeaBYl_QnoKcTcRc8ayi7evUkyJ-rwPH-vWcZQLXqXC-czX55=s11-no>";
		String kiss = "<img src =https://lh3.googleusercontent.com/xDO2kFubOK2Os3Q8aZDgq6pHzMRF1KVl_8-f0ImePU1U_UlTzBzMljdXk-C-YaRfrepDKagBBvcCPhojpeWDNEJIE3T_sqc8o3BceOw70zs0KyeL_bq2P_Vqv38m6amQvBhsIyrtP1iCw9mZDJ4oRH8OG1nOxeoPwuSkjPtf21dBJXR5BP4SG8VumQU6-_vrvwcNEdGvDJdLfqry_SLVk8iXZZZ2J3AYpO62e90Wrg4hwVK4VX9zaewymvXb0PZKdI5A28Z4MjGrSEq0rHnppLhtBj5912ll5dfjbwpiZg8Ts7zQZmJWV9oqRUt30DKzHrEteGC4pnUCt94MC5u8Y7PnAUNKNi0OcQlPf1ISG98IV-1wofkrGGsxRPwOvX7U0DuICZ_1zPb4B9BYkZZb94_da6FTZUdYnhW6VL6BV8s7pnxGuZaUmpDv1pT8BIFZIlk4Ps9ovNatfOCiwbqjN3SOr6UZYVgSTo7eiL5UO2C8LFYvbIDqLUL47pJ4S6pGvgPgcg5FU44NUGKHS3ZDYpqJYPpEtCYsfHcffpo1Lgto8vUz2TyCBQJe9AYVrufjOnv1iAT8fzcyjXheEX8YlGZr4gqik67vYnHZLxiEu5IMBBQn=s11-no>";
		
		String aux = "";
		for ( int i = 0 ; i < msg.length ( ) ; i++ ) {
			if ( msg.charAt ( i ) == ':' ) {
				if ( msg.charAt ( i + 1 ) == 'D' ) {
					aux = aux + smile + " ";
					i += 2;
				} else if ( msg.charAt ( i + 1 ) == 'P' ) {
					aux = aux + tongue + " ";
					i += 2;
				} else if ( msg.charAt ( i + 1 ) == 'v' ) {
					aux = aux + pacman + " ";
					i += 2;
				} else if ( msg.charAt ( i + 1 ) == ')' ) {
					aux = aux + happy + " ";
					i += 2;
				} else if ( msg.charAt ( i + 1 ) == '*' ) {
					aux = aux + kiss + " ";
					i += 2; 
				} else if ( msg.charAt ( i + 1 ) == '(' ) {
					aux = aux + sad + " ";
					i += 2;
				} else {
					aux = aux + msg.charAt ( i );
				} // End nested if - else. 
			} else {
				aux = aux + msg.charAt ( i );
			} // End if - else.
		} // End for.
		
		return aux;
		
	} // End Emoticon.

} // End class.