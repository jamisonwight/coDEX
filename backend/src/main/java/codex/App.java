package codex;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

@Lazy
@SpringBootApplication
public class App extends AbstractJavaFxApplicationSupport {

	/**
	 * Note that this is configured in application.properties
	 */
	@Value("CoDEX")
	private String windowTitle;

	@Override
	public void start(Stage stage) throws Exception {

		stage.setWidth(800);
		stage.setHeight(600);

		final StackPane stackPane = new StackPane();
		final WebView window = new WebView();
		final WebEngine webEngine = window.getEngine();

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.setContent(window);

		webEngine.getLoadWorker().stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {

				if (newState == Worker.State.SUCCEEDED) {
					stage.setTitle(webEngine.getLocation());
				}

			}
		});
		webEngine.load("http://localhost:8080/");
		stackPane.getChildren().add(window);
		Scene scene = new Scene(stackPane);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) {
		launchApp(App.class, args);
	}

	// Enable CORS globally
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/api/*").allowedOrigins("http://localhost:8080");
			}
		};
	}
}
