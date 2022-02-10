package br.com.viittor.springdata;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		while(true) {
			System.out.println("Qual a função que você deseja acessar");
			System.out.println("0 - Sair");
			System.out.println("1 - Estudante");

			Integer function = scanner.nextInt();

			switch (function) {
				case 1:
					break;
				default:
					System.out.println("Finalizando");
					System.exit(0);
					break;
			}
		}
	}
}
