package cc.mrbird.common.utils;

import cc.mrbird.common.properties.FebsOssQiniuProperties;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;

/**七牛云Oss 工具类
 * @author liuzx
 * @Date 2018年11月29日21:12:41
 */
@Component
public class QiniuOssUtil {


    private static FebsOssQiniuProperties febsOssQiniuProperties;


    @Autowired
    public QiniuOssUtil(FebsOssQiniuProperties febsOssQiniuProperties) {
        QiniuOssUtil.febsOssQiniuProperties = febsOssQiniuProperties;
    }


    public static String upload(FileInputStream file, String fileName) {
        // 构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        // 其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);

        try {
            Auth auth = Auth.create(febsOssQiniuProperties.getAccessKey(), febsOssQiniuProperties.getSecretKey());
            String upToken = auth.uploadToken(febsOssQiniuProperties.getBucket());
            try {
                Response response = uploadManager.put(file, fileName, upToken, null, null);
                // 解析上传成功的结果
                DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

                String returnPath = febsOssQiniuProperties.getPath() + "/" + putRet.key;
                return returnPath;
            } catch (QiniuException ex) {
                Response r = ex.response;
                System.err.println(r.toString());
                try {
                    System.err.println(r.bodyString());
                } catch (QiniuException ex2) {
                    //ignore
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  null;
    }

}
