package hello.itemservice;

import hello.itemservice.config.MyBatisConfig;
import hello.itemservice.repository.ItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

/**
 * scanBasePackages = hello.itemservice.web 패키지 하위 파일만 컴포넌트 스캔
 * 컨트롤러에서 Service, Repository 에 의존하기 때문에 자동주입이 되지않는다.
 * @Import 어노테이션 사용해서 직접 빈으로 등록하면 설정파일 내 빈들로 의존성 주입이 된다.
 */
@Slf4j
@Import(MyBatisConfig.class)
@SpringBootApplication(scanBasePackages = "hello.itemservice.web")
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}


	/**
	 * local 프로필일때만 동작(환경에 따라 다른 설정할때 사용)
	 */
	@Bean
	@Profile("local")
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

	/**
	 * test 프로필일때만 동작
	 * jdbc:h2:mem:db를 url로 지정하면 임베디드 모드(메모리 모드)로 동작하는 h2 데이터베이스를 사용할 수 있다
	 * DB_CLOSE_DELAY=-1 임베디드 모드에서는 데이터베이스 커넥션 연결이 모두 끊어지면 데이터베이스도 종료되는데,
	 * 그것을 방지하는 설정이다.
	 */
/*	@Bean
	@Profile("test")
	public DataSource dataSource(){
		log.info("메모리 데이터베이스 초기화");
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
		dataSource.setUsername("sa");
		dataSource.setPassword("");
		return dataSource;
	}*/

}
