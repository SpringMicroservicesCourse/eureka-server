# Eureka 服務註冊中心 ⚡

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.5-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2024.0.2-blue.svg)](https://spring.io/projects/spring-cloud)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## 專案介紹

本專案實作一個基於 Netflix Eureka 的服務註冊中心，是微服務架構中負責服務註冊與發現的核心元件。透過此註冊中心，各微服務可以動態註冊自己的服務資訊，並發現其他可用的服務實例。

**核心功能：**
- **服務註冊**：微服務實例向 Eureka 註冊自己的服務資訊
- **服務發現**：微服務可以從 Eureka 獲取其他服務的實例清單  
- **健康檢查**：定期檢查已註冊服務的健康狀態
- **負載均衡**：配合 Spring Cloud LoadBalancer 實現服務負載均衡

> 💡 **為什麼選擇 Eureka？**
> - 簡單易用，與 Spring Cloud 整合度高
> - 提供直觀的管理介面，方便監控服務狀態
> - 支援服務自動註冊與剔除機制
> - AP 模式設計，具備良好的可用性

### 🎯 專案特色

- **開箱即用**：透過 `@EnableEurekaServer` 註解快速啟用服務註冊中心
- **視覺化管理**：提供 Web 管理介面，可即時查看已註冊的服務狀態
- **高可用性**：支援叢集部署，確保服務註冊中心的高可用
- **自我保護機制**：在網路分割情況下保護已註冊的服務資訊

## 技術棧

### 核心框架
- **Spring Boot 3.4.5** - 主框架，提供自動配置與生產就緒功能
- **Spring Cloud 2024.0.2** - 微服務框架，提供服務註冊與發現等功能
- **Netflix Eureka Server** - 服務註冊與發現的核心實作

### 開發工具與輔助
- **Maven** - 專案建構與依賴管理工具
- **Java 21** - 執行環境（支援最新的語言特性）

## 專案結構

```
eureka-server/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── tw/fengqing/spring/cloud/eureka/
│   │   │       └── EurekaServerApplication.java      # 主啟動類
│   │   └── resources/
│   │       └── application.properties                # 配置檔案
│   └── test/
├── target/                                           # 編譯輸出目錄
├── pom.xml                                          # Maven 專案配置
├── LICENSE                                          # 授權檔案
└── README.md                                        # 專案說明文件
```

## 快速開始

### 前置需求
- Java 21 或更高版本
- Maven 3.6+ 或 Gradle 7.0+
- 網路連線（用於下載依賴套件）

### 安裝與執行

1. **克隆此倉庫：**
```bash
git clone <repository-url>
```

2. **進入專案目錄：**
```bash
cd eureka-server
```

3. **編譯專案：**
```bash
mvn clean compile
```

4. **執行應用程式：**
```bash
# 方法一：使用 Maven 執行
mvn spring-boot:run

# 方法二：打包後執行
mvn clean package
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar
```

5. **存取管理介面：**
```
開啟瀏覽器，前往：http://localhost:8761
```

## 進階說明

### 環境變數
```properties
# 伺服器連接埠（可選，預設為8761）
SERVER_PORT=8761

# Eureka 叢集配置（生產環境使用）
EUREKA_INSTANCE_HOSTNAME=eureka-server-1
EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE=http://eureka-server-2:8761/eureka,http://eureka-server-3:8761/eureka
```

### 設定檔說明
```properties
# application.properties 主要設定

# 伺服器連接埠設定
server.port=8761

# 單機模式設定：不註冊自己，也不從其他 Eureka Server 獲取服務註冊資訊
eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false

# 生產環境叢集設定範例（註解說明）
# eureka.client.service-url.defaultZone=http://peer1:8761/eureka,http://peer2:8761/eureka
# eureka.instance.hostname=${EUREKA_INSTANCE_HOSTNAME:localhost}
# eureka.server.enable-self-preservation=true
```

### 微服務註冊設定
其他微服務要註冊到此 Eureka Server，需要在其 `application.properties` 中加入：

```properties
# 應用程式名稱（必要）
spring.application.name=your-service-name

# Eureka Server 位址
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/

# 服務實例設定
eureka.instance.prefer-ip-address=true
eureka.instance.lease-renewal-interval-in-seconds=30
eureka.instance.lease-expiration-duration-in-seconds=90
```

## 參考資源

- [Spring Cloud Netflix 官方文件](https://cloud.spring.io/spring-cloud-netflix/reference/html/)
- [Eureka Wiki](https://github.com/Netflix/eureka/wiki)
- [Spring Cloud 官方文件](https://spring.io/projects/spring-cloud)
- [微服務架構模式](https://microservices.io/patterns/service-registry.html)

## 注意事項與最佳實踐

### ⚠️ 重要提醒

| 項目 | 說明 | 建議做法 |
|------|------|----------|
| 生產環境部署 | 單機模式不適用於生產環境 | 建議部署至少 2-3 個 Eureka 實例形成叢集 |
| 網路安全 | Eureka 預設沒有身份驗證 | 生產環境請加入 Spring Security 進行保護 |
| 效能調優 | 預設設定適用於開發環境 | 生產環境需調整心跳、續約等時間間隔參數 |
| 版本相容性 | Netflix 已停止 Eureka 2.0 開發 | 建議評估 Consul、Nacos 等替代方案 |

### 🔒 最佳實踐指南

- **叢集部署**：生產環境請部署多個 Eureka 實例，避免單點故障
- **健康檢查**：配置適當的健康檢查端點，確保服務狀態準確
- **安全防護**：加入身份驗證與授權機制，保護註冊中心安全
- **監控告警**：整合 Actuator 與監控系統，即時掌握服務狀態
- **資源規劃**：根據註冊服務數量，合理分配記憶體與CPU資源

### 📊 叢集部署範例

若要建立 Eureka 叢集，可參考以下配置：

**eureka-server-1 配置：**
```properties
server.port=8761
eureka.instance.hostname=eureka-server-1
eureka.client.service-url.defaultZone=http://eureka-server-2:8762/eureka,http://eureka-server-3:8763/eureka
```

**eureka-server-2 配置：**
```properties
server.port=8762
eureka.instance.hostname=eureka-server-2
eureka.client.service-url.defaultZone=http://eureka-server-1:8761/eureka,http://eureka-server-3:8763/eureka
```

## 授權說明

本專案採用 MIT 授權條款，詳見 LICENSE 檔案。

## 關於我們

我們主要專注在敏捷專案管理、物聯網（IoT）應用開發和領域驅動設計（DDD）。喜歡把先進技術和實務經驗結合，打造好用又靈活的軟體解決方案。

## 聯繫我們

- **FB 粉絲頁**：[風清雲談 | Facebook](https://www.facebook.com/profile.php?id=61576838896062)
- **LinkedIn**：[linkedin.com/in/chu-kuo-lung](https://www.linkedin.com/in/chu-kuo-lung)
- **YouTube 頻道**：[雲談風清 - YouTube](https://www.youtube.com/channel/UCXDqLTdCMiCJ1j8xGRfwEig)
- **風清雲談 部落格**：[風清雲談](https://blog.fengqing.tw/)
- **電子郵件**：[fengqing.tw@gmail.com](mailto:fengqing.tw@gmail.com)

---

**📅 最後更新：2024年12月**  
**👨‍💻 維護者：風清雲談團隊**
