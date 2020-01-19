package cn.hwsoft.admin.util;

import cn.hwsoft.wisdom.core.domain.*;
import cn.hwsoft.wisdom.core.utils.TimeUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: court
 * @description: 文件操作的工具类
 * @author: QEcode
 * @create: 2019-07-21 08:54
 **/
@Component
public class FileUtil {

    //本机网络地址
    private static  String webPATH;
    //根目录  D:/java
    private static String basePATH;
    //图片共享的的公共区域
    //  "/images"
    private static String commonPATH;
    //  "/inspect"
    private static String inspectUploadPath;
    //  "/zip"
    private static String zipPATH;

    //word文档
    // "doc/appealTemp.doc"
    private static String appealWordTemlPATH;
    private static String informWordTemlPATH;
    private static String sueWordTemlPATH;
    private static String wordPATH;

    private static String[] PROCESSSTATUS = {"已提交","已受理","已完结"};


    @Value("#{file['basePATH']}")
    public void setBasePATH(String basePATH){
        FileUtil.basePATH = basePATH;
    }
    @Value("#{file['webPATH']}")
    public void setWebPATH(String webPATH){
        FileUtil.webPATH = webPATH;
    }
    @Value("#{file['commonPath']}")
    public void setCommonPath(String commonPath){
        FileUtil.commonPATH = commonPath;
    }
    @Value("#{file['zipPath']}")
    public  void setZipPath(String zipPath){
        FileUtil.zipPATH = zipPath;
    }
    @Value("#{file['inspectUploadPath']}")
    public  void setInspectUploadPath(String inspectUploadPath){
        FileUtil.inspectUploadPath = inspectUploadPath;
    }
    @Value("#{file['appealWordTemlPath']}")
    public void setAppealWordTeml(String appealWordTemlPath){
        FileUtil.appealWordTemlPATH = appealWordTemlPath;
    }
    @Value("#{file['sueWordTemlPath']}")
    public void setSueWordTemlPath(String sueWordTemlPath){
        FileUtil.sueWordTemlPATH = sueWordTemlPath;
    }
    @Value("#{file['informWordTemlPath']}")
    public void setInformWordTemlPath(String informWordTemlPath){
        FileUtil.informWordTemlPATH = informWordTemlPath;
    }
    @Value("#{file['wordPath']}")
    public void setWordPath(String wordPath){
        FileUtil.wordPATH = wordPath;
    }

    /**
     * 将文件的地址转为本机地址
     * @param file
     * @return
     */
    public  static  String path(String file){
        //   /images/inspect/inform/47368541/47368541-image1.jpg 转为 D:/java/images/inspect/appeal/12345678/nav_c1.png
        String fileName = null;
        if(file!=null && file.length()>0){
            fileName = basePATH + file;
            return fileName;
        }
        return null;
    }
    /**
     * 将文件的本机地址转为网络地址
     * @param file
     * @return
     */
    public  static  String WebPath(String file){
        //    /inspect/appeal/12345678/nav_c1.png
        String fileName = file;
        return fileName;
    }

    /**
     * 判断文件是否存在
     * @param fileName
     * @return
     */
    public static boolean exits(String fileName){
        //D:/java/images/inspect/appeal/12345678/nav_c1.png
        //判断文件是否存在
        File file = new File(fileName);
        if(file.exists()){
            return true;
        }
        return false;
    }

    /**
     * 单文件下载
     * @param fileName
     * @return
     */
    public static DownloadFileInfo downloadFileInfo(String fileName){
        if(exits(fileName)){
            File file = new File(fileName);
            String name = file.getName();
            return new DownloadFileInfo(name, file);
        }
        return null;
    }

