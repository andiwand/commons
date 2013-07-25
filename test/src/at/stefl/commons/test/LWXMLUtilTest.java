package at.stefl.commons.test;

import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import at.stefl.commons.lwxml.LWXMLUtil;
import at.stefl.commons.lwxml.path.LWXMLPath;

public class LWXMLUtilTest {

    public static void main(String[] args) throws Throwable {
	JFileChooser fileChooser = new JFileChooser();
	int option = fileChooser.showOpenDialog(null);

	if (option == JFileChooser.CANCEL_OPTION)
	    return;

	File file = fileChooser.getSelectedFile();
	FileReader reader = new FileReader(file);

	System.out.println(LWXMLUtil.parseAttributeValue(reader, new LWXMLPath(
		"/office:document-meta/office:meta/meta:document-statistic"),
		"meta:table-count"));
    }

}