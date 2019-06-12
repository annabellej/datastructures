import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class FileUtil
{
	public static Iterator<String> loadFile(String fileName)
	{
		try
		{
			Scanner in = new Scanner(new File(fileName));
			List<String> list = new ArrayList<>();
			while (in.hasNextLine())
				list.add(in.nextLine());
			in.close();
			return list.iterator();
		}
		catch(FileNotFoundException e)
		{
			throw new RuntimeException(e);
		}
	}

	public static void saveFile(String fileName, Iterator<String> data)
	{
		try
		{
			PrintWriter out = new PrintWriter(
				new FileWriter(fileName), true);
			while (data.hasNext())
				out.println(data.next());
			out.close();
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}
}