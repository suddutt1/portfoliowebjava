package com.ibm.tools.portfoliodb.dataloader;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CSVFileReader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*for(Map<String,String> row:readFile("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\PortfolioDBDocs\\EMP_V1.csv"))
		{
			System.out.println(row);
		}*/
		List<String> lines = readTABDeliminatedFile("C:\\Users\\SUDDUTT1\\SD_DATA\\NONPROJ\\PortfolioDBDocs\\NONOTABS.txt");
		List<Map<String,String>> readLines = readTABDiliitedLine(lines);
		System.out.println(readLines); 
	}
	
	public static List<Map<String,String>> readFile(String filePath)
	{
		List<Map<String,String>> recordList = null;
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(filePath));
			recordList = new ArrayList<Map<String,String>>();
			String headerLine = reader.readLine() ;
			String[] headerFieldNames  = parseCSVLine(headerLine);
			//TODO:TO add header field name validation
			String dataLine = null;
			while((dataLine = reader.readLine())!=null)
			{
				String[] dataContent = parseCSVLine(dataLine);
				if(dataContent.length>0 && dataContent.length >= headerFieldNames.length)
				{
					Map<String,String> dataRowMap = new LinkedHashMap<String, String>(headerFieldNames.length);
					for(int colIndex =0;colIndex< headerFieldNames.length;colIndex++)
					{
						dataRowMap.put(headerFieldNames[colIndex],dataContent[colIndex]);
					}
					recordList.add(dataRowMap);
				}
			}
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try{
			if(reader!=null)
			{
				reader.close();
			}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return recordList;
	}
	
	private static String[] parseCSVLine(String line)
	{
		String[] textWithQuotes = line.split(",");
		String[] textContents = new String[textWithQuotes.length];
		for(int index=0;index<textWithQuotes.length;index++)
		{
			String temp = textWithQuotes[index].trim();
			if(textWithQuotes[index].startsWith("\""))
			{
				temp = temp.substring(1);
			}
			if(textWithQuotes[index].endsWith("\"")&& temp.length()>0)
			{
				temp = temp.substring(0, temp.length()-1);
			}
			textContents[index] = temp;
		}
		return textContents;
	}
	
	public static List<Map<String,String>> readTABDiliitedLine(List<String> lines)
	{
		//Assuming the first line is the header.
		String[] header = parseTABDeliminatedLine(lines.get(0));
		List<Map<String,String>> rowsRead = new ArrayList<Map<String,String>>(lines.size()-1);
		for(String line: lines.subList(1, lines.size()))
		{
			//System.out.println(line);
			rowsRead.add(parseTABDeliminatedLine(line,header));
			
		}
		return rowsRead;
	}
	private static String[] parseTABDeliminatedLine(String line)
	{
		String[] textWithQuotes = line.split("\t");
		for(int index=0;index<textWithQuotes.length;index++)
		{
			textWithQuotes[index] = textWithQuotes[index].trim();
		}
		return textWithQuotes;
	}
	private static Map<String,String> parseTABDeliminatedLine(String line,String[] headers)
	{
		Map<String,String> fields = new LinkedHashMap<String, String>();
		String[] textWithQuotes = line.split("\t");
		if(headers.length >= textWithQuotes.length)
		{
			for(int index=0;index<textWithQuotes.length;index++)
			{
				fields.put(headers[index].trim(), textWithQuotes[index].trim());
			}
		}
		else 
		{
			for(int index=0;index<headers.length;index++)
			{
				fields.put(headers[index].trim(), textWithQuotes[index].trim());
			}
		}
		
		return fields;
	}

	
	public static List<String> readTABDeliminatedFile(String filePath)
	{
		List<String> lineList = new ArrayList<String>();
		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new FileReader(filePath));
			String line = null;
			StringBuffer continuingLine = new StringBuffer();
			boolean isLastLineTerminated = true;
			while((line=reader.readLine())!=null)
			{
				isLastLineTerminated = isLineTerminated(line,isLastLineTerminated);
				//System.out.println(isLastLineTerminated);
				continuingLine.append(line);
				if(isLastLineTerminated)
				{
					lineList.add(continuingLine.toString());
					continuingLine.setLength(0);
				}
			}
			if(continuingLine.length()>0)
			{
				lineList.add(continuingLine.toString());
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try{
			if(reader!=null)
			{
				reader.close();
			}
			}catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		return lineList;
	}

	private static boolean isLineTerminated(String line,boolean isLastLineTerminated)
	{
		int count=(isLastLineTerminated?0:1); 
		int pos = line.indexOf("\"",0);
		while(pos!=-1)
		{
			pos = line.indexOf("\"",pos+1);
			count++;
		}
		return (count%2==0? true:false);
	}
}
