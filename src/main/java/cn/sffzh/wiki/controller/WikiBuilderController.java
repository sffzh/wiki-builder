package cn.sffzh.wiki.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/builder")
public class WikiBuilderController {

	@RequestMapping("/src/{mdName}")
	public String getUnloadMarkdown(String mdName) {
		
	}
}
