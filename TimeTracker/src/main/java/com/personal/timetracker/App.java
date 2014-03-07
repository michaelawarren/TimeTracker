package com.personal.timetracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Hello world!
 *
 */
public class App
{

	public static void main(String[] args)
	{
		System.out.println("Welcome to Time tracker!");
		if (args.length == 1)
		{
			Command command = null;
			System.out.println(args[0]);
			try
			{
				command = Command.valueOf(args[0]);
			}
			catch (Exception ex)
			{
				System.out.println("Invalid Command");
				return;
			}

			switch (command)
			{
				case START:
				case END:
					write(command, Calendar.getInstance().getTime());
					break;
				case LOG_TIME:
				case BUILD_REPORT:
				case VIEW_LOG:
				default:
					throw new UnsupportedOperationException();
			}
		}
	}

	public static void readFile()
	{
		BufferedReader br = null;

		try
		{
			String sCurrentLine;

			br = new BufferedReader(new FileReader("testing.txt"));

			while ((sCurrentLine = br.readLine()) != null)
			{
				System.out.println(sCurrentLine);
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (br != null)
				{
					br.close();
				}
			}
			catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}

	private static void write(Command command, Date time)
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			String content = command +", " + dateFormat.format(time) + "\n";

			File file = new File("C:/cygwin64/home/Michael/dev/log.csv");

			// if file doesnt exists, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(content);
			bw.close();

			System.out.println("Done");

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
