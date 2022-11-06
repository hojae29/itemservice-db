package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
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

//@Import(MemoryConfig.class)
@Import(JdbcTemplateV1Config.class)
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

}
