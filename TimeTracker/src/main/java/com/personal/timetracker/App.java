package com.personal.timetracker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App
{
	static final String FILE_PATH = "C:/cygwin64/home/Michael/dev/ParallelRayTracer/log.csv";

	public static void main(String[] args)
	{
		System.out.println("Welcome to Time tracker!");
		if (args.length >= 1)
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
			
			String message = "";
			final Date now = Calendar.getInstance().getTime();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			Date today = new Date();
			try
			{
				today = formatter.parse(formatter.format(today));
			}
			catch(Exception ex)
			{
			}
			
			switch (command)
			{
				case START:
					if (args.length > 1)
						message = args[1];
					else
					{
						System.out.println("Start command requires a task.");
						return;
					}
				case END:
					write(command, now, message);
					break;
				case VIEW_DAY:
					hoursForRange(today, now, null);
					break;
				case BUILD_REPORT:
					Date lastReportDate = new Date();
					buildReport(lastReportDate);
					break;
				default:
					throw new UnsupportedOperationException();
			}
		}
	}

	public static List<String> readLogFile()
	{
		BufferedReader br = null;
		List<String> fileContents = new ArrayList<String>();
		try
		{
			String sCurrentLine;

			br = new BufferedReader(new FileReader(FILE_PATH));

			while ((sCurrentLine = br.readLine()) != null)
			{
				fileContents.add(sCurrentLine);
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
		return fileContents;
	}

	private static void write(Command command, Date time, String task)
	{
		try
		{
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			List<String> content = readLogFile();
			
			switch(command)
			{
				case START:
					content.add(task +"," + dateFormat.format(time));
					break;
				case END:
					String last = content.get(content.size() - 1);
					last += "," + dateFormat.format(time);
					content.set(content.size() - 1, last);
					break;
			}

			File file = new File(FILE_PATH);

			// if file doesnt exists, then create it
			if (!file.exists())
			{
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (String string : content)
			{
				bw.write(string + "\n");
			}
			
			bw.close();

			System.out.println("Done");

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	private static void hoursForRange(Date start, Date end, List<String> times)
	{
		System.out.println(start.toString() + " - " + end.toString());
	}
	private static void buildReport(Date lastReportDate)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
