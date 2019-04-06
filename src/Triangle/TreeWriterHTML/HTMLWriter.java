package Triangle.TreeWriterHTML;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import Triangle.SyntacticAnalyzer.Token;

public class HTMLWriter {

	private String fileName;
	private String spaceBuffer;
	private Queue<String> dataQueue;
	private String lastTag;


	public HTMLWriter(String fileName) {
		this.fileName = fileName;
		dataQueue = new LinkedList<>();
		lastTag = "";
		spaceBuffer = "";
		writeHeader();
	}

	private void writeHeader(){
		this.dataQueue.add("<?xml version=\"1.0\" standalone=\"yes\"?>\n");
		this.dataQueue.add("<head>\n");
		this.dataQueue.add("  <link rel=\"stylesheet\" href=\"codeStyle.css\">\n");
		this.dataQueue.add("</head>");
		this.dataQueue.add("<body>");
		this.dataQueue.add("<div class= \"code\">");
	}

	// Draw the AST representing a complete program.
	public void writeSeparator(char pSeparator) {
		String rawText;
		switch (pSeparator){
			case '\n':
				rawText = "<br>";
				break;
			case '\t':
				rawText = "<span class=\"mtk1\">&nbsp;&nbsp;</span>";
				break;
			case '!':
				rawText = "<comment>!";
				break;
			case ' ':
				rawText = "&nbsp;";
				break;
			default:
				rawText = Character.toString(pSeparator);
		}
		this.dataQueue.add(rawText);
	}


	public void closeTag(String pTag){
		this.dataQueue.add( String.format("</%s>", pTag) );
	}

	public void write(String pText, int pKind) {
		String tag;

		if (!lastTag.equals(""))
			closeTag(lastTag);

		switch(pKind){
			case Token.IDENTIFIER:
			{
				tag = "identifier";
			}
			break;

			case Token.CHARLITERAL:
			case Token.INTLITERAL:
			{
				tag = "literal";
			}
			break;

			default:
				if (pKind < Token.WHILE)
					tag = "resword";//String.format("<common> %s </common>", pText);
				else
					tag = "identifier";
		}

		this.dataQueue.add(String.format("<%s>%s</%s>", tag, pText, tag));
		lastTag = tag;
	}

	public boolean save(){
		// Prepare the file to write

		this.dataQueue.add("</div>");
		this.dataQueue.add("</body>");

		try {
			FileWriter fileWriter = new FileWriter(fileName);

			while (!this.dataQueue.isEmpty())
			//HTML header
				fileWriter.write(this.dataQueue.remove());

			fileWriter.close();

		} catch (IOException e) {
			System.err.println("Error while creating file for print the AST");
			e.printStackTrace();
		}
		return true;
	}

}
