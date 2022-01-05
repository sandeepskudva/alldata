package Research;


import java.io.File;
import net.sourceforge.tess4j.*;

public class extract {
	
	public static void imageextract(String imagepath)
	{
       File imageFile = new File(imagepath);
        
        ITesseract instance = new Tesseract();        // JNA Interface Mapping
        instance.setDatapath("E:\\TSG\\Selenium\\OCR old");   
        String result;
        try {
            result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}

    public static void main(String[] args) {
    	
    	imageextract("E:\\TSG\\image1.jpg");
    }
}