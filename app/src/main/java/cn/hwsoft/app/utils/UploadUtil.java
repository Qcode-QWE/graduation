package cn.hwsoft.app.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Component
public class UploadUtil {

	//本机网络地址
	private static  String webPATH;
	//根目录  D:/java
	private static String basePATH;
	//图片共享的的公共区域
	//  "/images"
	private static String commonPATH;
	//  "/inspect"
	private static String inspectUploadPath;

	//  "/cover"
	private static String coverUploadPath;

	//  "/small"
	private static String inspectSmallPath;

	// /temp
	private static String inspectTempPath;

	@Value("#{file['inspectTempPath']}")
	public void setInspectTempPath(String inspectTempPath) {
		UploadUtil.inspectTempPath = inspectTempPath;
	}

	public static String getInspectTempPath() {
		return inspectTempPath;
	}

	@Value("#{file['basePATH']}")
	public void setBasePATH(String basePATH){
		UploadUtil.basePATH = basePATH;
	}
	@Value("#{file['webPATH']}")
	public void setWebPATH(String webPATH){
		UploadUtil.webPATH = webPATH;
	}
	@Value("#{file['commonPath']}")
	public void setCommonPath(String commonPath){
		UploadUtil.commonPATH = commonPath;
	}
	@Value("#{file['inspectUploadPath']}")
	public  void setInspectUploadPath(String inspectUploadPath){
		UploadUtil.inspectUploadPath = inspectUploadPath;
	}
	@Value("#{file['coverUploadPath']}")
	public void setCoverUploadPath(String coverUploadPath){
		UploadUtil.coverUploadPath = coverUploadPath;
	}
	@Value("#{file['inspectSmallPath']}")
	public void setInspectSmallPath(String inspectSmallPath){
		UploadUtil.inspectSmallPath = inspectSmallPath;
	}

	private static String[] inspectType = {"inform","sue","appeal"};

	/**
	 * 上传文件
	 * @param file
	 * @return
	 */
	public static String upload(MultipartFile file,String path) {
		//使用UUID生成一个永不重复的文件名
		String uuid = UUID.randomUUID().toString();
		//获取文件名，例如：1.jpg
		String orgFileName = file.getOriginalFilename();
		//得到文件的扩展名，例如：.jpg
		String ext= orgFileName.substring(orgFileName.lastIndexOf('.')+1);
		//得到新的文件名
		String fileName = uuid + "."+ext;
		//获取保存路径 D:/java/images/inspect
		String basePath = basePATH+commonPATH+ path;
		//返回存储路径
		//  /images/inspect/1.jpg
		String url=commonPATH+ path +"/"+fileName;
		try {
			//通过文件的绝对路径创建文件对象,将文件保存到对应路径下
			File file1  = new File(basePath);
			if (!file1 .exists()) {
				file1 .mkdirs();
			}
			//将图片存入文件夹
			File targetFile = new File(file1, fileName);
			file.transferTo(targetFile);
			return  url;
		} catch (IOException e) {
			e.printStackTrace();
			return  null;
		}
	}

	public static String getInspectPath(){
		String path = inspectUploadPath;
		return  path;
	}
	public static String getCoverPath(){
		String path = coverUploadPath;
		return  path;
	}



	/**
	 * 删除文件
	 * @param imagePath
	 */
	public static void deleteFile(String imagePath) {
		String path =  basePATH + imagePath;
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * 将存储在公共区域的图像移到/type/receipt目录下
	 * @param fileName
	 * @param type
	 * @param receipt
	 */
	public static List<String> changeFile(String fileName,int type,int receipt) {
		String[] fileNames = fileName.split(",");
		String path = commonPATH + inspectUploadPath +"/"+inspectType[type-1]+"/"+receipt;
		File pathFile = new File(basePATH+path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		// 用于存储原图地址和缩略图地址
		List<String> list = new ArrayList<>();
		int i = 1;
		StringBuffer imgBuffer = new StringBuffer();
		StringBuffer smallBuffer = new StringBuffer();
		for(String name:fileNames){
			//D:/java/images/inspect/1.jpg
			String oldFile = basePATH+name;
			File oldF = new File(oldFile);
			//先生成缩略图,再移动
			String smallName = createSmallImg(oldFile,type,receipt,i);
			//得到文件的扩展名，例如：.jpg
			String ext= oldFile.substring(oldFile.lastIndexOf('.')+1);
			String str = receipt+"-image"+i+"."+ext;
			i++;
			//移动图片
			String newFile = basePATH+path+"/"+str;
			File newF = new File(newFile);
			if(newF.exists()){
				newF.delete();
			}
			oldF.renameTo(newF);
			smallBuffer.append(smallName);
			imgBuffer.append(path+"/"+str);
			if(i!=fileNames.length+1){
				smallBuffer.append(",");
				imgBuffer.append(",");
			}
		}
		list.add(smallBuffer.toString());
		list.add(imgBuffer.toString());
		return  list;
	}


	/**
	 * 生成缩略图
	 * @param fileName
	 * @param type
	 * @param receipt
	 * @return
	 */
	public static String createSmallImg(String fileName,int type,int receipt,int i){
		//  /images/inspect/small/appeal/12345678
		String path = commonPATH + inspectUploadPath + inspectSmallPath +"/"+inspectType[type-1]+"/"+receipt;
		File pathFile = new File(basePATH+path);
		if(!pathFile.exists()){
			pathFile.mkdirs();
		}
		File oldF = new File(fileName);
		//得到文件的扩展名，例如：.jpg
		String ext= fileName.substring(fileName.lastIndexOf('.')+1);
		String str = receipt+"-image"+i+"."+ext;
		// D:/images/inspect/small/appeal/12345678/image-1.jpg
		String newFileName  = basePATH+path+"/"+str;
		File newFile = new File(newFileName);
		//缩略图所需高宽
		int towidth = 75, toheight = 100;
		//把得到的文件以字节形式转成输入流
		try {
			BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(oldF), 4096);
			//
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile), 4096);
			//将图进行缩略
			//创建一个不带透明色的BufferedImage对象
			BufferedImage tag = new BufferedImage(towidth, toheight, BufferedImage.TYPE_INT_RGB);
			//新生成结果图片
			Image image = ImageIO.read(inputStream);
			tag.getGraphics().drawImage(image, 0, 0, towidth, toheight, null);
			//以jpg格式输入新图片
			ImageIO.write(tag, ext, outputStream);
			inputStream.close();
			outputStream.flush();
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  path+"/"+str;
	}

}


