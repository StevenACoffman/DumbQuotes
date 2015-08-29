package edu.umich.lsa;

/**
 * Replaces Windows code page 1252 "Smart Quotes" with straight quotes (" and ').
 * You can prevent replacement of specific quotes by escaping them with a
 * backslash, e.g. \".
 * Uses ideas from Demoronizer perl script here:
 * http://www.fourmilab.ch/webtools/demoroniser/
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.lang.String;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;


public class DumbQuotes
{

   public static void main(String[] args)
   {

		// in JDK 1.4, defaultEncodingName will typically be "Cp1252"
		// In an Applet, this requires signing for privilege.
		String defaultEncodingName = System.getProperty( "file.encoding" );
		System.out.println("file.encoding:"+defaultEncodingName);
		// in JDK 1.5+, will typically be "windows-1252"
		// First, get the Charset/encoding then convert to String.
		defaultEncodingName = Charset.defaultCharset().name();
		System.out.println("Charset.defaultCharset:"+defaultEncodingName);

		// I'm told this circumlocution has the nice property you can even use
		// it in an unsigned Applet.
		//String defaultEncodingName = new OutputStreamWriter( System.out ).getEncoding();


     FileOutputStream fos = null;
     OutputStreamWriter osw = null;
     BufferedWriter bw = null;
     PrintWriter pw = null;
     FileInputStream fis = null;
     InputStreamReader isr = null;
     BufferedReader br = null;

     String sUni = null;

     try
     { 
       // read a Unicode string from a file
       //fis = new FileInputStream( "javauni.txt" );
       //isr = new InputStreamReader( fis, "UTF-8" );
       //br = new BufferedReader( isr );
			String wholeFileString="\u0091 \u0092 \u0093 \u0094 ";
       //String wholeFileString = readFileAsString("testdata.xml");
       //wholeFileString = flunk(wholeFileString);

       // write a UniCode string to a file
       fos = new FileOutputStream( "javauni.xml" );
       osw = new OutputStreamWriter( fos, "UTF-8" );
       bw = new BufferedWriter( osw );
       pw = new PrintWriter(bw);

       pw.write( wholeFileString);
       pw.flush();
       pw.close();

/*

      while( true )
       {
         try
         {
           sUni = br.readLine();
           if( sUni == null ) { break; } 
           System.out.println( "br value: " + sUni );
         }
         catch( Exception ae )
         {
           ae.printStackTrace();
           System.out.println("ae: " + ae );
           break;
         }
       } // end while
       br.close();
       */
     }
     catch( Exception e )
     {
			 e.printStackTrace();
       System.out.println("e: " + e );
     }

   } // end main



    /**
     * Windows-1252 uses characters that are incompatible with both UTF-8 and Latin-1.
     * This function maps those into plausible substitutes.
     *
     * This has the effect of replacing smart quotes replaced with straight quotes.
     */
	public static String flunk(String value)
	{
		// map incompatible non-ISO characters into plausible
		// substitutes

		value= value.replaceAll( "\\u0082", "\'" );	// 130 single Low-9 Quotation Mark - unicode 0x201a
		value= value.replaceAll( "\\u0084", "\"" );	// 132 Double Low-9 Quotation Mark - unicode 0x201e
		value= value.replaceAll( "\\u0091", "\'" );	// 145 left single quotation mark - unicode 0x2018
		value= value.replaceAll( "\\u0092", "\'" );	// 146 right single quotation mark - 0x2019

		value= value.replaceAll( "\\u0093", "\"" );	// 147 Left Double Quotation Mark - unicode 0x201c	
		value= value.replaceAll( "\\u0094", "\"" );	// 148 Right Double Quotation Mark - unicode 0x201d	



		value= value.replaceAll( "\\u0080","&euro;");	//128	 Euro Sign "&euro;" - unicode 0x20ac
		value= value.replaceAll( "\\u0083","&fnof;");	//131  Latin Small Letter F With Hook "&fnof;" - unicode 0x0192
		value= value.replaceAll( "\\u0085","...");	//133  Horizontal Ellipsis "..." - unicode 0x2026
		value= value.replaceAll( "\\u0086","&dagger;");	//134 Dagger "&dagger;" - unicode 0x2020
		value= value.replaceAll( "\\u0087","&Dagger;");	//135 Double Dagger "&Dagger;" - unicode 0x2021
		value= value.replaceAll( "\\u0088","&circ;");	//136 Modifier Letter Circumflex Accent "&circ;" - unicode 0x02c6
		value= value.replaceAll( "\\u0089","&permil;");	//137 Per Mille Sign "&permil;" - unicode 0x2030
		value= value.replaceAll( "\\u008a","&Scaron;");	//138 Latin Capital Letter S With Caron "&Scaron;" - unicode 0x0160
		value= value.replaceAll( "\\u008b","&lsaquo;");	//139 Single Left-Pointing Angle Quotation Mark "&lsaquo;" - unicode 0x2039
		value= value.replaceAll( "\\u008c","&OElig;");	//140 Latin Capital Ligature Oe "Oe" - unicode 0x0152
		value= value.replaceAll( "\\u008e","&#x17d;");	//142 Latin Capital Letter Z With Caron "&#x17d;" - unicode 0x017d
		value= value.replaceAll( "\\u0095","&bull;");	//149 Bullet "*" - unicode 0x2022
		value= value.replaceAll( "\\u0096","-");	//150 En Dash "-" - unicode 0x2013
		value= value.replaceAll( "\\u0097","--");	//151 Em Dash "--" - unicode 0x2014
		value= value.replaceAll( "\\u0098","~");	//152 Small Tilde "~" - unicode 0x02dc
		value= value.replaceAll( "\\u0099","&trade;");	//153 Trade Mark Sign "&trade;" - unicode 0x2122
		value= value.replaceAll( "\\u009a","&scaron;");	//154 Latin Small Letter S With Caron - unicode 0x0161
		value= value.replaceAll( "\\u009b","&rsaquo;");	//155 Single Right-Pointing Angle Quotation Mark "&rsaquo;" - unicode 0x203a
		value= value.replaceAll( "\\u009c","&oelig;");	//156 Latin Small Ligature Oe "&oelig;" - unicode 0x0153
		return value;
	}
	private static String readFileAsString(String filePath) throws java.io.IOException {
			//byte[] buffer = new byte[(int) new File(filePath).length()];
			char[] buffer = new char[(int) new File(filePath).length()];
			BufferedReader f = null;
			try {
					//f = new BufferedInputStream(new FileInputStream(filePath));
					f = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
					f.read(buffer);
					
			} finally {
					if (f != null)
					try 
					{ f.close();
					}
					 catch (IOException ignored) {
					ignored.printStackTrace();
					}
			}
			return new String(buffer);
	}
}


