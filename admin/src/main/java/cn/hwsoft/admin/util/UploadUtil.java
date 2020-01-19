package cn.hwsoft.admin.util;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.UUID;

/**
 * 文件上传工具类
 */
@Component
public class UploadUtil {

	//本机文件网络根目录
	private static String webPATH;
	//文件存放的基本目录
	private static String basePATH;
	//文件存放的根目录
	private static String commonPATH;
	//文件存放的具体目录
	private static String uploadPATH;

	@Value("#{file['webPATH']}")
	public void setWebPATH(String webPATH){
		UploadUtil.webPATH = webPATH;
	}

	@Value("#{file['basePATH']}")
	public void setBasePATH(String basePATH){
		UploadUtil.basePATH = basePATH;
	}
	@Value("#{file['commonPath']}")
	public void setCommonPATH(String commonPath){
		UploadUtil.commonPATH = commonPath;
	}

	public static void setUploadPATH(String uploadPATH){
		UploadUtil.uploadPATH = uploadPATH;
	}

	/**
	 * 处理文件上传
	 * @param file
	 * @return  123.png
	 */
	public static String upload(MultipartFile file) {
		//使用UUID生成一个永不重复的文件名
		String uuid = UUID.randomUUID().toString();
		//获取文件名，例如：1.jpg
		String orgFileName = file.getOriginalFilename();
		//得到文件的扩展名，例如：.jpg
		String ext= orgFileName.substring(orgFileName.lastIndexOf('.')+1);
		//得到新的文件名
		String fileName = uuid + "."+ext;
		//获取保存路径
		String dirPath = basePATH+commonPATH+uploadPATH;
		//获取显示路径
		String showPath=webPATH+commonPATH+uploadPATH;
		System.out.println(dirPath);
		try {
			//通过文件的绝对路径创建文件对象,将文件保存到对应路径下
			File targetFile = new File(dirPath);
			if (!targetFile.exists()) {
				targetFile.mkdirs();
			}
			//读取图片
			BufferedInputStream in = new BufferedInputStream(file.getInputStream());
			//字节流转图片对象
			Image bi = ImageIO.read(in);
			//构建图片流 按照16:9的比例 设置宽高 240*135
			//宽240 高135 我这里直接写死了 也可以写成参数形式的。
			BufferedImage tag = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
			//绘制改变尺寸后的图
			tag.getGraphics().drawImage(bi, 0, 0, 800, 600, null);
			//输出流
			BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(dirPath+"//"+fileName));
			ImageIO.write(tag, ext,out);
			in.close();
			out.close();
//			将上传的文件复制到保存文件夹目录
//			file.transferTo(targetFile);
//			FileUtils.writeByteArrayToFile(targetFile, file.getBytes());
//			返回文件相对路径
			return showPath+"/"+fileName;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 删除文件
	 * @param imagePath
     */
	public static void deleteFile(ServletContext servletContext, String imagePath) {
		String path = servletContext.getRealPath("/") + imagePath;
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}
}
























