package cn.sffzh.wiki.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;
import lombok.experimental.Accessors;

@ConfigurationProperties(prefix = "markdownFileConfig")
@Data
@Accessors(chain = true)
public class MDSrcConfig {

	/**
	 * markdown文件存放地址的根目录
	 */
	private String sourceDic;
	
	/**
	 * 项目部署时的根路径。
	 */
	private String rootUriPath;
}
