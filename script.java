import java.io.*;
import java.util.ArrayList;

class Script {

	static String HEADER1 = "<!DOCTYPE html>\r\n" + //
	"<html lang=\"en\">\r\n" + //
	"<head>\r\n" + //
	"    <meta charset=\"UTF-8\">\r\n" + //
	"	<link rel=\"stylesheet\" href=\"../styles.css\">\r\n" + //
	"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
	"    <title>";

static String HEADER2 = "</title>\r\n" + //
	"</head>\r\n" + //
	"<body>\r\n";

static String FOOTER = "\r\n" +
	"</body>\r\n" + //
	"</html>\r\n";






	public static void main(String[] args) throws Exception {

		File contentDir = new File("content");
		File outputDir = new File("output");
		File indexDotMd = new File(contentDir, "index.md");

		if (!contentDir.exists()) {
			contentDir.mkdirs();
		}

		if (!outputDir.exists()) {
			outputDir.mkdirs();
		}

		if (!indexDotMd.exists()) {
			indexDotMd.createNewFile();

		try (FileWriter fw = new FileWriter(indexDotMd);
			BufferedWriter bw = new BufferedWriter(fw);) {
			bw.write("---\n");
			bw.write("title: Home\n");
			bw.write("date: 2024-02-20\n");
			bw.write("---\n\n");
			bw.write("Hello World\n\n");
			bw.write("This is a test.\n");
			bw.close();
		}

		catch(IOException e) {
			System.out.println("Error writing defaults to file.");
		}

		}


		File[] files = contentDir.listFiles();

        for (File file : files) {
            String title = fetchTitle(file);
            ArrayList<String> bodyLines = fetchBody(file);
            String body = String.join("\n", bodyLines);
            String html = HEADER1 + title + HEADER2 + body + FOOTER;
            String outputFileName = file.getName().replaceFirst("[.][^.]+$", "") + ".html";
            File outputFile = new File(outputDir, outputFileName);
            try (FileWriter writer = new FileWriter(outputFile);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
                bufferedWriter.write(html);
            } catch (IOException e) {
                System.err.println("Error writing to file: " + outputFile.getName());
                e.printStackTrace();
            }
        }
    }

    static String fetchTitle(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("title:")) {
                    return line.substring(6).trim();
                }
            }
        }
        return "Untitled";
    }


	static ArrayList fetchBody(File file) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			ArrayList hasBody= new ArrayList<String>();

			String line = "";
			line = reader.readLine();

			while (line != null) {
				if (line.startsWith("---") ) {
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
					line = reader.readLine();
				}

				else {
					line = reader.readLine();

					if (line != null) {
					hasBody.add(line);
					}
				}
				
			}

			return hasBody;
		}
	}


}