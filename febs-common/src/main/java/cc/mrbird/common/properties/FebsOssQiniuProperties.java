package cc.mrbird.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**获取配置文件中的七牛配置
 * @author liuzx
 * @Date 2018年11月29日21:13:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "febs.oss.qiniu")
public class FebsOssQiniuProperties {

    private String accessKey;

    private String secretKey;

    private String bucket;

    private String path;




}
