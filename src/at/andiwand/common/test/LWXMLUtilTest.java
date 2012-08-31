package at.andiwand.common.test;

import java.io.File;
import java.io.FileReader;

import javax.swing.JFileChooser;

import at.andiwand.common.lwxml.LWXMLUtil;
import at.andiwand.common.lwxml.path.LWXMLPath;


public class LWXMLUtilTest {
	
	public static void main(String[] args) throws Throwable {
		JFileChooser fileChooser = new JFileChooser();
		int option = fileChooser.showOpenDialog(null);
		
		if (option == JFileChooser.CANCEL_OPTION) return;
		
		File file = fileChooser.getSelectedFile();
		FileReader reader = new FileReader(file);
		
		System.out.println(LWXMLUtil.getAttributeValue(reader, new LWXMLPath(
				"/office:document-meta/office:meta/meta:document-statistic"),
				"meta:table-count"));
	}
	
}