    /**
     * 多文件下载,将多个文件打包成zip文件,文件名为  回执单号.zip
     * @param fileNames
     * @return
     */
    public static String zip(List<String> fileNames, int receipt,String path) {
        String zipath = "";
        if(path!=null){
            zipath = basePATH+ commonPATH + zipPATH +zipPATH;
        }else{
            zipath = basePATH+ commonPATH + zipPATH;
        }
        File file = new File(zipath);
        //如果文件夹不存在
        if(!file.exists()){
            file.mkdirs();
        }
        //压缩文件
        String resourceName = receipt+".zip";
        String zipName = zipath+"/"+resourceName;
        //如果文件存在
        if(path==null && exits(zipName)){
            // TODO: 2019/7/28 如果文件会改动,需要修改
            return zipName;
            //File z = new File(zipName);
            //z.delete();
        }
        RandomAccessFile raf = null;
        FileChannel fc = null;
        FileLock fl = null;
        //当有多个线程在同时操作文件时,multiThread = true
        boolean multiThread = false;
        try {
            raf = new RandomAccessFile(zipName+"l", "rw");
            fc = raf.getChannel();
            while(true) {
                try {
                    //独占锁
                    fl = fc.tryLock();
                    if (fl != null) {
                        break;
                    }
                    multiThread = true;
                } catch (Exception e) {
                    //如果是同一进程的多线程，重复请求tryLock()会抛出OverlappingFileLockException异常
                }
            }
            if(multiThread){
                return zipName;
            }
            //对文件进行压缩
            ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipName)));
            // -- 设置压缩方法
            zipOut.setMethod(ZipOutputStream.DEFLATED);
            BufferedInputStream  input = null;
            for (String str : fileNames) {
                if(exits(str)){
                    File file1 = new File(str);
                    input = new BufferedInputStream(new FileInputStream(file1));
                    byte[] data = new byte[(int) file1.length()];
                    input.read(data);
                    zipOut.putNextEntry(new ZipEntry(file1.getName()));
                    zipOut.write(data);
                    if(input!=null){
                        input.close();
                    }
                    zipOut.closeEntry();
                }
            }
            if(zipOut!=null){
                zipOut.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fl.release();
                fc.close();
                raf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return zipName;
    }

    /**
     * 删除文件
     * @param fileNames
     */
    public static void delete(List<String> fileNames){
        for(String file:fileNames){
            if(exits(file)){
                File z = new File(file);
                z.delete();
            }
        }
    }


    /**
     * 将申述信息写入到word文档中
     * @param appeal
     * @param user
     * @param type
     * @return
     */
    public static String appealWord(Inspect_appeal appeal, User user, Inspect_type type){
        String wordTempPath = basePATH + commonPATH + appealWordTemlPATH;
        String path = basePATH + commonPATH + wordPATH +"/"+appeal.getReceipt()+".doc";
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(wordTempPath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            //将信息输出到word文档中
            if(appeal!=null){
                range.replaceText("${title}",appeal.getTitle());
                range.replaceText("${receipt}",appeal.getReceipt()+"");
                range.replaceText("${relation_number}",appeal.getRelation_number());
                range.replaceText("${process_status}",PROCESSSTATUS[appeal.getProcess_status()-1]);
                String date = TimeUtils.timeStampToDate(appeal.getCreate_time()+"");
                range.replaceText("${create_time}",date);
                range.replaceText("${detail}",appeal.getDetail());
            }
            if(type!=null){
                range.replaceText("${type}",type.getTitle());
            }
            if(user!=null){
                range.replaceText("${realname}",user.getRealname());
                range.replaceText("${identity}",user.getIdentity()+"");
                range.replaceText("${phone}",user.getPhone()+"");
                range.replaceText("${address}",user.getAddress());
            }
            os = new FileOutputStream(new File(path));
            //把doc输出到输出流中
            doc.write(os);
            os.close();
            is.close();
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        return null;
    }

    /**
     * @param inform
     * @param user
     * @param type
     * @return
     */
    public static String informWord(Inspect_inform inform, User user, Inspect_type type){
        String wordTempPath =basePATH + commonPATH + informWordTemlPATH;
        String path =basePATH + commonPATH + wordPATH +"/"+inform.getReceipt()+".doc";
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(wordTempPath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            //将信息输出到word文档中
            if(inform!=null){
                range.replaceText("${title}",inform.getTitle());
                range.replaceText("${receipt}",inform.getReceipt()+"");
                range.replaceText("${process_status}",PROCESSSTATUS[inform.getProcess_status()-1]);
                String date = TimeUtils.timeStampToDate(inform.getCreate_time()+"");
                range.replaceText("${create_time}",date);
                range.replaceText("${detail}",inform.getDetail());
                range.replaceText("${suspect}",inform.getSuspect());
            }
            if(type!=null){
                range.replaceText("${type}",type.getTitle());
            }
            if(user!=null){
                range.replaceText("${realname}",user.getRealname());
                range.replaceText("${identity}",user.getIdentity()+"");
                range.replaceText("${phone}",user.getPhone()+"");
                range.replaceText("${address}",user.getAddress());
            }
            os = new FileOutputStream(new File(path));
            //把doc输出到输出流中
            doc.write(os);
            os.close();
            is.close();
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return null;
    }

    /**
     *
     * @param sue
     * @param user
     * @param type
     * @return
     */
    public static String sueWord(Inspect_sue sue, User user, Inspect_type type){
        String wordTempPath = basePATH + commonPATH+ sueWordTemlPATH;
        String path = basePATH +  commonPATH + wordPATH +"/"+sue.getReceipt()+".doc";
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(wordTempPath);
            HWPFDocument doc = new HWPFDocument(is);
            Range range = doc.getRange();
            //将信息输出到word文档中
            if(sue!=null){
                range.replaceText("${title}",sue.getTitle());
                range.replaceText("${receipt}",sue.getReceipt()+"");
                range.replaceText("${process_status}",PROCESSSTATUS[sue.getProcess_status()-1]);
                String date = TimeUtils.timeStampToDate(sue.getCreate_time()+"");
                range.replaceText("${create_time}",date);
                range.replaceText("${detail}",sue.getDetail());
                range.replaceText("${suspect}",sue.getSuspect());
            }
            if(type!=null){
                range.replaceText("${type}",type.getTitle());
            }
            if(user!=null){
                range.replaceText("${realname}",user.getRealname());
                range.replaceText("${identity}",user.getIdentity()+"");
                range.replaceText("${phone}",user.getPhone()+"");
                range.replaceText("${address}",user.getAddress());
            }
            os = new FileOutputStream(new File(path));
            //把doc输出到输出流中
            doc.write(os);
            os.close();
            is.close();
            return path;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
        }
        return null;
    }

    /**
     * 对ie浏览器兼容,并解决文件名中文问题
     * @param fileInfo
     * @return
     */
    public static  ResponseEntity<Resource> downloadResponse(
            DownloadFileInfo fileInfo) {
        File file = fileInfo.getFile();
        String fileName = fileInfo.getFileName();
        org.springframework.core.io.Resource body = new FileSystemResource(file);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String header = request.getHeader("User-Agent").toUpperCase();
        HttpStatus status = HttpStatus.CREATED;
        try {
            if (header.contains("MSIE") || header.contains("TRIDENT") || header.contains("EDGE")) {
                fileName = URLEncoder.encode(fileName, "UTF-8");
                fileName = fileName.replace("+", "%20");    // IE下载文件名空格变+号问题
                status = HttpStatus.OK;
            } else {
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }
        } catch (UnsupportedEncodingException e) {}

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(file.length());
        return new ResponseEntity<org.springframework.core.io.Resource>(body, headers, status);
    }



}
