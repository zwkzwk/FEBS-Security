package cc.mrbird.common.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author liuzx
 * @date 2018年11月29日18:59:35
 * 文件上传OSS类型
 */
@Getter
@AllArgsConstructor
public enum EnumOss {

	LOCAL,

	QINIU,

	ALIYUN,

	TENCENT;

}
