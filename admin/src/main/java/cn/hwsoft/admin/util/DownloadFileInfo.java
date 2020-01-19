package cn.hwsoft.admin.util;

import lombok.Getter;
import lombok.Setter;

import java.io.File;

/**
 * @program: court
 * @description: 文件下载
 * @author: QEcode
 * @create: 2019-07-21 08:43
 **/
@Setter
@Getter
public class DownloadFileInfo {
    private String fileName;
    private File file;

    public DownloadFileInfo() {}

    public DownloadFileInfo(String fileName, File file) {
        this.fileName = fileName;
        this.file = file;
    }


}
