import java.io.*;
import java.util.ArrayList;

public class Blogmaker {

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
		createFileStyleCSS();
		
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


	static void createFileStyleCSS() throws Exception {
		String cssContent = "/*parts of css file is from hackclub.css. Link to that: https://css.hackclub.com */\n\n" +
		":root {\n" +
		"    --darker: #121217;\n" +
		"    --dark: #17171d;\n" +
		"    --darkless: #252429;\n" +
		"    --black: #1f2d3d;\n" +
		"    --steel: #273444;\n" +
		"    --slate: #3c4858;\n" +
		"    --muted: #8492a6;\n" +
		"    --smoke: #e0e6ed;\n" +
		"    --snow: #f9fafc;\n" +
		"    --white: #ffffff;\n" +
		"    --red: #ec3750;\n" +
		"    --orange: #ff8c37;\n" +
		"    --yellow: #f1c40f;\n" +
		"    --green: #33d6a6;\n" +
		"    --cyan: #5bc0de;\n" +
		"    --blue: #338eda;\n" +
		"    --purple: #a633d6;\n" +
		"    --text: var(--black);\n" +
		"    --background: var(--white);\n" +
		"    --elevated: var(--white);\n" +
		"    --sheet: var(--snow);\n" +
		"    --sunken: var(--smoke);\n" +
		"    --border: var(--smoke);\n" +
		"    --primary: #ec3750;\n" +
		"    --secondary: #8492a6;\n" +
		"    --accent: #5bc0de;\n" +
		"    --twitter: #1da1f2;\n" +
		"    --facebook: #3b5998;\n" +
		"    --instagram: #e1306c;\n" +
		"    --breakpoint-xs: 32em;\n" +
		"    --breakpoint-s: 48em;\n" +
		"    --breakpoint-m: 64em;\n" +
		"    --breakpoint-l: 96em;\n" +
		"    --breakpoint-xl: 128em;\n" +
		"    --spacing-0: 0px;\n" +
		"    --spacing-1: 4px;\n" +
		"    --spacing-2: 8px;\n" +
		"    --spacing-3: 16px;\n" +
		"    --spacing-4: 32px;\n" +
		"    --spacing-5: 64px;\n" +
		"    --spacing-6: 128px;\n" +
		"    --spacing-7: 256px;\n" +
		"    --spacing-8: 512px;\n" +
		"    --font-1: 12px;\n" +
		"    --font-2: 16px;\n" +
		"    --font-3: 20px;\n" +
		"    --font-4: 24px;\n" +
		"    --font-5: 32px;\n" +
		"    --font-6: 48px;\n" +
		"    --font-7: 64px;\n" +
		"    --font-8: 96px;\n" +
		"    --font-9: 128px;\n" +
		"    --font-10: 160px;\n" +
		"    --font-11: 192px;\n" +
		"    --line-height-limit: 0.875;\n" +
		"    --line-height-title: 1;\n" +
		"    --line-height-heading: 1.125;\n" +
		"    --line-height-subheading: 1.25;\n" +
		"    --line-height-caption: 1.375;\n" +
		"    --line-height-body: 1.5;\n" +
		"    --font-weight-body: 400;\n" +
		"    --font-weight-bold: 700;\n" +
		"    --font-weight-heading: var(--font-weight-bold);\n" +
		"    --letter-spacing-title: -0.009em;\n" +
		"    --letter-spacing-headline: 0.009em;\n" +
		"    --size-wide-plus: 2048px;\n" +
		"    --size-wide: 1536px;\n" +
		"    --size-layout-plus: 1200px;\n" +
		"    --size-layout: 1024px;\n" +
		"    --size-copy-ultra: 980px;\n" +
		"    --size-copy-plus: 768px;\n" +
		"    --size-copy: 680px;\n" +
		"    --size-narrow-plus: 600px;\n" +
		"    --size-narrow: 512px;\n" +
		"    --radii-small: 4px;\n" +
		"    --radii-default: 8px;\n" +
		"    --radii-extra: 12px;\n" +
		"    --radii-ultra: 16px;\n" +
		"    --radii-circle: 99999px;\n" +
		"    --shadow-text: 0 1px 2px rgba(0, 0, 0, 0.25), 0 2px 4px rgba(0, 0, 0, 0.125);\n" +
		"    --shadow-small: 0 1px 2px rgba(0, 0, 0, 0.0625),\n" +
		"      0 2px 4px rgba(0, 0, 0, 0.0625);\n" +
		"    --shadow-card: 0 4px 8px rgba(0, 0, 0, 0.125);\n" +
		"    --shadow-elevated: 0 1px 2px rgba(0, 0, 0, 0.0625),\n" +
		"      0 8px 12px rgba(0, 0, 0, 0.125);\n" +
		"}\n" +
		"\n" +
		"body {\n" +
		"    font-family: Arial, sans-serif;\n" +
		"    margin: 0 10px; /* Added small margin on both sides */\n" +
		"    padding: 0;\n" +
		"    background-color: #f5f5f5;\n" +
		"    color: #333;\n" +
		"    line-height: 1.6;\n" +
		"}\n" +
		"\n" +
		"h2, h3 {\n" +
		"    color: #007bff;\n" +
		"}\n" +
		"\n" +
		"a {\n" +
		"    color: #007bff;\n" +
		"    text-decoration: none;\n" +
		"}\n" +
		"\n" +
		"a:hover {\n" +
		"    text-decoration: underline;\n" +
		"}\n" +
		"\n" +
		".container {\n" +
		"    max-width: 50%;\n" +
		"    margin: auto;\n" +
		"    padding: 20px;\n" +
		"    background-color: #fff;\n" +
		"    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\n" +
		"    border-radius: 5px;\n" +
		"    margin-top: 50px;\n" +
		"}\n" +
		"\n" +
		"code {\n" +
		"  font-family: \"SF Mono\", \"Roboto Mono\", Menlo, Consolas, monospace;\n" +
		"  font-size: inherit;\n" +
		"  color: var(--purple);\n" +
		"  background: var(--sunken);\n" +
		"  overflow: auto;\n" +
		"  border-radius: var(--radii-small);\n" +
		"  margin-left: var(--spacing-1);\n" +
		"  margin-right: var(--spacing-1);\n" +
		"  padding-left: var(--spacing-1);\n" +
		"  padding-right: var(--spacing-1);\n" +
		"}\n" +
		"\n" +
		"br {\n" +
		"  -webkit-user-select: none; /* Safari */\n" +
		"  -ms-user-select: none; /* IE 10 and IE 11 */\n" +
		"  user-select: none; /* Standard syntax */\n" +
		"}";

			String fileName = "styles.css";
			File file = new File(fileName);
	
			if (!file.exists()) {
				try (FileWriter writer = new FileWriter(file)) {
					writer.write(cssContent);
					System.out.println("Successfully created and wrote CSS to " + fileName);
				} catch (IOException e) {
					System.out.println("An error occurred while creating the file.");
					e.printStackTrace();
				}
			} else {
				System.out.println("File " + fileName + " already exists. No action taken.");
			}
		}


}