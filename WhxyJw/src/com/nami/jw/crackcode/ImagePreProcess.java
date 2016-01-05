package com.nami.jw.crackcode;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import com.nami.jw.util.JwUtils;


public class ImagePreProcess {

private static Map<BufferedImage, String> trainMap = null;
	
	//public static String srcPath = "WebRoot\\images\\ctrackcode\\srcimg\\";
//	public final static String srcPath = new File("").getAbsolutePath()+"\\WebRoot\\images\\ctrackcode\\srcimg\\";
//	public final static String trainPath = new File("").getAbsolutePath()+"\\WebRoot\\images\\ctrackcode\\trainimg\\";
		
	public  static String srcPath = "C:\\Develop\\JavaWeb\\apache-tomcat-6.0.44\\webapps\\WhxyJw\\images\\ctrackcode\\srcimg\\";
	public  static String trainPath = "C:\\Develop\\JavaWeb\\apache-tomcat-6.0.44\\webapps\\WhxyJw\\images\\ctrackcode\\trainimg\\";

	/**
	 * 从教务网的下载验证码
	 * @param url
	 * @param imgName  验证码的文件名，我一般传学生的学号+".png"
	 * @return
	 */
	public static String downloadImage(String url, String imgName) {
		CloseableHttpClient httpClient = JwUtils.getHttpClient();
		HttpGet getMethod = new HttpGet(url);
		HttpResponse response = null;
		try {
			response = httpClient.execute(getMethod);
			if("HTTP/1.1 200 OK".equals(response.getStatusLine().toString())){
				HttpEntity entity = response.getEntity();
				
				InputStream is = entity.getContent();
				OutputStream os = new FileOutputStream(new File(srcPath+imgName));
				int length = -1;
				byte[] bytes = new byte[1024];
				while((length = is.read(bytes)) != -1){
					os.write(bytes, 0, length);
				}
				os.close();
				return srcPath+imgName;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int isBlue(int colorInt) {  
        Color color = new Color(colorInt);  
        int rgb = color.getRed() + color.getGreen() + color.getBlue();
        if (rgb == 153) {  
            return 1;  
        }  
        return 0;  
    }  
	
	public static int isBlack(int colorInt) {
		Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= 100) {
			return 1;
		}
		return 0;
	}


	public static BufferedImage removeBackgroud(String picFile)
			throws Exception {
		BufferedImage img = ImageIO.read(new File(picFile));  
        img = img.getSubimage(5, 1, img.getWidth()-5, img.getHeight()-2); 
        img = img.getSubimage(0, 0, 50, img.getHeight());
        int width = img.getWidth();  
        int height = img.getHeight();  
        for(int x=0; x<width; x++){
        	for(int y=0; y<height; y++){
        		if(isBlue(img.getRGB(x, y)) == 1){
        			img.setRGB(x, y, Color.BLACK.getRGB());
        		}else{
        			img.setRGB(x, y, Color.WHITE.getRGB());
        		}
        	}
        }
        return img;  
	}
	
	public static List<BufferedImage> splitImage(BufferedImage img)
			throws Exception {
		List<BufferedImage> subImgs = new ArrayList<BufferedImage>();
		int width = img.getWidth()/4;
		int height = img.getHeight();
		subImgs.add(img.getSubimage(0, 0, width, height));
		subImgs.add(img.getSubimage(width, 0, width, height));
		subImgs.add(img.getSubimage(width*2, 0, width, height));
		subImgs.add(img.getSubimage(width*3, 0, width, height));
		return subImgs;
	}

	public static Map<BufferedImage, String> loadTrainData() throws Exception {
		if (trainMap == null) {
			Map<BufferedImage, String> map = new HashMap<BufferedImage, String>();
			File dir = new File(trainPath);
			File[] files = dir.listFiles();
			for (File file : files) {
				map.put(ImageIO.read(file), file.getName().charAt(0) + "");
			}
			trainMap = map;
		}
		return trainMap;
	}

	public static String getSingleCharOcr(BufferedImage img,
			Map<BufferedImage, String> map) {
		String result = "#";
		int width = img.getWidth();
		int height = img.getHeight();
		int min = width * height;
		for (BufferedImage bi : map.keySet()) {
			int count = 0;
			if (Math.abs(bi.getWidth()-width) > 2)
				continue;
			int widthmin = width < bi.getWidth() ? width : bi.getWidth();
			int heightmin = height < bi.getHeight() ? height : bi.getHeight();
			Label1: for (int x = 0; x < widthmin; ++x) {
				for (int y = 0; y < heightmin; ++y) {
					if (isBlack(img.getRGB(x, y)) != isBlack(bi.getRGB(x, y))) {
						count++;
						if (count >= min)
							break Label1;
					}
				}
			}
			if (count < min) {
				min = count;
				result = map.get(bi);
			}
		}
		return result;
	}

	public static String getAllOcr(String file) throws Exception {
		BufferedImage img = removeBackgroud(file);
		List<BufferedImage> listImg = splitImage(img);
		Map<BufferedImage, String> map = loadTrainData();
		String result = "";
		for (BufferedImage bi : listImg) {
			result += getSingleCharOcr(bi, map);
		}
		return result;
	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		downloadImage("http://jw1.hustwenhua.net/(03rmlx552qwi20j0n1dtcm55)/CheckCode.aspx", "130103021125.png");
		String text = getAllOcr(srcPath+"130103021125.png"); //这里是要验证的图片路径
		System.out.println("验证码是："+text);
	}
}
