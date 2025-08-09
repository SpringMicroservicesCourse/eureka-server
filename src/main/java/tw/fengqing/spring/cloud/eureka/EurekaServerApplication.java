package tw.fengqing.spring.cloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Eureka服務註冊中心啟動類
 * 
 * 此應用程式提供微服務架構中的服務註冊與發現功能
 * 
 * 主要功能：
 * - 服務註冊：微服務實例向Eureka註冊自己的服務資訊
 * - 服務發現：微服務可以從Eureka獲取其他服務的實例清單
 * - 健康檢查：定期檢查已註冊服務的健康狀態
 * - 負載均衡：配合Spring Cloud LoadBalancer實現服務負載均衡
 * 
 * 預設端口：8761
 * 管理介面：http://localhost:8761
 * 
 * @author SpringMicroservicesCourse
 * @version 1.0
 * @since Spring Boot 3.4.5, Spring Cloud 2024.0.2
 */
@SpringBootApplication
@EnableEurekaServer  // 啟用Eureka Server功能
public class EurekaServerApplication {

	/**
	 * 應用程式啟動入口
	 * 
	 * @param args 命令列參數
	 */
	public static void main(String[] args) {
		SpringApplication.run(EurekaServerApplication.class, args);
	}

}