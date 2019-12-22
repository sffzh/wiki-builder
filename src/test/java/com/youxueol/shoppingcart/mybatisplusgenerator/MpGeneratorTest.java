package com.youxueol.shoppingcart.mybatisplusgenerator;

import java.io.File;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * <p> 通过junit test 生成代码  </p>
 *
 * @date 2018/11/29
 */
public class MpGeneratorTest {

        
    public static void main(String[] args) {
    	generate(
    			"127.0.0.1:3306/yx_shoppingcart",
    			"YX", "com.youxueol", "cart",
    			"YX_SHOPPING_CART",
    			"YX_CART_DISCOUNT", 
    			"YX_CART_HISTORY", 
    			"YX_CART_ITEM", 
    			"YX_CART_ITEM_KIND",
    			"YX_CART_ITEM_DISCOUNT", 
    			"YX_CART_PREORDER"
    			);
    	
    	generate(
    			"127.0.0.1:3306/yx_shoppingcart",
    			"YX", "com.youxueol", "discount",
    			"YX_DISCOUNT", 
    			"YX_DISCOUNT_ITEM", 
    			"YX_DISCOUNT_KIND", 
    			"YX_DISCOUNT_THRESHOLD", 
    			
    			"YX_DISCOUNT_COUPON",
    			"YX_USER_COUPON"

    			);
	}

    private static void generate(String DBURL,String moduleName,String packageRoot,String packageName,
    		String... tableNamesInclude){
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir("D:\\workspace\\shopping-cart\\src\\main\\java");
        gc.setAuthor("Ryan");
        gc.setOpen(false);
        //默认不覆盖，如果文件存在，将不会再生成，配置true就是覆盖
        gc.setFileOverride(false);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://"+DBURL+"?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(moduleName);
        if(packageName == null)
        	pc.setParent(packageRoot);
        else
        	pc.setParent(packageRoot+'.'+packageName);
        pc.setEntity("entity");
        pc.setMapper("mapper");
        pc.setController("controller");
        pc.setXml("mapper");

        
        mpg.setPackageInfo(pc);
        

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baomidou.mybatisplus.samples.generator.common.BaseEntity");
        strategy.setEntityLombokModel(false);
//        strategy.setSuperControllerClass("com.baomidou.mybatisplus.samples.generator.common.BaseController");
        strategy.setInclude(tableNamesInclude);
//        strategy.setSuperEntityColumns("id");
        strategy.setEntityLombokModel(true);
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix("YX_");
        strategy.entityTableFieldAnnotationEnable(true);
        mpg.setStrategy(strategy);
        // 选择 freemarker 引擎需要指定如下加，注意 pom 依赖必须有！
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());

//        configCustomizedCodeTemplate(mpg);
        configInjection(mpg,packageName);

        mpg.execute();
    }

    /**
     * 自定义模板
     * @param mpg
     */
    private static void configCustomizedCodeTemplate(AutoGenerator mpg){
        //配置 自定义模板
        TemplateConfig templateConfig = new TemplateConfig()
//                .setEntity("templates/MyEntityTemplate.java")//指定Entity生成使用自定义模板
                .setXml(null);//不生成xml
        mpg.setTemplate(templateConfig);
    }
    
    private static String resourceDirPath(String javaSRCPath,String subPath) {
    	File dic = new File(javaSRCPath);
    	if(dic.getName().equals("java")) {
    		return new File(new File(javaSRCPath).getParentFile(),subPath).getAbsolutePath();
    	}
    	return null;
    }

    /**
     * 配置自定义参数/属性
     *
     * @param mpg
     */
    private static void configInjection(AutoGenerator mpg,String packageName){
    	
    	String subPath = packageName == null || packageName.isEmpty()?"":
    		File.separator + packageName.replace(".", File.separator);
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
//                this.setMap(map);
                /*
                自定义属性注入: 模板配置：abc=${cfg.abc}
                 */
            }
            public InjectionConfig setConfig(ConfigBuilder config) {
            	config.getPathInfo()
            	.put(ConstVal.XML_PATH, 
            			resourceDirPath(config.getGlobalConfig().getOutputDir(),"resources/mapper" + subPath)
            			);
            	return this;
            }
        };
//        List<FileOutConfig> focList = new ArrayList<>();
//        focList.add(new FileOutConfig("/templates/MyEntityTemplate.java.ftl") {
//            @Override
//            public String outputFile(TableInfo tableInfo) {
//                // 指定模板生，自定义生成文件到哪个地方
//                return "D:/abc";
//            }
//        });
//        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
    }
}
