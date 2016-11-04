// tag::runner[]
package bookmarks;


import bookmarks.specs.CustomRepositoryFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryFactoryBeanClass = CustomRepositoryFactoryBean.class)
public class Application {

	/*@Bean
	CommandLineRunner init(AccountRepository accountRepository,
			BookmarkRepository bookmarkRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,lx,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(
						a -> {
							Account account = accountRepository.save(new Account(a,
									"password"));
							bookmarkRepository.save(new Bookmark(account,
									"http://bookmark.com/1/" + a, "A description"));
							bookmarkRepository.save(new Bookmark(account,
									"http://bookmark.com/2/" + a, "A description"));
						});
	}*/

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
// end::runner[]
